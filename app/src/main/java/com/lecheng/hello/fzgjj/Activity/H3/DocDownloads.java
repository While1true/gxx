package com.lecheng.hello.fzgjj.Activity.H3;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Adpt.Common.UnityAdapter;
import com.lecheng.hello.fzgjj.Adpt.Common.ViewHolder;
import com.lecheng.hello.fzgjj.Bean.BeanBgxzList;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.TbsFileReader.FileDisplayActivity;
import com.lecheng.hello.fzgjj.Utils.FileUtils;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Constance;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import RxWeb.GsonUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

public class DocDownloads extends AppCompatActivity implements IWSListener {
    String textUrl = "http://www.fjszgjj.com/upfiles/files/7A4BB2F8-E081-D90F-5D3169164EA75B5A.doc";
    @Bind(R.id.ptr)
    PullToRefreshListView ptr;
    ProgressDialog dialog = null;
    File filess=new File(Environment.getExternalStorageDirectory(),"gjjcache");

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home3f);
        ActionBar frag = (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("表格下载");
        ButterKnife.bind(this);
        httpPost();
    }

    private void httpPost() {
        String[] key = {};
        String[] value = {};
        new HttpGo().httpWebService(this, this, "bgxzList", key, value);
    }

    @Override
    public void onWebServiceSuccess(String s) {
        final BeanBgxzList bean = GsonUtil.GsonToBean(s, BeanBgxzList.class);
        if (bean == null)
            return;
        ptr.setAdapter(new UnityAdapter<BeanBgxzList.DataBean>(this, bean.getData(), R.layout.item3) {
            @Override
            public void convert(ViewHolder helper, BeanBgxzList.DataBean item, int position) {
                helper.setText(R.id.tv1, item.getFilename());
                helper.setText(R.id.tv2, item.getCreatedate());
                helper.setVisible(R.id.tv3, false);
                helper.setVisible(R.id.tv4, false);
            }
        });
        ptr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s1 = bean.getData().get(position-1).getFileurl();
                if (Constance.DEBUGTAG)
                    Log.i(Constance.DEBUG, "onItemClick: " + s1);

                downloadFile(s1,bean.getData().get(position-1).getFilename()+s1.substring(s1.lastIndexOf("."),s1.length()));
            }
        });
        ptr.getRefreshableView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String s1 = bean.getData().get(position-1).getFileurl();
                File file = new File(filess, bean.getData().get(position-1).getFilename()+s1.substring(s1.lastIndexOf("."),s1.length()));
                if(file.exists()){
                    FileUtils.send(DocDownloads.this,file);
                }
                return true;
            }
        });
    }

    @SuppressLint("NewApi")
    private void downloadFile(String textUrl, String name) {

        File file = new File(filess, name);
        if (dialog == null) {
            dialog = new ProgressDialog(DocDownloads.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setProgressNumberFormat("已下载：%1d kb/ 总进度：%2d kb");
            dialog.setCancelable(false);
        }
        if (watchFile(file)) {
            dialog.show();
            new MyToast(DocDownloads.this, "正在下载...", 0);
            OkHttpUtils.get()
                    .url(textUrl)
                    .build()
                    .execute(new FileCallBack(filess.getAbsolutePath(), name) {
                        @Override
                        public void inProgress(float progress, long total, int id) {
                            super.inProgress(progress, total, id);
                            dialog.setMax((int) total/1024);
                            dialog.setProgress((int) (progress * total/1024));
                        }

                        @Override
                        public void onError(Call call, Exception e, int id) {
                            new MyToast(DocDownloads.this, "下载失败", 1);
                            if (Constance.DEBUGTAG)
                                Log.i(Constance.DEBUG, "onError: ", e);
                            if (dialog != null)
                                dialog.dismiss();
                        }

                        @Override
                        public void onResponse(File response, int id) {
                            if (Constance.DEBUGTAG)
                                Log.i(Constance.DEBUG + "--" + getClass().getSimpleName() + "--", "onResponse: "+response.getAbsolutePath());
                            new MyToast(DocDownloads.this, "已下载:" + response.getName(), 1);
                            dialog.dismiss();
                            watchFile(response);
                        }
                    });
        }
    }

    private boolean watchFile(File file) {
        if (file.exists()) {
            if(!FileDisplayActivity.show(DocDownloads.this,file.getAbsolutePath())){
               FileUtils.openBySystem(this,file);
            }
            return false;
        }else{
            File parentFile = file.getParentFile();
            if(!parentFile.exists())
                parentFile.mkdirs();
        }

        return true;
    }


}
