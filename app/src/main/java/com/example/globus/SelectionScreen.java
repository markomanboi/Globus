package com.example.globus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.globus.Learning.EconomicGlobalization;
import com.example.globus.Learning.GlobalizationConcepts;
import com.example.globus.Learning.InternationalLawGlobalization;
import com.example.globus.Learning.NationGlobalization;
import com.example.globus.Learning.RegionalismGlobalization;
import com.example.globus.Learning.SocietyGlobalization;

public class SelectionScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_screen);

        ImageView backbutton=findViewById(R.id.btnBackSelect);


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectionScreen.this, MainActivity.class));
                finish();
            }
        });

        ImageView globalization=findViewById(R.id.btnGlobe);
        ImageView economicglobalization=findViewById(R.id.btnEcon);
        ImageView society=findViewById(R.id.btnSociety);
        ImageView nation=findViewById(R.id.btnNation);
        ImageView internationalLaw=findViewById(R.id.btnUN);
        ImageView regionalism=findViewById(R.id.btnRegion);
        ImageView imgGlobe=findViewById(R.id.imageViewGlobeSelect);

        globalization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectionScreen.this, GlobalizationConcepts.class));
            }
        });

        economicglobalization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectionScreen.this, EconomicGlobalization.class));
            }
        });

        society.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectionScreen.this, SocietyGlobalization.class));
            }
        });

        nation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectionScreen.this, NationGlobalization.class));
            }
        });

        internationalLaw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectionScreen.this, InternationalLawGlobalization.class));
            }
        });

        regionalism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectionScreen.this, RegionalismGlobalization.class));
            }
        });

        imgGlobe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectionScreen.this, "Pick a topic to learn about!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
