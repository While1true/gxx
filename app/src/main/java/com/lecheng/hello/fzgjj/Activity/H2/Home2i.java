package com.lecheng.hello.fzgjj.Activity.H2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lecheng.hello.fzgjj.R;

import de.greenrobot.event.EventBus;

public class Home2i extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2i);
//        EventBus.getDefault().post("个人业务指南");//改变anctionbar的标题
    }
}
