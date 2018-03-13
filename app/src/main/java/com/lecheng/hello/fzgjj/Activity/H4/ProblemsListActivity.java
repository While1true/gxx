package com.lecheng.hello.fzgjj.Activity.H4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lecheng.hello.fzgjj.Activity.Unit.BaseTitleActivity;
import com.lecheng.hello.fzgjj.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by vange on 2017/11/7.
 */

public class ProblemsListActivity extends BaseTitleActivity {


    @Bind(R.id.draw)
    Button draw;
    @Bind(R.id.loan)
    Button loan;
    @Bind(R.id.deposite)
    Button deposite;
    @Bind(R.id.other)
    Button other;

    @Override
    protected int getContentLayoutId() {
        return R.layout.problems_layout;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("疑难解答");


    }



    @OnClick({R.id.draw, R.id.loan, R.id.deposite, R.id.other})
    public void onViewClicked(View view) {
        Intent intent=new Intent(this,Puzzle.class);
        switch (view.getId()) {
            case R.id.draw:
                intent.putExtra("type",1);
                break;
            case R.id.loan:
                intent.putExtra("type",2);
                break;
            case R.id.deposite:
                intent.putExtra("type",3);
                break;
            case R.id.other:
                intent.putExtra("type",4);
                break;
        }
        startActivity(intent);
    }
}
