package com.example.rajeevsawant.logs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void printToLogs(View view) {
        // Find first menu item TextView and print the text to the logs
        TextView firstMenu = (TextView) findViewById(R.id.menu_item_1);
        Log.v("MainActivity", (String) firstMenu.getText());

        // Find second menu item TextView and print the text to the logs

        TextView secondMenu = (TextView) findViewById(R.id.menu_item_2);
        Log.v("MainActivity", (String)secondMenu.getText());

        // Find third menu item TextView and print the text to the logs

        TextView thirdMenu = (TextView) findViewById(R.id.menu_item_3);
        Log.v("MainActivity", (String)thirdMenu.getText());

    }
}
