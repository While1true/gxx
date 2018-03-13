package com.lecheng.hello.fzgjj.Activity.H4;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Adpt.Common.UnityAdapter;
import com.lecheng.hello.fzgjj.Adpt.Common.ViewHolder;
import com.lecheng.hello.fzgjj.Bean.BeanZxlyList;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Interface.I047Listener;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Utils.RequestParams;

import RxWeb.BaseAppCompatActivity;
import RxWeb.GsonUtil;

import RxWeb.MyObserve;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//小豆机器人：http://xiao.douqq.com/
public class Puzzle extends BaseAppCompatActivity implements I047Listener, IWSListener {
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.lv1)
    ListView lv1;
    @Bind(R.id.et1)
    EditText et1;
    @Bind(R.id.tv2)
    TextView tv2;
    private String KEY = "bmZrS1FhdzZZRitXUFJ3OT1IaVdTdXo9TEJBQUFBPT0";
    private String URL = "http://api.douqq.com/";
    private HttpGo httpGo = new HttpGo();
    private int type;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.home4e);
        ButterKnife.bind(this);
        ActionBar frag = (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("疑问解答");
        type = getIntent().getIntExtra("type", 1);
        /*tv1.append("00" + new Random().nextInt(9) + "为您解答");*/
        httpGo.httpWebService(this, this, "ynjdList", new String[]{"type"}, new String[]{type+""});
    }

    @OnClick({R.id.btnSend})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSend:
                if (TextUtils.isEmpty(et1.getText().toString())) {
                    new MyToast(this, "请输入内容", 1);
                    return;
                }

                RequestParams params = new RequestParams();
                params.add("twnr", et1.getText().toString());
                params.add("type", type);
                ApiService.resolveProblem(params, new MyObserve<String>() {
                    @Override
                    public void onNext(String value) {
                        if (Constance.DEBUGTAG)
                            Log.i(Constance.DEBUG, "onNext: " + value);
                        new MyToast(Puzzle.this, "提问成功,请等待解答", 0);
                        et1.setText("");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
                break;
        }
    }

    @Override
    public void onSuccess(String strJson) {
//        new MyToast(this, "" + strJson, 1);
        tv2.append("客服：" + strJson + "\n\n");
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
