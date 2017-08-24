package com.example.admin.myfirstapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Set;

import static android.content.ContentValues.TAG;

/**
 * Created by Admin on 1/4/2017.
 */

public class SecondActivity extends AppCompatActivity implements FirstInterface {
    String VarId = "";
    String address = "";
    BluetoothAdapter btAdapter;
    BluetoothDevice ConnectingDevice;
    public Button reset;
    public Button right;
    public Button stop;
    public Button forward;
    public Button left;
    public Button back;

    public TextView pitch;
    public TextView roll;
    MyService mService ;
    String PitchAngle = "Pitch: ";
    String RollAngle = "Roll: ";
    static String pitchData = "";
    static String rollData = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity);

        reset = (Button) findViewById(R.id.reset);
        stop = (Button) findViewById(R.id.stop);
        right = (Button) findViewById(R.id.right);
        left = (Button) findViewById(R.id.left);
        back= (Button) findViewById((R.id.back));
        forward = (Button) findViewById(R.id.forward);
//        pitch = (TextView) findViewById(R.id.pitchAngle);
//        roll = (TextView) findViewById(R.id.rollAngle);
        mService = new MyService();


        VarId = "HC-05";
        address = "20:16:01:06:54:31";
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
        for (BluetoothDevice btd : pairedDevices) {
            if (btd.getAddress().toString().equals(address)) {
                ConnectingDevice = btd;
                break;
            }
        }
        try {
            Intent service = new Intent(SecondActivity.this, MyService.class);

            service.putExtra("Device", ConnectingDevice);
            startService(service);

        } catch (Exception e) {
            Log.d(TAG, "" + e);
        }
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mService.writeData("R");
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mService.writeData("S");
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mService.writeData("Y");
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mService.writeData("F");
            }
        });


        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mService.writeData("L");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mService.writeData("B");
            }
        });


//        Thread t = new Thread() {
//
//            @Override
//            public void run() {
//                try {
//                    while (!isInterrupted()) {
//                        Thread.sleep(1000);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//
//                        pitch.setText(PitchAngle+pitchData);
//                                roll.setText(RollAngle+rollData);
//
//                            }
//                        });
//                    }
//                }
//                catch (InterruptedException e) {
//                }
//            }
//        };
//
//        t.start();


    }
    public void pitchAngle( String pitch_value)
    {
        pitchData= pitch_value;


//            pitch.setText(PitchAngle);
//        PitchAngle = "Pitch:";
    }
    public void rollAngle(String roll_value)
    {
       rollData=roll_value;

//            roll.setText(RollAngle);
//        RollAngle = "Roll: ";
    }
}

