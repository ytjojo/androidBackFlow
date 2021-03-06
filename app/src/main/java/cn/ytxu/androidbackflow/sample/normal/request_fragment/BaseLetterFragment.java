package cn.ytxu.androidbackflow.sample.normal.request_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cn.ytxu.androidbackflow.BackFlow;
import cn.ytxu.androidbackflow.BaseBackFlowFragment;
import cn.ytxu.androidbackflow.R;
import cn.ytxu.androidbackflow.sample.ContainerActivity;

public class BaseLetterFragment extends BaseBackFlowFragment {

    private TextView tipTxt;
    private Button jumpBtn, rollbackFlowBtn;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.container_letter_fragment, container, false);
        setRoot(view);

        tipTxt = $(R.id.letter_tip_txt);
        jumpBtn = $(R.id.letter_jump_btn);
        rollbackFlowBtn = $(R.id.letter_back_flow_btn);
        rollbackFlowBtn.setVisibility(View.GONE);
        tipTxt.setText(getClass().getSimpleName());

        jumpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(getActivity(), ContainerActivity.class);
                    intent.putExtra(ContainerActivity.PARAM_CLASSNAME, LetterFragmentType.getNextFragmentName(BaseLetterFragment.this));
                    startActivity(intent);
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        });

        $(R.id.letter_jump_but_non_back_flow_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(getActivity(), ContainerActivity.class);
                    intent.putExtra(ContainerActivity.PARAM_CLASSNAME, LetterFragmentType.getNextFragmentName(BaseLetterFragment.this));
                    startActivity4NonBackFlow(intent);
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        });

        finishApp();

        initView();
        return view;
    }

    protected void initView() {
    }

    protected void setRollbackFlow(final Class<? extends Fragment> atyClass) {
        setRollbackFlow(atyClass, "rollback :" + atyClass.getSimpleName());
    }

    protected void setRollbackFlow(final Class<? extends Fragment> atyClass, String text) {
        rollbackFlowBtn.setVisibility(View.VISIBLE);
        rollbackFlowBtn.setText(text);
        rollbackFlowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackFlow.request(BaseLetterFragment.this, atyClass);
            }
        });
    }

    private void finishApp() {
        $(R.id.letter_finish_task_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackFlow.finishTask(BaseLetterFragment.this);
            }
        });
    }
}
