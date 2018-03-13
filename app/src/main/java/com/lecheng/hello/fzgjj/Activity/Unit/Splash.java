package com.lecheng.hello.fzgjj.Activity.Unit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lecheng.hello.fzgjj.MainActivity;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Splash extends Fragment {


    private Runnable action;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup c, Bundle sis) {
        view = inflater.inflate(R.layout.unit_splash, c, false);
        ButterKnife.bind(this, view);
        action = new Runnable() {
            @Override
            public void run() {
                getFragmentManager().popBackStack();

                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().overridePendingTransition(0, 0);
                getActivity().finish();
            }
        };
        view.postDelayed(action,2000);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view.removeCallbacks(action);
        ButterKnife.unbind(this);
    }


    @OnClick(R.id.tv)
    public void onClick() {
        new MyToast(getActivity(), "欢迎来到福建住房公积金APP", 1);
    }
}
