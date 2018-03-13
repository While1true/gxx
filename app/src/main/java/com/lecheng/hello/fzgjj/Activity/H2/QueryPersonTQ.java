package com.lecheng.hello.fzgjj.Activity.H2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.ck.hello.nestrefreshlib.View.Adpater.SLoading;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Adpt.Common.UnityAdapter;
import com.lecheng.hello.fzgjj.Adpt.Common.ViewHolder;
import com.lecheng.hello.fzgjj.Bean.GRTQBean;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;

import RxWeb.GsonUtil;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class QueryPersonTQ extends Activity implements IWSListener {

    @Bind(R.id.lv1)
    PullToRefreshListView lv1;
    @Bind(R.id.tv1)
    TextView tv1;
    @Bind(R.id.tv2)
    TextView tv2;
    @Bind(R.id.tv3)
    TextView tv3;
    @Bind(R.id.tv5)
    TextView tv5;
    @Bind(R.id.sloading)
    SLoading sloading;
    ActionBar frag;
    private String subperacct = "", type = "1";
    private boolean getBt = false;
    private Runnable action;
    private UnityAdapter<GRTQBean.DataBean> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2fff);
        ButterKnife.bind(this);
        sloading.startAnimator();
        frag = (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("个人提取查询");
        httpGet(MySP.loadData(this, "username", "") + "", "1");
//        tv1.setText("账户");
//        tv2.setText("提取时间");
//        tv3.setText("提取金额");
//        tv4.setText("余额");
//        tv5.setText("类型");
    }

    public void httpGet(String acc, String type) {
        String[] k = {"gjjaccount", "type"};
        String[] v = {acc, type};
        new HttpGo().httpWebService(this, this, "grtqmxcx", k, v);
    }

    @Override
    public void onWebServiceSuccess(String s) {
        sloading.stopAnimator();
        sloading.setVisibility(View.GONE);
        if (Constance.DEBUGTAG)
            Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() +
                    "--", "onWebServiceSuccess: " + s);

        try {
            final GRTQBean bean = GsonUtil.GsonToBean(s, GRTQBean.class);
//            if (!getBt) subperacct = bean.getData().get(0).getSUBPERACCT();
            getBt = true;
            if(adapter==null) {
                adapter = new UnityAdapter<GRTQBean.DataBean>(this, bean.getData(), R.layout.item2f) {
                    @Override
                    public void convert(ViewHolder helper, GRTQBean.DataBean item, int position) {
                        String name = item.getDraw_no();
                        helper.setText(R.id.tv1, name == null ? "" : name);
                        String data = item.getDraw_prin();
                        helper.setText(R.id.tv2, data == null ? "" : data);
                        String drawmoney = item.getSreal_draw_date();
                        helper.setText(R.id.tv3, drawmoney == null ? "" : drawmoney);
                        String bal = item.getDraw_mode();
                        helper.setText(R.id.tv4, bal == null ? "" : bal);
                        String reason = item.getDraw_rea();
                        helper.setText(R.id.tv5, reason == null ? "" : reason);
                    }
                };
            }else{
                adapter.setList(bean.getData());
            }
            lv1.setAdapter(adapter);
            lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    /*startActivity(new Intent(QueryPersonTQ.this, GJJAccountTQQuery.class)
                            .putExtra("draw_no", bean.getData().get(position - 1).getDRAW_NO())
                            .putExtra("type", type));*/
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            new MyToast(this, "无相关信息", 1);
            adapter.notifyDataSetChanged();
//            e.printStackTrace();
//            action = new Runnable() {
//                @Override
//                public void run() {
//                    finish();
//                }
//            };
//            lv1.postDelayed(action,1500);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lv1.removeCallbacks(action);
        sloading.stopAnimator();
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                httpGet(MySP.loadData(this, "username", "") + "", type = "1");
                break;
            case R.id.btn2:
//                if (subperacct.equals(""))
//                    new MyToast(this, "暂无补贴账户信息", 1);
//                else
                adapter.getList().clear();
                httpGet(subperacct, type = "3");
                break;
        }
    }

}