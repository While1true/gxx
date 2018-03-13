package com.lecheng.hello.fzgjj.Activity.H2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class AppointmentAnnouncement extends Activity {

    ActionBar frag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2d);
        ButterKnife.bind(this);
        frag= (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("预约申请");
    }


}
