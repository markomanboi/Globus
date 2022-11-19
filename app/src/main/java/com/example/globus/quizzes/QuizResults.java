package com.example.globus.quizzes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.globus.R;
import com.example.globus.SelectionScreen;

public class QuizResults extends AppCompatActivity {

    TextView correct, incorrect, attempted, score, you;
    int cor = 0, incorr = 0, attempt = 0, scor = 0, yo = 0, whichQuiz=0;
    int x = 0;
    private static final int def = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        Intent intent = getIntent();
        cor = intent.getIntExtra("correct", 0);
        attempt = intent.getIntExtra("attemp", 0);
        whichQuiz= intent.getIntExtra("categ", 0);
        incorr = attempt - cor;
        scor = 10 * cor;
        correct = (TextView) findViewById(R.id.correct);
        incorrect = (TextView) findViewById(R.id.incorrect);
        attempted = (TextView) findViewById(R.id.attempted);
        score = (TextView) findViewById(R.id.score);
        you = (TextView) findViewById(R.id.you);
        if (whichQuiz==1 || whichQuiz==2 || whichQuiz==3) {
            attempted.setText("  " + attempt);
            correct.setText("  " + cor);
            incorrect.setText("  " + incorr);
            score.setText("Score  :    " + cor + "/" + 15);
            float x1 = (cor * 100) / 15;
            if (cor==15){
                you.setText("Perfect!");
            }
            else if (x1 < 40)
                you.setText("You Failed!");
            else if (x1 < 70)
                you.setText("You did average!");
            else if (x1 < 90)
                you.setText("You did above average!");
            else
                you.setText("You did well!");
        }
        else{
            attempted.setText("  " + attempt);
            correct.setText("  " + cor);
            incorrect.setText("  " + incorr);
            score.setText("Score  :    " + cor + "/" + 10);
            float x1 = (cor * 100) / 10;
            if (cor==10){
                you.setText("Perfect!");
            }
            else if (x1 < 40)
                you.setText("You Failed!");
            else if (x1 < 70)
                you.setText("You did average!");
            else if (x1 < 90)
                you.setText("You did above average!");
            else
                you.setText("You did well!");
        }
        Button backQuiz=findViewById(R.id.btnBackQuiz);

        backQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizResults.this, SelectionScreen.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
