# aidl
aidl：主要用于进程间的通信。

使用过程中注意点：<br/>
1、服务端aidlserver和客户端aidlclient的aidl包名必须相同<br/>
2、aidl文件最好从服务器端copy放入到client端<br/>
3、aidl支持的数据类型：    
   &nbsp; ● 基本数据类型（int、long、char、boolean、double等）；  <br/>
   &nbsp; ● String和CharSequence；<br/>
   &nbsp; ● List：只支持ArrayList，里面每个元素都必须能够被AIDL支持；  <br/>
   &nbsp; ● Map：只支持HashMap，里面的每个元素都必须被AIDL支持，包括key和value  <br/>
   &nbsp; ● Percelable：所有实现了Parecelable接口的对象<br/>
   &nbsp; ● AIDL：所有的AIDL接口本身也可以在AIDL文件中使用<br/>
4、aidlserver端被kill掉，aidlclient还是可以调用返回值问题？<br/>
    bindService(intent,connection,BIND_AUTO_CREATE);<br/>
    //第三个参数一般选Context.BIND_AUTO_CREATE，意思是如果在绑定过程中，Service进程被意外杀死了，系统还会自动重新启动被绑定的Service。所以当我们点击KILL PROCESS按钮的时候会杀死Service进程，但是马上又会自动重启， 重新调用onServiceConnected方法重新绑定。当然，这个参数还有别的一些选择Context.BIND_AUTO_CREATE
   
