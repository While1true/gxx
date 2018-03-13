package com.lecheng.hello.fzgjj.Activity.Unit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lecheng.hello.fzgjj.MainActivity;
import com.lecheng.hello.fzgjj.R;

import butterknife.ButterKnife;

public class Register extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup c, Bundle sis) {
        View view = inflater.inflate(R.layout.unit_reg, c, false);
        ButterKnife.bind(this, view);
        MainActivity.instance.setBg1();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
