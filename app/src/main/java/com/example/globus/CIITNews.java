package com.example.globus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;

public class CIITNews extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{
    private TextView appbar_title, appbar_subtitle,date, time ,title;
    private LinearLayout titleAppbar;
    private Toolbar toolbar;
    private boolean isHideToolbarView=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_i_i_t_news);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        appbar_title=findViewById(R.id.title_on_appbar);
        appbar_subtitle=findViewById(R.id.subtitle_on_appbar);
        titleAppbar=findViewById(R.id.title_appbar);

        initWebView("https://www.facebook.com/CIITMedia/");

        appbar_title.setText("CIIT News");
        appbar_subtitle.setText("See what's going on at CIIT.");

    }

    private void initWebView(String url){
        WebView webView=findViewById(R.id.CIITWeb);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll=appBarLayout.getTotalScrollRange();
        float percentage=(float) Math.abs(verticalOffset)/(float) maxScroll;

        if (percentage==1f && isHideToolbarView){
            titleAppbar.setVisibility(View.VISIBLE);
            isHideToolbarView=!isHideToolbarView;
        }

        else if (percentage<1f && isHideToolbarView){
            titleAppbar.setVisibility(View.GONE);
            isHideToolbarView=!isHideToolbarView;
        }
    }
}
