package com.example.rajeevsawant.testingapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        TextView testing = new TextView(this);
        testing.setText(" Welcome ");
        testing.setAllCaps(true);
        testing.setTextColor(Color.parseColor("#FFA500"));
        testing.setTextSize(20);

        setContentView(testing);
    }

}
