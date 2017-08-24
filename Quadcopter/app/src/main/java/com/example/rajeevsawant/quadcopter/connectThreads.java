package com.example.rajeevsawant.quadcopter;

import android.bluetooth.BluetoothSocket;
import java.io.InputStream;
import android.util.Log;
import java.io.IOException;
import java.io.OutputStream;

import static android.content.ContentValues.TAG;


public class connectThreads extends Thread {
    private static BluetoothSocket mmSocket;
    private static InputStream mmInStream;
    private static OutputStream mmOutStream;
    static SecondActivity s1 = new SecondActivity();
    // private final Handler mHandler;

    public connectThreads(BluetoothSocket socket) {
        mmSocket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        //   mHandler = new Handler();

        // Get the input and output streams, using temp objects because
        // member streams are final
        try {
            tmpIn = mmSocket.getInputStream();
            tmpOut = mmSocket.getOutputStream();

        } catch (IOException e) {
        }

        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }

    /* Call this from the main activity to send data to the remote device */
    public void write(byte[] bytes) {
        try {
            Log.d(TAG, "Inside Connected Thread");

            mmOutStream.write(bytes);
        } catch (IOException e) {
            Log.d(TAG, "" + e);
        }

    }

    /* Call this from the main activity to shutdown the connection */
    public void cancel() {
        try {
            mmSocket.close();
            mmInStream.close();
            mmOutStream.close();
        } catch (IOException e) {
        }
    }

    public void run() {
        // Keep listening to the InputStream until an exception occurs
        byte[] buffer = new byte[100];
        int begin = 0;
        int bytes = 0; // bytes returned from read()
        String roll_angle = "";
        String pitch_angle = "";
        String temp = "";
        while (true) {
            try {

                bytes += mmInStream.read(buffer, bytes, buffer.length - bytes);
                if (bytes > 0) {
                    String data = new String(buffer);
                    for (int i = begin; i < bytes; i++) {
                        if(data.charAt(i) == '#')
                        {
                            pitch_angle = temp;
                            s1.pitchAngle(pitch_angle);
                            Log.d(TAG,""+pitch_angle);
                            temp = "";
                        }
                        else if(data.charAt(i) == '*')
                        {
                            roll_angle = temp;
                            s1.rollAngle(roll_angle);
                            Log.d(TAG,""+roll_angle);
                            temp = "";

                        }
                        else
                        {
                            temp = temp + data.charAt(i);
                        }
                        if (i == bytes - 1) {

                            bytes = 0;
                            begin = 0;
                        }

                    }

                }
            }
            catch(IOException e)
            {
                break;
            }
        }
    }
}