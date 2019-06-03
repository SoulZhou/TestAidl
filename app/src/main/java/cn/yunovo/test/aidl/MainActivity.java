package cn.yunovo.test.aidl;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;

import cn.yunovo.nxos.testcrash.IMyAidlInterface;
import cn.yunovo.nxos.testcrash.log.LogUtils;
import cn.yunovo.nxos.testcrash.sdk.ipc.MyAidlInterfaceKit;

public class MainActivity extends Activity {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyAidlInterfaceKit.getInstance().setmContext(this);
        MyAidlInterfaceKit.getInstance().setCallBackAidl(callBackAidl);
        MyAidlInterfaceKit.getInstance().startConnect();
    }

    MyAidlInterfaceKit.CallBackAidl callBackAidl = new MyAidlInterfaceKit.CallBackAidl() {
        @Override
        public void onConnect(IMyAidlInterface iMyAidlInterface) {
            try {
                String str = iMyAidlInterface.getName();
                LogUtils.d(TAG,"str = " + str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void unConnect() {

        }
    };
}
