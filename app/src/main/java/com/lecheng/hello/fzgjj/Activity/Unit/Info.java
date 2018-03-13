package com.lecheng.hello.fzgjj.Activity.Unit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.common.SysOSUtil;
import com.blankj.utilcode.util.SizeUtils;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Net.Api;
import com.lecheng.hello.fzgjj.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Info extends AppCompatActivity {


    @Bind(R.id.wv)
    WebView wv;
    @Bind(R.id.tvTime)
    TextView tvTime;
    @Bind(R.id.tv1)
    TextView tv1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unit_info);
        ButterKnife.bind(this);
        System.out.println("Info - unit_info");
        ActionBar frag = (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("详情");
        String title = getIntent().getStringExtra("title");
        String time = getIntent().getStringExtra("time");
        tv1.setText(title);
        tvTime.setText("发布时间:" + time.substring(2, 16));
        try {
            WebSettings settings = wv.getSettings();
            settings.setUseWideViewPort(true);//设定支持viewport
            settings.setTextZoom(280);
            settings.setMinimumFontSize(21);
            settings.setLoadWithOverviewMode(true);
            settings.setBuiltInZoomControls(true);
            settings.setSupportZoom(true);//设定支持缩放


            String content = getIntent().getStringExtra("info");
            String source = getIntent().getStringExtra("source");
            String content1=content.replace("line-height","what").replace("font-size","what2");
            if (Constance.DEBUGTAG)
                Log.i(Constance.DEBUG, "onCreate: " + "信息来源："+content1+ source);
            wv.loadDataWithBaseURL("about:blank",
                    content1 + (TextUtils.isEmpty(source) ? "" : ("信息来源：" + source)),
                    "text/html", "utf-8", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
