package com.example.globus.Learning;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.globus.R;
import com.example.globus.quizzes.QuizEconomy;

public class EconomicGlobalization extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    public final static String Message = "com.example.globus.MESSAGE";
    Button btnQuiz;
    private ProgressDialog progressBar;
    private TextView[] mDots;
    ImageView backbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_globalization_concepts);

        mSlideViewPager=(ViewPager) findViewById(R.id.viewPagerGlobalization);
        mDotLayout= (LinearLayout) findViewById(R.id.dots);

        mSlideViewPager.setAdapter(new MyAdapter());
        addDotsIndicator(0);

        ImageView backbutton=findViewById(R.id.btnBack);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public class MyAdapter extends PagerAdapter {

        LayoutInflater layoutInflater;

        int layouts[]={R.layout.economy_page1,R.layout.economy_page2,R.layout.economy_page3,
                       R.layout.economy_page4, R.layout.economy_page5,  R.layout.economy_page6,
                        R.layout.economy_page7, R.layout.economy_page8, R.layout.economy_page9};
        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == (ConstraintLayout) o;
        }

        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            layoutInflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=layoutInflater.inflate(R.layout.economy_page1,container,false);
            View view2=layoutInflater.inflate(R.layout.economy_page2,container,false);
            View view3=layoutInflater.inflate(R.layout.economy_page3,container,false);
            View view4=layoutInflater.inflate(R.layout.economy_page4,container,false);
            View view5=layoutInflater.inflate(R.layout.economy_page5,container,false);
            View view6=layoutInflater.inflate(R.layout.economy_page6,container,false);
            View view7=layoutInflater.inflate(R.layout.economy_page7,container,false);
            View view8=layoutInflater.inflate(R.layout.economy_page8,container,false);
            View view9=layoutInflater.inflate(R.layout.economy_page9,container,false);
            View[] viewarr ={view,view2,view3,view4,view5,view6,view7,view8,view9};

            container.addView(viewarr[position]);
            return viewarr[position];
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((ConstraintLayout)(object));
        }
    }

    public void addDotsIndicator(int position){
        mDots=new TextView[9];
        mDotLayout.removeAllViews();
        for (int i=0; i<mDots.length; i++){
            mDots[i]=new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorGrey));

            mDotLayout.addView(mDots[i]);
        }
        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorBlack));
        }
    }

    ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (position==8){
                btnQuiz=findViewById(R.id.btnTakeQuiz);
                btnQuiz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //To show button click
                        new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);

                        progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                        progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                        progressBar.setMessage("Getting Questions Ready ...");//Title shown in the progress bar
                        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                        progressBar.setProgress(0);//attributes
                        progressBar.setMax(100);//attributes
                        progressBar.show();//show the progress bar
                        //This handler will add a delay of 3 seconds
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Intent start to open the navigation drawer activity
                                progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                                Intent intent = new Intent(EconomicGlobalization.this, QuizEconomy.class);
                                intent.putExtra(Message, "c1");
                                startActivity(intent);
                            }
                        }, 2000);
                    }
                });
            }
        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
