package com.lecheng.hello.fzgjj.Activity.H2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.ActivityManager;
import com.lecheng.hello.fzgjj.Utils.MySP;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalInfo extends AppCompatActivity implements IWSListener {

    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tv2)
    TextView tv2;
    @Bind(R.id.tv3)
    TextView tv3;
    @Bind(R.id.tv4)
    TextView tv4;
    @Bind(R.id.tv5)
    TextView tv5;
    @Bind(R.id.tv6)//单位地址
            TextView tv6;
    @Bind(R.id.tv7)
    TextView tv7;
    @Bind(R.id.tv8)
    TextView tv8;
    @Bind(R.id.tv9)
    TextView tv9;
    @Bind(R.id.tv10)
    TextView tv10;
    @Bind(R.id.tv11)
    TextView tv11;
    @Bind(R.id.tv12)
    TextView tv12;
    @Bind(R.id.btnNext)
    Button btnNext;
    ActionBar frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2ba);
        ButterKnife.bind(this);
        frag= (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("2.个人信息");
        String[] k = {"accountcode",};
        String[] v = {MySP.loadData(this, "username", "") + ""};
        new HttpGo().httpWebService(this, this, "dwyhxxcx", k, v);
    }

    @Override
    public void onWebServiceSuccess(String s) {
        try {
            btnNext.setEnabled(true);
            JSONObject json = new JSONObject(s);
            JSONArray array = json.getJSONArray("data");
            JSONObject j2 = (JSONObject) array.get(0);
            tv1.setText(j2.getString("name"));
            tv2.setText(j2.getString("credit_code"));
            tv3.setText(j2.getString("sex").equals("1") ? "男" : "女");
            tv4.setText(j2.getString("birthday"));
            tv5.setText(j2.getString("WORKUNITS"));
            tv6.setText(j2.getString("mobile"));
//            tv7.setText(j2.getString(""));
            tv8.setText(j2.getString("PROFESSIONAL"));
//            tv9.setText(j2.getString(""));
//            tv10.setText(j2.getString(""));
//            tv11.setText(j2.getString(""));
            tv12.setText(j2.getString(""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.btnBack, R.id.btnNext})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnNext:
                startActivity(new Intent(this, Implication.class));
                ActivityManager.get().add(this);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.get().remove();
    }
}
