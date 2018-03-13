package com.lecheng.hello.fzgjj.Activity.H3;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.R;

import de.greenrobot.event.EventBus;
@Deprecated
public class Home3d extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home3d);
        ActionBar frag= (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("各类计算器");
    }
}
