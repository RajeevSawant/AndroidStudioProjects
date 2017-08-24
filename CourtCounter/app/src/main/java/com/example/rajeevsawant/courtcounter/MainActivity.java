package com.example.rajeevsawant.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int scoreTeamA = 0;
    int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeamA(0);
        displayForTeamB(0);
    }

    public void threePointScoreA(View view){
        scoreTeamA += 3;
        displayForTeamA(scoreTeamA);
    }

    public void threePointScoreB(View view){
        scoreTeamB += 3;
        displayForTeamB(scoreTeamB);
    }


    public void twoPointScoreA(View view){
        scoreTeamA += 2;
        displayForTeamA(scoreTeamA);
    }

    public void twoPointScoreB(View view){

        scoreTeamB += 2;
        displayForTeamB(scoreTeamB);
    }

    public void freeThrowScoreA(View view){
        scoreTeamA += 1;
        displayForTeamA(scoreTeamA);
    }

    public void freeThrowScoreB(View view){
        scoreTeamB += 1;
        displayForTeamB(scoreTeamB);
    }

    public void resetAll(View view){
        scoreTeamB = 0;
        scoreTeamA = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

}
