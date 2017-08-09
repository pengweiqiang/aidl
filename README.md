# aidl
aidl：主要用于进程间的通信。

使用过程中注意点：
1、服务端aidlserver和客户端aidlclient的aidl包名必须相同
2、aidl文件最好从服务器端copy放入到client端
3、aidl支持的数据类型：
    ● 基本数据类型（int、long、char、boolean、double等）；
    ● String和CharSequence；
    ● List：只支持ArrayList，里面每个元素都必须能够被AIDL支持；
    ● Map：只支持HashMap，里面的每个元素都必须被AIDL支持，包括key和value
    ● Percelable：所有实现了Parecelable接口的对象
    ● AIDL：所有的AIDL接口本身也可以在AIDL文件中使用
4、aidlserver端被kill掉，aidlclient还是可以调用返回值问题？
    bindService(intent,connection,BIND_AUTO_CREATE);
    //第三个参数一般选Context.BIND_AUTO_CREATE，意思是如果在绑定过程中，Service进程被意外杀死了，
    // 系统还会自动重新启动被绑定的Service。所以当我们点击KILL PROCESS按钮的时候会杀死Service进程，但是马上又会自动重启，
    // 重新调用onServiceConnected方法重新绑定。当然，这个参数还有别的一些选择Context.BIND_AUTO_CREATE
   
