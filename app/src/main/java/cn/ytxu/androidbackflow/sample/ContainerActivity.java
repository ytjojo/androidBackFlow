package cn.ytxu.androidbackflow.sample;

import android.os.Bundle;

import cn.ytxu.androidbackflow.BaseActivity;
import cn.ytxu.androidbackflow.BaseFragment;
import cn.ytxu.androidbackflow.R;

/**
 * Created by ytxu on 16/12/31.
 */
public class ContainerActivity extends BaseActivity {

    /**
     * sample: XXXFragment.class.getName()
     */
    public final static String PARAM_CLASSNAME = "className";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseFragment fragment = getFragment();
        if (fragment == null) {
//            finish();
            return;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitAllowingStateLoss();
    }

    private BaseFragment getFragment() {
        String className = getIntent().getStringExtra(PARAM_CLASSNAME);
        BaseFragment fragment = null;
        if (className != null && !className.isEmpty()) {
            try {
                fragment = (BaseFragment) getClassLoader().loadClass(className).newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return fragment;
    }

}
