package com.lecheng.hello.fzgjj.Widget;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.lecheng.hello.fzgjj.Bean.UpdateBean;
import com.lecheng.hello.fzgjj.Interface.MyCallback;
import com.lecheng.hello.fzgjj.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by vange on 2017/12/15.
 */

public class MyDialogFragment extends DialogFragment {
    @Bind(R.id.info)
    TextView info;
    @Bind(R.id.version)
    TextView version;
    @Bind(R.id.size)
    TextView size;
    private View inflate;
    private MyCallback<MyDialogFragment> callback;
    UpdateBean bean;

    public MyDialogFragment setCallback(MyCallback<MyDialogFragment> callback) {
        this.callback = callback;
        return this;
    }

    private int layout;

    public MyDialogFragment setLayout(int layout) {
        this.layout = layout;
        return this;
    }

    public MyDialogFragment setBean(UpdateBean bean) {
        this.bean = bean;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(layout, container, false);
        ButterKnife.bind(this, inflate);
        initView();
        return inflate;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    private void initView() {
        size.setText("文件大小：" + bean.getAppSize());
        version.setText("版本号：" + bean.getVersionNumber());
        info.setText(bean.getUpdateInformation());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.cancel, R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                dismiss();
                break;
            case R.id.confirm:
                if (callback != null) {
                    callback.call(this);
                }
                break;
        }
    }
}
