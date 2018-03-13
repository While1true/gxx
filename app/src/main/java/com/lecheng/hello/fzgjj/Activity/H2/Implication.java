package com.lecheng.hello.fzgjj.Activity.H2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.ActivityManager;
import com.lecheng.hello.fzgjj.Utils.MyToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Implication extends AppCompatActivity {

    @Bind(R.id.cb1)
    CheckBox cb1;
    @Bind(R.id.btnBack)
    Button btnBack;
    @Bind(R.id.btnNext)
    Button btnNext;
    ActionBar frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2bb);
        ButterKnife.bind(this);
        frag= (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("3.提取申明");
    }

    @OnClick({R.id.btnBack, R.id.btnNext})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnNext:
                if (cb1.isChecked()) {
                    startActivity(new Intent(this, CreditDocument.class));
                    ActivityManager.get().add(this);
                } else new MyToast(this, "请阅读并接受协议申明", 0);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.get().remove();
    }
}
