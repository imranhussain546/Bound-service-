package com.imran.boundservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private Button start;

private Boolean mbound=false;
private MyService service;
private ServiceConnection serviceConnection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        MyService.MyServiceBinder myServiceBinder= (MyService.MyServiceBinder) iBinder;
        service=myServiceBinder.getService();
        mbound=true;
        Log.d(MyService.TAG, "onServiceConnected: ");
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        Log.d(MyService.TAG, "onServiceDisconnected: ");
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=findViewById(R.id.button);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent service =new Intent(MainActivity.this,MyService.class);
                bindService(service,serviceConnection, Context.BIND_AUTO_CREATE);
            }
        });
    }

}