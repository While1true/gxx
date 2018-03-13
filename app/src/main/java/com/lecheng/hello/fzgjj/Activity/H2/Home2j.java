package com.lecheng.hello.fzgjj.Activity.H2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lecheng.hello.fzgjj.R;


public class Home2j extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2j);
//        EventBus.getDefault().post("单位业务指南");//改变anctionbar的标题
    }
}
