package com.example.globus.quizzes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.globus.R;
import com.example.globus.quizDB.QuizGlobalizationEconomy;
import com.example.globus.quizDB.QuizGlobalizationSociety;
import com.github.lzyzsd.circleprogress.DonutProgress;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;

public class QuizSociety extends AppCompatActivity {

    DonutProgress donutProgress;
    int variable =0;
    TextView ques;
    Button OptA, OptB, OptC, OptD;
    Button play_button;
    String get;
    QuizGlobalizationSociety quizGlobalizationSociety;
    public int visibility = 0, c1 = 0, i, j = 0, k = 0, l = 0;
    String global = null, Ques, Opta, Optb, Optc, Optd;
    ArrayList<Integer> list = new ArrayList<Integer>();
    Toast toast;
    TextView numQuestion;
    int countQuestion=0;
    int time = 120;
    boolean pause=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_society);

        numQuestion=findViewById(R.id.numQuestion);
        Intent intent = getIntent();//recieving the intent send by the Navigation activity
        get = "c1";
        toast = new Toast(this);
        //attribute of the circular progress bar
        donutProgress = (DonutProgress) findViewById(R.id.donut_progress);
        donutProgress.setMax(120);
        donutProgress.setFinishedStrokeColor(Color.parseColor("#2196F3"));
        donutProgress.setTextColor(Color.parseColor("#2196F3"));
        donutProgress.setKeepScreenOn(true);
        SharedPreferences sp = getSharedPreferences("Score", Context.MODE_PRIVATE);

        //Now the linking of all the datbase files with the Question activity
        quizGlobalizationSociety = new QuizGlobalizationSociety(this);
        quizGlobalizationSociety.createDatabase();
        quizGlobalizationSociety.openDatabase();
        quizGlobalizationSociety.getWritableDatabase();

        //Till here we are linking the database file
        OptA = (Button) findViewById(R.id.OptionA);
        OptB = (Button) findViewById(R.id.OptionB);
        OptC = (Button) findViewById(R.id.OptionC);
        OptD = (Button) findViewById(R.id.OptionD);
        ques = (TextView) findViewById(R.id.Questions);
        play_button = (Button) findViewById(R.id.play_button);//Play button to start the game
    }

    public void onClick(View v) {//When this method is executed then there will be new question came and also same method for play button
        final SharedPreferences shared = getSharedPreferences("Score", Context.MODE_PRIVATE);
        k++;
        if (visibility == 0) {
            //showing the buttons which were previously invisible
            OptA.setVisibility(View.VISIBLE);
            OptB.setVisibility(View.VISIBLE);
            OptC.setVisibility(View.VISIBLE);
            OptD.setVisibility(View.VISIBLE);
            play_button.setVisibility(View.GONE);
            donutProgress.setVisibility(View.VISIBLE);
            visibility = 1;
            new CountDownTimer(120000, 1000) {//countdowntimer
                int i = 120;

                @Override
                public void onTick(long millisUntilFinished) {
                    i = i - 1;
                    time =i;
                    donutProgress.setProgress(i);


                }

                @Override
                public void onFinish() {
                    toast.cancel();
                    SharedPreferences.Editor editor = shared.edit();//here we are saving the data when the countdown timer will finish and it is saved in shared prefrence file that is defined in onCreate method as score
                    editor.putInt("Questions", k).commit();
                    editor.putInt("Society", l * 10).apply();
                    donutProgress.setProgress(0);
                    if(variable==0) {
                        Intent intent = new Intent(QuizSociety.this, QuizResults.class);
                        intent.putExtra("correct", l);
                        intent.putExtra("attemp", k);
                        intent.putExtra("categ", 3);
                        startActivity(intent);
                        finish();
                    }
                }
            }.start();
        }

        if (global != null) {
            if (global.equals("A")) {
                if (v.getId() == R.id.OptionA) {
                    //Here we use the snackbar because if we use the toast then they will be stacked an user cannot idetify which questions answer is it showing
                    Snackbar.make(v, "         Correct ☺", Snackbar.LENGTH_SHORT).show();

                    l++;
                } else {
                    Snackbar.make(v, "Incorrect\t      Answer :" + Opta + "", Snackbar.LENGTH_SHORT).show();
                }

            } else if (global.equals("B")) {
                if (v.getId() == R.id.OptionB) {
                    Snackbar.make(v, "          Correct ☺", Snackbar.LENGTH_SHORT).show();
                    l++;
                } else {
                    Snackbar.make(v, "Incorrect\t      Answer :" + Optb + "", Snackbar.LENGTH_SHORT).show();
                }

            } else if (global.equals("C")) {
                if (v.getId() == R.id.OptionC) {

                    Snackbar.make(v, "         Correct ☺", Snackbar.LENGTH_SHORT).show();
                    l++;
                } else {
                    Snackbar.make(v, "Incorrect\tAnswer :" + Optc + "", Snackbar.LENGTH_SHORT).show();
                }
            } else if (global.equals("D")) {
                if (v.getId() == R.id.OptionD) {
                    Snackbar.make(v, "        Correct ☺", Snackbar.LENGTH_SHORT).show();
                    l++;
                } else {

                    Snackbar.make(v, "Incorrect\tAnswer :" + Optd + "", Snackbar.LENGTH_SHORT).show();
                }
            }
        }
        if (get.equals("c1")) {

            if (c1 == 0) {
                for (i = 1; i < 16; i++) {
                    list.add(new Integer(i));
                }
                Collections.shuffle(list);
                c1=1;
            }
            if (j==15 || time==0) {
                pause=true;
                toast.cancel();
                SharedPreferences.Editor editor = shared.edit();//here we are saving the data when the countdown timer will finish and it is saved in shared prefrence file that is defined in onCreate method as score
                editor.putInt("Questions", k).commit();
                editor.putInt("Society", l * 10).apply();
                donutProgress.setProgress(0);
                if(variable==0) {
                    Intent intent = new Intent(QuizSociety.this, QuizResults.class);
                    intent.putExtra("correct", l);
                    intent.putExtra("attemp", k-1);
                    intent.putExtra("categ", 3);
                    startActivity(intent);
                    finish();
                }
            }
            else{
                countQuestion++;
                numQuestion.setText(countQuestion+"/"+15);
                Ques = quizGlobalizationSociety.readQuestion(list.get(j));
                Opta = quizGlobalizationSociety.readOptionA(list.get(j));
                Optb = quizGlobalizationSociety.readOptionB(list.get(j));
                Optc = quizGlobalizationSociety.readOptionC(list.get(j));
                Optd = quizGlobalizationSociety.readOptionD(list.get(j));
                global = quizGlobalizationSociety.readAnswer(list.get(j++));
            }

        }
        ques.setText("" + Ques);
        OptA.setText(Opta);
        OptB.setText(Optb);
        OptC.setText(Optc);
        OptD.setText(Optd);
    }

    @Override
    protected void onPause() {
        super.onPause();
        variable =1;
        SharedPreferences sp = getSharedPreferences("Score", Context.MODE_PRIVATE);
        if (time!=0 && j<=15) {
            if (pause==false)
                Toast.makeText(this, "No cheating! Quiz has been reset!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        variable =1;
        SharedPreferences sp = getSharedPreferences("Score", Context.MODE_PRIVATE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        variable = 1;
        finish();
    }
}
