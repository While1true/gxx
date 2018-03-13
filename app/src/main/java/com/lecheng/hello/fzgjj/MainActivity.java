package com.lecheng.hello.fzgjj;

/**
 * @author zqy ; lecheng 复用于 2016-8-8
 * 图标包引用地址：
 * http://www.easyicon.net/iconsearch/iconset:Essential-Collection-icons/
 * 备用图标地址：
 * http://www.easyicon.net/1129199-Money_icon.html
 * <p>
 * 福州公积金官网,可以作为参考：http://www.fzzfgjj.com/
 * http://www.fzzfgjj.com/HomePage.aspx
 * 后台Git地址：http://10.0.200.231:8000/gjjAndroid.git
 * PC版公积金首页：http://10.0.110.149:8080/emr/pageFront/view
 **/

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.lecheng.hello.fzgjj.Activity.H2.Services;
import com.lecheng.hello.fzgjj.Activity.H3.ToolFragmnet;
import com.lecheng.hello.fzgjj.Activity.H4.MineFragment;
import com.lecheng.hello.fzgjj.Activity.ReUI.HomeFragment;
import com.lecheng.hello.fzgjj.Activity.Unit.Menu;
import com.lecheng.hello.fzgjj.Bean.UpdateBean;
import com.lecheng.hello.fzgjj.Interface.BackHandledInterface;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Utils.UpdateUtils;
import com.lecheng.hello.fzgjj.Widget.BackHandledFragment;
import com.lecheng.hello.fzgjj.Widget.MyCustomTabHost;

import RxWeb.MyObserve;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rxbus.BaseBean;

@Deprecated
public class MainActivity extends AppCompatActivity implements BackHandledInterface {
    public static final String TAG = "MainActivity";
    public static MainActivity instance = null;
    private static long lct = 0;//后退事件处理
    @Bind(R.id.container)
    LinearLayout container;
    @Bind(R.id.tvTitle)
    TextView tvTitle;
    private MyCustomTabHost mTabHost;
    private LayoutInflater mLayoutInflater;                                         //布局填充器
    private Class mFragmentArray[] = {HomeFragment.class, Services.class, ToolFragmnet.class, MineFragment.class};                                         //Fragment数组界面
    private int mImageArray[] = {R.drawable.style_ic_h1, R.drawable.style_ic_h2,
            R.drawable.style_ic_h3, R.drawable.style_ic_h4};                    //存放图片数组
    private String mTextArray[] = {"信息服务", "业务办理", "工具", "管理"};                  //选修卡文字
    private BackHandledFragment mBackHandedFragment;//支持fragment返回的参数
    public static final String PUSH_API_KEY = "tCKgzevZVL5FLBcDc8L7OREY";
//            "aCbeGGkCiPGtqjkxXn7yKMWN";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amain);
        ButterKnife.bind(MainActivity.this);
        instance = MainActivity.this;
        initView();
        ApiService.checkVersion(new MyObserve<BaseBean<UpdateBean>>() {
            @Override
            public void onNext(BaseBean<UpdateBean> value) {
                try {
                    if (value != null && value.getStatus() == 1 && Integer.parseInt(value.getData().getUpdateNumber()) > getPackageManager().getPackageInfo(getPackageName(), 0).versionCode) {
                        UpdateUtils.checkUpdate(MainActivity.this, value.getData());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    private void initView() {                                                       //初始化组件
        mLayoutInflater = LayoutInflater.from(this);
        mTabHost = (MyCustomTabHost) findViewById(android.R.id.tabhost);            //找到TabHost
        mTabHost.setup(this, getSupportFragmentManager(), R.id.fl1);
        int count = mFragmentArray.length;                                //得到fragment的个数
        for (int i = 0; i < count; i++) {
            TabSpec tabSpec = mTabHost.newTabSpec(mTextArray[i])// 给每个Tab按钮设置图标、文字和内容
                    .setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, mFragmentArray[i], null);  // 将Tab按钮添加进Tab选项卡中
        }

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                tvTitle.setText(tabId);
            }
        });

        mTabHost.onTabChanged(mTextArray[0]);

//        tvTitle.setText("信息服务");

        ApiService.load(this, null);

    }

    //给每个Tab按钮设置图标和文字
    private View getTabItemView(int index) {
        View view = mLayoutInflater.inflate(R.layout.unit_tabhost_itemview, null);
        View v = view.findViewById(R.id.imageview);
        v.setBackgroundResource(mImageArray[index]);
        TextView tv = (TextView) view.findViewById(R.id.textview);
        tv.setText(mTextArray[index]);
        return view;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void setSelectedFragment(BackHandledFragment selectedFragment) {
        this.mBackHandedFragment = selectedFragment;
    }

    @Override
    public void onBackPressed() {
        newBackPress();
    }

    private void newBackPress() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            if (System.currentTimeMillis() - lct < 1200) {
                finish();
            } else {
                new MyToast(this, "再按一次退出", Toast.LENGTH_SHORT);
                lct = System.currentTimeMillis();
            }
        }
    }


    /*//用于传值的参数//用于传值的参数//用于传值的参数//用于传值的参数//用于传值的参数//用于传值的参数*/
    private String dataStr;

    public String getStr1() {
        return dataStr;
    }

    public void setStr1(String str1) {
        this.dataStr = str1;
    }

    public void setBg1() {
        container.setBackgroundResource(R.color.colorGray);
    }

    public void setBg2() {
        container.setBackgroundResource(R.color.colorWhite);
    }

    @OnClick({R.id.iv_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_menu:
                Menu menu = new Menu(this);
                menu.showPopupWindow(view);
//                startActivity(new Intent(this, MoreInfo.class));
                break;
        }
    }

    public void check(int pager) {
        mTabHost.setCurrentTab(pager);
    }
}