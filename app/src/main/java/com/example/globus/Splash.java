package com.example.globus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Splash extends AppCompatActivity {
    ProgressBar progressBar;
    int progress=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar=findViewById(R.id.progressBar2);

        setProgressValue(progress);

        final RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1200);
        rotate.setInterpolator(new LinearInterpolator());

        final ImageView image= (ImageView) findViewById(R.id.globeRotate);
        image.startAnimation(rotate);
        rotate.setFillAfter(true);

    }
    private void setProgressValue(final int progress) {

        // set the progress
        progressBar.setProgress(progress);
        // thread is used to change the progress value
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setProgressValue(progress + 10);
            }
        });
        thread.start();

        if (progress==100) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }
}
