package com.example.globus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class NewsSelection extends AppCompatActivity {

    public static String categ="";
    public boolean CIITorNot=false;

    public boolean isCIITorNot() {
        return CIITorNot;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ImageView backbutton=findViewById(R.id.btnBack);

        Button btnCIIT=findViewById(R.id.btnCIIT);
        Button btnFeat=findViewById(R.id.btnFeatured);
        Button btnBusiness=findViewById(R.id.btnBusiness);
        Button btnEntertainment=findViewById(R.id.btnEntertainment);
        Button btnScience=findViewById(R.id.btnScience);
        Button btnTechnology=findViewById(R.id.btnTechnology);
        Button btnHealth=findViewById(R.id.btnHealth);
        Button btnSports=findViewById(R.id.btnSports);

        btnCIIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CIITorNot=true;
                categ="CIIT News";
                startActivity(new Intent(NewsSelection.this, CIITNews.class));
            }
        });

        btnFeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categ="Featured News";
                startActivity(new Intent(NewsSelection.this, FeaturedNews.class));
            }
        });

        btnBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categ="business";
                startActivity(new Intent(NewsSelection.this, FeaturedNews.class));
            }
        });

        btnEntertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categ="entertainment";
                startActivity(new Intent(NewsSelection.this, FeaturedNews.class));
            }
        });

        btnScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categ="science";
                startActivity(new Intent(NewsSelection.this, FeaturedNews.class));
            }
        });

        btnSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categ="sports";
                startActivity(new Intent(NewsSelection.this, FeaturedNews.class));
            }
        });

        btnHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categ="health";
                startActivity(new Intent(NewsSelection.this, FeaturedNews.class));
            }
        });

        btnTechnology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categ="technology";
                startActivity(new Intent(NewsSelection.this, FeaturedNews.class));
            }
        });


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static String getCateg() {
        return categ;
    }
}
