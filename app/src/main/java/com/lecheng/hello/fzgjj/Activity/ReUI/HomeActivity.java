package com.lecheng.hello.fzgjj.Activity.ReUI;

import android.os.Bundle;

import com.lecheng.hello.fzgjj.Utils.StateBarUtils;

import RxWeb.BaseAppCompatActivity;

/**
 * Created by 不听话的好孩子 on 2018/3/12.
 */

public class HomeActivity extends BaseAppCompatActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        StateBarUtils.performTransStateBar(getWindow());
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new HomeFragment()).commit();
    }
}
