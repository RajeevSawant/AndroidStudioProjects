package com.example.rajeevsawant.americanfootball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int pointForTeamA = 0;
    int pointForTeamB = 0;
    int strikeforTeamA = 0;
    int strikeforTeamB = 0;
    int outsForTeamA = 0;
    int outsForTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runsDisplayForTeamA(0);
        runsDisplayForTeamB(0);
        strikeDisplayForTeamB(0);
        strikeDisplayForTeamA(0);
        outsDisplayForTeamA(0);
        outsDisplayForTeamB(0);
    }


    public void resetALL(View view) {
        pointForTeamA = 0;
        pointForTeamB = 0;
        strikeforTeamA = 0;
        strikeforTeamB = 0;
        outsForTeamA = 0;
        outsForTeamB = 0;

        runsDisplayForTeamA(pointForTeamA);
        runsDisplayForTeamB(pointForTeamB);
        strikeDisplayForTeamB(strikeforTeamB);
        strikeDisplayForTeamA(strikeforTeamA);
        outsDisplayForTeamA(outsForTeamA);
        outsDisplayForTeamB(outsForTeamB);
    }

    public void onePointForTeamA(View view) {
        pointForTeamA += 1;
        runsDisplayForTeamA(pointForTeamA);

    }

    public void onePointForTeamB(View view) {
        pointForTeamB += 1;
        runsDisplayForTeamB(pointForTeamB);
    }

    public void strikeForTeamA(View view) {
        if (strikeforTeamA < 3) {
            strikeforTeamA += 1;
            strikeDisplayForTeamA(strikeforTeamA);
        } else {
            strikeforTeamA = 0;
            if (outsForTeamA < 9) {
                outsForTeamA += 1;
                outsDisplayForTeamA(outsForTeamA);
                strikeDisplayForTeamA(strikeforTeamA);
            }
        }

    }

    public void strikeForTeamB(View view) {
        if (strikeforTeamB < 3) {
            strikeforTeamB += 1;
            strikeDisplayForTeamB(strikeforTeamB);
        } else {
            strikeforTeamB = 0;
            if (outsForTeamB < 9) {
                outsForTeamB += 1;
                outsDisplayForTeamB(outsForTeamB);
                strikeDisplayForTeamB(strikeforTeamB);
            }

        }

    }


    public void runsDisplayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void runsDisplayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }


    public void strikeDisplayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_strike);
        scoreView.setText(String.valueOf(score));
    }

    public void strikeDisplayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_strike);
        scoreView.setText(String.valueOf(score));
    }


    public void outsDisplayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_out);
        scoreView.setText(String.valueOf(score));
    }

    public void outsDisplayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_out);
        scoreView.setText(String.valueOf(score));
    }
}
