package com.will.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author will on 2017/8/9 16:34
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class MyService extends Service {

    MyBinder myBinder =new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    ////调用aidl文件的sub(相当于 class MyBinder extends Binder implents IMIn,此处和eclipse使用aidl用的一样)
    class MyBinder extends IMyAidlInterface.Stub{

        @Override
        public String pp() throws RemoteException {
            Log.d("AAAAAAAAAAAAA", "来自客户端的调用");
            return "success";

        }
    }

}
