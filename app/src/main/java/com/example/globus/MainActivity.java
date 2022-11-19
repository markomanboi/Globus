package com.example.globus;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnnews=findViewById(R.id.btnNews);
        Button btnLearnGlobe=findViewById(R.id.btnLearn);
        Button btnAbout=findViewById(R.id.btnAbout);
        ImageView imgGlobe=findViewById(R.id.imageViewGlobe);

        btnnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("WARNING: This feature requires an Internet connection. Data charges may apply when using mobile networks such as LTE/3G/2G. Proceed?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        Html.fromHtml("<font color='#2196F3'>Proceed</font>"),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                startActivity(new Intent(MainActivity.this, NewsSelection.class));
                            }
                        });


                builder1.setNegativeButton(
                        Html.fromHtml("<font color='#2196F3'>No</font>"),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        btnLearnGlobe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SelectionScreen.class));
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, About.class));
            }
        });

        imgGlobe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Welcome to Globus!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
