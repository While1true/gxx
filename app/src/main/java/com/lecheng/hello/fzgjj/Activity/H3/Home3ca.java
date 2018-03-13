package com.lecheng.hello.fzgjj.Activity.H3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Adpt.Common.UnityAdapter;
import com.lecheng.hello.fzgjj.Adpt.Common.ViewHolder;
import com.lecheng.hello.fzgjj.Bean.BeanBsznList;
import com.lecheng.hello.fzgjj.Net.HttpGo;
import com.lecheng.hello.fzgjj.Interface.IWSListener;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import RxWeb.GsonUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

@Deprecated
public class Home3ca extends AppCompatActivity implements IWSListener {
    @Bind(R.id.ptr)
    PullToRefreshListView ptr;
    private HttpGo httpGo = new HttpGo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home3ca);
        ButterKnife.bind(this);
        ActionBar frag= (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("办事指南");
        String code = getIntent().getStringExtra("code");
        new MyToast(this,code,1);
        String[] key = {"code","page","rows",};
        String[] value = {code,"1","10",};
        httpGo.httpWebService(this, this, "bsznList", key, value);
    }

    @Override
    public void onWebServiceSuccess(String s) {
        try {
            final BeanBsznList bean = GsonUtil.GsonToBean(s, BeanBsznList.class);
            ptr.setAdapter(new UnityAdapter<BeanBsznList.DataBean>(this, bean.getData(), R.layout.item3) {
                @Override
                public void convert(ViewHolder helper, BeanBsznList.DataBean item, int position) {
                    helper.setText(R.id.tv1, item.getTitle());
                    helper.setVisible(R.id.tv2, false);
                    helper.setVisible(R.id.tv3, false);
                    helper.setVisible(R.id.tv4, false);  }
            });
            ptr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    startActivity(new Intent(PolicyDocs.this, Info.class)
//                            .putExtra("info", bean.getData().get(position).getContent()));
                }
            });
        } catch (Exception e) {
            new MyToast(this, "信息获取失败，请重试", 1);
        }
    }
}
