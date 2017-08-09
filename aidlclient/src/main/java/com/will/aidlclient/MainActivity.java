package com.will.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.will.aidl.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    IMyAidlInterface aidlServerService;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button)findViewById(R.id.btn1);
        textView = (TextView)findViewById(R.id.text);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction("com.will.aidl.action");
                intent.setPackage("com.will.aidl");
                bindService(intent,connection,BIND_AUTO_CREATE);
            }
        });
    }

    ServiceConnection connection=new ServiceConnection() {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            aidlServerService= IMyAidlInterface.Stub.asInterface(service);
            try {
                String pp = aidlServerService.pp();
                textView.setText(pp);
            } catch (RemoteException e) {
                e.printStackTrace();
            }


        }


        @Override
        public void onServiceDisconnected(ComponentName name) {
            aidlServerService=null;
        }
    };


}
