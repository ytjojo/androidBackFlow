package cn.ytxu.androidbackflow.sample;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import cn.ytxu.androidbackflow.BackFlow;

/**
 * Created by ytxu on 17/1/4.
 */
public class App extends Application {

    private static final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        BackFlow.Logger.log(TAG, "init app, process:" + getCurProcessName(this));
    }

    public String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
