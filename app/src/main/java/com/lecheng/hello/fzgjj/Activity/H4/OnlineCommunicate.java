package com.lecheng.hello.fzgjj.Activity.H4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Adpt.Common.UnityAdapter;
import com.lecheng.hello.fzgjj.Adpt.Common.ViewHolder;
import com.lecheng.hello.fzgjj.Bean.BeanZxlyList;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import RxWeb.GsonUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OnlineCommunicate extends Activity implements IWSListener {
    @Bind(R.id.lv1)
    ListView lv1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home4d);
        ButterKnife.bind(this);
        ActionBar frag= (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("在线问答");
    }

    @Override
    protected void onResume() {
        super.onResume();
        new HttpGo().httpWebService(this, this, "zxlyList", new String[]{}, new String[]{});
    }

    @OnClick({R.id.btn1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                startActivity(new Intent(this, Comments.class));
                break;
        }
    }

    @Override
    public void onWebServiceSuccess(String s) {
        try {
            BeanZxlyList bean = GsonUtil.GsonToBean(s, BeanZxlyList.class);
            lv1.setAdapter(new UnityAdapter<BeanZxlyList.DataBean>(this, bean.getData(), R.layout.item7_zxly) {
                @Override
                public void convert(ViewHolder helper, BeanZxlyList.DataBean item, int position) {
                    helper.setText(R.id.tvTitle, item.getTitle());
                    helper.setText(R.id.tv1, item.getName());
                    helper.setText(R.id.tv2, item.getTwnr());
                    helper.setText(R.id.tv4, item.getHfnr());
                    try {
                        helper.setText(R.id.tv3, "日期：" + item.getCreatedate().substring(2, 11));
                        helper.setText(R.id.tv5, "日期：" + item.getUpdatedate().substring(2, 11));
                    } catch (Exception e) {
                    }
                }
            });
        } catch (Exception e) {
            new MyToast(this, "数据解析失败", 0);
        }
    }
}
