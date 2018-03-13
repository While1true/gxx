package com.lecheng.hello.fzgjj.Activity.H2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Adpt.Common.UnityAdapter;
import com.lecheng.hello.fzgjj.Adpt.Common.ViewHolder;
import com.lecheng.hello.fzgjj.Bean.TypeBean;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.ActivityManager;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;

import java.util.List;

import RxWeb.BaseAppCompatActivity;
import RxWeb.MyObserve;
import butterknife.Bind;
import butterknife.ButterKnife;

public class Preengage extends BaseAppCompatActivity implements IWSListener {

    @Bind(R.id.sp1)
    GridView sp1;
    @Bind(R.id.tvMsg)
    TextView tvMsg;

    private String bookingDate, bookingTime;

    @Override
    public void onWebServiceSuccess(String s) {
        try {
            if (!s.equals(null)) {
                new MyToast(Preengage.this, "取消预约成功！", 0);
                MySP.saveData(Preengage.this, "bookingDate", "");
                MySP.saveData(Preengage.this, "bookingTime", "");
                tvMsg.setText("");
                bookingDate = "";
                bookingTime = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.get().remove();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.home2b);
        ButterKnife.bind(this);
        ActionBar frag= (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("1.请选择提取的类型");
        bookingDate = MySP.loadData(this, "bookingDate", "") + "";
        bookingTime = MySP.loadData(this, "bookingTime", "") + "";
        String appcode = MySP.loadData(this, "appcode", "") + "";
        String appcodename=MySP.loadData(this, "appcodename", "") + "";
        if (!bookingDate.equals(""))
            tvMsg.setText("您已预约:" + bookingDate + (bookingTime.equals("1") ? "上午" : "下午")+"\n办理"+appcodename+"业务"+"\n预约码:"+appcode);

        ApiService.getTQTYPE(new MyObserve<List<TypeBean>>(this) {
            @Override
            public void onNext(final List<TypeBean> list) {
                if(list==null)
                    return;
                sp1.setAdapter(new UnityAdapter<TypeBean>(Preengage.this, list, R.layout.item8) {
                    @Override
                    public void convert(ViewHolder helper, TypeBean item, int position) {
                        helper.setText(R.id.tv1, item.getCode_name());
                    }
                });
                sp1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (!bookingDate.equals("")) {
                            new AlertDialog.Builder(Preengage.this)
                                    .setItems(new CharSequence[]{"您已经有过历史预约了，如果要重新预约，请取消以前的预约"}, null)
                                    .setCancelable(true)
                                    .setPositiveButton("取消以前的预约", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            String[] k = {"bookingDate", "bookingTime", "acc_code",};
                                            String[] v = {bookingDate, bookingTime,
                                                    MySP.loadData(Preengage.this, "username", "") + "",};
                                            new HttpGo().httpWebService(Preengage.this, Preengage.this, "yyCancel", k, v);
                                        }
                                    }).create().show();
                        } else {
                            MySP.saveData(Preengage.this, "h2b_selection", list.get(position).getCode_id());
                            startActivity(new Intent(Preengage.this, PersonalInfo.class));
                            ActivityManager.get().add(Preengage.this);
                        }
                    }
                });

            }

            @Override
            public void onError(Throwable e) {
                Log.e("", "onError: ", e);
            }
        });

    }
}
