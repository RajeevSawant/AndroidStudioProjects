package com.example.rajeevsawant.quadcopter;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

import static android.content.ContentValues.TAG;

public class MyService extends Service {
    BluetoothDevice ConnectingDevice;
    private static BluetoothSocket mmSocket;
    BluetoothAdapter mBluetoothAdapter;
    public static connectThreads mConnectedThread;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        //public void onHandleIntent(Intent intent) {
        // For time consuming an long tasks you can launch a new thread here...
        // Do your Bluetooth Work Here
        Log.d(TAG, "IN Service");
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        }
        ConnectingDevice = intent.getParcelableExtra("Device");
        Log.d(TAG,""+ConnectingDevice);
        try {
            // MY_UUID is the app's UUID string, also used by the server code
            //tmp = device.createRfcommSocketToServiceRecord(UUID.fromString("492515c1-5c1e-4e57-868c-5691b674411f"));
            //UUID idOne = UUID.randomUUID()

            mmSocket = ConnectingDevice.createInsecureRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
            //tmp = device.createInsecureRfcommSocketToServiceRecord(idOne);


        } catch (IOException e) {
            Log.d(TAG, "" + e);
        }
        mBluetoothAdapter.cancelDiscovery();

        try {
            // Connect the device through the socket. This will block
            // until it succeeds or throws an exception
            Log.d(TAG, "Connecting");
            mmSocket.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mConnectedThread = new connectThreads(mmSocket);
        mConnectedThread.start();
    }




    public void writeData(String data)
    {

        Double d = 45.0;
        Log.d(TAG,""+mmSocket);
        byte[] msgBuffer = data.getBytes();
        Log.d(TAG,"Inside WriteData");
        try {
            mConnectedThread.write(msgBuffer);
        }
        catch(Exception e)
        {
            Log.d(TAG,""+e);
        }

    }

}