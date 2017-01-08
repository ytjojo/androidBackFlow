package cn.ytxu.androidbackflow;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.ytxu.androidbackflow.sample.App;

public class BaseActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BackFlow.Logger.log(TAG, "taskId:" + getTaskId()
                + ", obj:" + Integer.toHexString(hashCode())
                + ", process:" + ((App) getApplication()).getCurProcessName(this));
    }

    public <T extends View> T $(@IdRes int id) {
        return (T) findViewById(id);
    }


    //******************** start activity replace method ********************
    public void startActivity4NonBackFlow(Intent intent) {
        super.startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void startActivity4NonBackFlow(Intent intent, @Nullable Bundle options) {
        super.startActivity(intent, options);
    }


    //******************** back flow ********************
    @Override
    public void startActivity(Intent intent) {
        startActivityForResult(intent, BackFlow.REQUEST_CODE);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        startActivityForResult(intent, BackFlow.REQUEST_CODE, options);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (BackFlow.handle(this, getSupportFragmentManager().getFragments(), requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
