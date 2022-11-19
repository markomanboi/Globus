package com.example.globus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.widget.SearchView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.widget.TextView;
import android.widget.Toast;

import com.example.globus.Models.Articles;
import com.example.globus.Models.Headlines;
import com.example.globus.Models.Source;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeaturedNews extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView recyclerView;
    public static final String API_KEY="9fa48752e393424c9bd7ef96eae4c15b";
    private RecyclerView.LayoutManager layoutManager;
    private FeaturedNewsAdapter adapter;
    private List<Articles> articles=new ArrayList<>();
    NewsSelection category=new NewsSelection();
    private TextView textView;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_featured_news);

        ImageView backbutton=findViewById(R.id.btnBack2);
        ImageView btnSearch=findViewById(R.id.btnSearch);

        final EditText search=findViewById(R.id.textSearch);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadJson(search.getText().toString());
            }
        });

        recyclerView=findViewById(R.id.recycleViewer);
        layoutManager=new LinearLayoutManager(FeaturedNews.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        swipeRefreshLayout=findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorBlue);

        onLoadingSwipeRefresh("");

    }

    private void initListener(){
        adapter.setOnItemClickListener(new FeaturedNewsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent=new Intent(FeaturedNews.this, NewsDetailActivity.class);

                Articles article=articles.get(position);
                intent.putExtra("url", article.getUrl());
                intent.putExtra("title",article.getTitle());
                intent.putExtra("img",article.getUrlToImage());
                intent.putExtra("date",article.getPublishedAt());
                intent.putExtra("source",article.getSource().getName());
                intent.putExtra("author",article.getAuthor());

                startActivity(intent);
            }
        });
    }

    public void LoadJson(final String keyword) {
        textView=findViewById(R.id.textViewNews);

        swipeRefreshLayout.setRefreshing(true);
        String categ=category.getCateg();
        if (categ.equals("Featured News")) {
            ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
            String country = Utils.getCountry();
            String language=Utils.getLanguage();
            Call<Headlines> call;

            if (keyword.length()>0){
                call=apiInterface.getNewsSearch(keyword,language,"published",API_KEY);
            }else{
                call=apiInterface.getNews(country,API_KEY);
            }
            textView.setText("Featured News");
            call.enqueue(new Callback<Headlines>() {
                @Override
                public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                    if (response.isSuccessful() && response.body().getArticles() != null) {
                        if (!articles.isEmpty()) {
                            articles.clear();
                        }
                        articles = response.body().getArticles();
                        adapter = new FeaturedNewsAdapter(articles, FeaturedNews.this);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        initListener();
                        swipeRefreshLayout.setRefreshing(false);
                    } else {
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(FeaturedNews.this, "No Result!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Headlines> call, Throwable t) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }
       else{
            ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
            determineCategory();
            String country = Utils.getCountry();
            String language=Utils.getLanguage();
            Call<Headlines> call;

            if (keyword.length()>0){
                call=apiInterface.getNewsSearch(keyword,language,"published",API_KEY);
            }else{
                call=apiInterface.getNewswithCategory(country,API_KEY,categ);
            }

            determineCategory();
            call.enqueue(new Callback<Headlines>() {
                @Override
                public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                    if (response.isSuccessful() && response.body().getArticles()!= null) {
                        if (!articles.isEmpty()) {
                            articles.clear();
                        }
                        articles = response.body().getArticles();
                        adapter = new FeaturedNewsAdapter(articles, FeaturedNews.this);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        initListener();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    else{
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(FeaturedNews.this,"No Result!",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Headlines> call, Throwable t) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }
    }

    public void determineCategory(){
        String categ=category.getCateg();
        if (categ.equals("business")){
            textView.setText("Business News");
        }
        else if (categ.equals("entertainment")){
            textView.setText("Entertainment News");
        }
        else if (categ.equals("science")){
            textView.setText("Science News");
        }
        else if (categ.equals("technology")){
            textView.setText("Technology News");
        }
        else if (categ.equals("health")){
            textView.setText("Health News");
        }
        else if (categ.equals("sports")){
            textView.setText("Sports News");
        }
    }

    @Override
    public void onRefresh() {
        LoadJson("");
    }

    private void onLoadingSwipeRefresh(final String keyword){
        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        LoadJson(keyword);
                    }
                }
        );
    }
}
