package c1725163212.mxzy.com.myapplication;

import android.app.Application;

import org.xutils.x;

public class MyApplication extends Application {
    @Override
    public void onCreate() {

        x.Ext.init(this);
        super.onCreate();

    }
}
