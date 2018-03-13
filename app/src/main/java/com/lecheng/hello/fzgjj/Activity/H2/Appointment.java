package com.lecheng.hello.fzgjj.Activity.H2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Adpt.Common.UnityAdapter;
import com.lecheng.hello.fzgjj.Adpt.Common.ViewHolder;
import com.lecheng.hello.fzgjj.Bean.BeanYyList;
import com.lecheng.hello.fzgjj.Bean.YYResult;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Interface.MyCallback;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.ActivityManager;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Utils.RequestParams;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import RxWeb.BaseAppCompatActivity;
import RxWeb.GsonUtil;
import RxWeb.MyObserve;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Appointment extends BaseAppCompatActivity implements IWSListener {


    @Bind(R.id.lv1)
    PullToRefreshListView lv1;
    ActionBar frag;
    private int i = 0;
    private String index;
    private int type;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.home2bd);
        ButterKnife.bind(this);
        frag= (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("预约办理(选择时间)");
        i = 0;
        findViewById(R.id.btnBack).setVisibility(View.GONE);
        index = getIntent().getStringExtra("index");
        type=getIntent().getIntExtra("type",1);
        getYyList();
    }

    private void getYyList() {
        String[] k = {"yylx"};
        String[] v = {type+""};
        if (Constance.DEBUGTAG)
            Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "getYyList: "+type);
        new HttpGo().httpWebService(this, this, "yyList", k, v);
    }

    @Override
    public void onWebServiceSuccess(String s) {
        try {
            Log.d("aaa", "onWebServiceSuccess: "+s);
            final BeanYyList bean = GsonUtil.GsonToBean(s, BeanYyList.class);
            final List<BeanYyList.DataBean> data = bean.getData();
            Collections.sort(data, new Comparator<BeanYyList.DataBean>() {
                @Override
                public int compare(BeanYyList.DataBean o1, BeanYyList.DataBean o2) {
                    if (Constance.DEBUGTAG)
                        Log.i(Constance.DEBUG, "compare: " + o1.getBookingtime() + "--" + o2.getBookingtime());
                    if (o1.getLong() > o2.getLong())
                        return 1;
                    else if (o1.getLong() == o2.getLong())
                        return 0;
                    return -1;
                }
            });
            if (i == 0) {
                lv1.onRefreshComplete();
                lv1.setAdapter(new UnityAdapter<BeanYyList.DataBean>(this, data, R.layout.item3) {
                    @Override
                    public void convert(ViewHolder helper, BeanYyList.DataBean item, int position) {
                        helper.setText(R.id.tv1, "预约时间：" + item.getDate());
                        helper.setText(R.id.tv3, " 星期" + item.getXingqi() + "/" +
                                (item.getBookingtime().equals("1") ? "上午" : "下午")+item.getWorktime());
                        helper.setText(R.id.tv4, "可预约人数" + item.getNumber());
                        helper.setVisible(R.id.tv2, false);
                    }
                });
                lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        if (!(MySP.loadData(view.getContext(), "bookingDate", "") + "").equals("")) {
                            new android.app.AlertDialog.Builder(Appointment.this)
                                    .setItems(new CharSequence[]{"您已经有过历史预约了，如果要重新预约，请取消以前的预约"}, null)
                                    .setCancelable(true)
                                    .setPositiveButton("取消预约", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                           PreHandlerActivity.cancelYY(Appointment.this, new MyCallback() {
                                               @Override
                                               public void call(Object o) {
                                                   getYyList();
                                               }
                                           },type);
                                        }
                                    }).create().show();

                            return;
                        }
                        i = 1;
                        if (Constance.DEBUGTAG)
                            Log.i(Constance.DEBUG, "onItemClick: " + position);
                       final String bookingDate = data.get(position - 1).getDate();final String bookingTime = data.get(position - 1).getBookingtime();
                      final String username= MySP.loadData(Appointment.this, "username", "") + "";
                        new AlertDialog.Builder(Appointment.this)
                                .setTitle("您确认预约吗?")
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).setPositiveButton("确定预约", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ApiService.YYCommit(new RequestParams()
                                        .add("ywlx", type)
                                        .add("bookingDate", bookingDate)
                                        .add("bookingTime", bookingTime)
                                        .add("acc_code",username), new MyObserve<YYResult>(Appointment.this) {
                                    @Override
                                    public void onNext(YYResult value) {

                                        YYResult.DataBean data1 = value.getData();
                                        if (Constance.DEBUGTAG)
                                            Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "onNext: data1"+data1.toString());
                                        if(data1==null) {
                                            new MyToast(Appointment.this, "预约失败", 1);
                                            return;
                                        }
                                        MySP.saveData(Appointment.this, "bookingDate", data1.getBookingDate());
                                        MySP.saveData(Appointment.this, "bookingTime", bookingTime);
                                        MySP.saveData(Appointment.this, "workTime", data1.getWorktime());
                                        MySP.saveData(Appointment.this,"appcodename",data1.getMsg());
                                        new MyToast(Appointment.this,"预约成功",1);
                                        setResult(RESULT_OK);
                                        finish();
//                                        ActivityManager.get().removeAll();
//                                sendMessage(null,null,null,null);

                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        new MyToast(Appointment.this,"预约失败",1);
                                        if (Constance.DEBUGTAG)
                                            Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "onError: "+e.getMessage());
                                    }
                                });
                            }
                        }).create().show();

                    }
                });
                lv1.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                    @Override
                    public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                        getYyList();
                    }
                });
                lv1.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            } else {
                if (bean.getStatus().equals("1")) {

                    finish();
                    ActivityManager.get().removeAll();

//                    new MyToast(Appointment.this, "预约成功! \n时间：" + bookingDate +
//                            (bookingTime.equals("1") ? "上午" : "下午") +
//                            "\n预约序号：" + data.get(0).getCount(), 1);
//                    MySP.saveData(Appointment.this, "bookingDate", bookingDate);
//                    MySP.saveData(Appointment.this, "bookingTime", bookingTime);
//                    NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
//                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
//                            .setSmallIcon(R.mipmap.ic_launcher)
//                            .setContentTitle("福建住房公积金APP")
//                            .setContentText("您预约的时间是" + bookingDate + (bookingTime.equals("1") ? "上午" : "下午"))
//                            .setTicker("");
//                    Notification notification2 = mBuilder.build();
//                    manager.notify(1, notification2);
                    Appointment.this.finish();
                } else {
                    new MyToast(Appointment.this, "预约失败，预约名额已经不足，请尝试预约其他时间！", 1);
                    getYyList();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.btnBack,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                startActivity(new Intent(Appointment.this, CreditDocument.class));
                finish();
                break;
        }
    }


}
