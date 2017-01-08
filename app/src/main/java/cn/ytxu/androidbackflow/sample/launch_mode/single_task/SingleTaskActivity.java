package cn.ytxu.androidbackflow.sample.launch_mode.single_task;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import cn.ytxu.androidbackflow.BackFlow;
import cn.ytxu.androidbackflow.BackFlowType;
import cn.ytxu.androidbackflow.BaseActivity;
import cn.ytxu.androidbackflow.R;

/**
 * Created by ytxu on 2017/1/8.
 */
public class SingleTaskActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_mode_activity);

        setTitle(getClass().getSimpleName());
        $(R.id.single_top_finish_task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackFlow.builder(BackFlowType.finish_task, SingleTaskActivity.this).create().request();
            }
        });
    }
}
