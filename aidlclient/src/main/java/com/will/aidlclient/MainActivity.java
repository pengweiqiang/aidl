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
import android.widget.Toast;

import com.will.aidl.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {
    Button btn1,btnUnbind;
    IMyAidlInterface aidlServerService;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button)findViewById(R.id.btn1);
        btnUnbind = (Button)findViewById(R.id.btn2);
        textView = (TextView)findViewById(R.id.text);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction("com.will.aidl.action");
                intent.setPackage("com.will.aidl");//由于5.0之后不能使用隐式启动，所以此处要加入服务端包名



                bindService(intent,connection,BIND_AUTO_CREATE);
                //第三个参数一般选Context.BIND_AUTO_CREATE，意思是如果在绑定过程中，Service进程被意外杀死了，
                // 系统还会自动重新启动被绑定的Service。所以当我们点击KILL PROCESS按钮的时候会杀死Service进程，但是马上又会自动重启，
                // 重新调用onServiceConnected方法重新绑定。当然，这个参数还有别的一些选择
            }
        });
        btnUnbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aidlServerService!=null) {
                    if (connection != null&&aidlServerService.asBinder().isBinderAlive()) {
                        unbindService(connection);
                    }
                }
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
            Toast.makeText(MainActivity.this,"解绑了",Toast.LENGTH_LONG).show();
            aidlServerService=null;
        }
    };


}
