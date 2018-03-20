package com.lecheng.hello.fzgjj.Activity.H3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.SizeUtils;
import com.ck.hello.nestrefreshlib.View.Adpater.Base.ItemHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Base.SimpleViewHolder;
import com.ck.hello.nestrefreshlib.View.Adpater.Impliment.SAdapter;
import com.lecheng.hello.fzgjj.Activity.Unit.MyBrowser;
import com.lecheng.hello.fzgjj.Net.Api;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Widget.SettingView;

import java.util.Arrays;
import java.util.List;

import RxWeb.BaseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import coms.kxjsj.refreshlayout_master.RefreshLayout;

@Deprecated
public class ToolFragmnet extends BaseFragment {

    @Bind(R.id.refreshLayout)
    RefreshLayout refreshLayout;
//     "网点查询",
//    , R.drawable.gj2
    private String[] array = {"政策法规","办事指南", "贷款额度测算",
            "贷款还款测算", "备案楼盘查询", "表格下载", "一至三十年月均\n还款金额简表"};
//    一至三十年月均还款金额简表
    private int[] arrImgRes = { R.drawable.gj1,
            R.drawable.gj3, R.drawable.gj4, R.drawable.gj5, R.drawable.gj6, R.drawable.gj8,
            R.drawable.gj7};


    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
                loadLazy();

    }

    @Override
    protected void loadLazy() {
        List<String> list = Arrays.asList(array);
        final RecyclerView recyclerView = refreshLayout.getmScroll();
        int i = SizeUtils.dp2px(15);
        refreshLayout.setPadding(i,0,i,0);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        final SAdapter sAdapter = new SAdapter<String>(list)
                .addType(R.layout.settingview, new ItemHolder<String>() {
                    @Override
                    public void onBind(SimpleViewHolder simpleViewHolder, String s, final int i) {
//                        int height = refreshLayout.getHeight();
//                        int eachHeight = height / 3> TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT,150,getResources().getDisplayMetrics())? (int) (150 * AdjustUtil.originalScreenScale) :height / 3;
//                        simpleViewHolder.itemView.getLayoutParams().height=eachHeight;
//                        LinearLayout linearLayout=simpleViewHolder.getView(R.id.root);
//                        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);
                        SettingView settingView=simpleViewHolder.getView(R.id.settingview);
                        settingView.setTitleText(s);
                        settingView.setTitledrawable(arrImgRes[i],0,0,0);
                        simpleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                switch (i) {
                                    case 0:
                                        startActivity(new Intent(getActivity(), PolicyDocs.class)
                                                .putExtra("gjz", "FLFG")
                                                .putExtra("gjz2", "法律法规"));
                                        break;
                                    case 1:
                                        startActivity(new Intent(getActivity(), GuideInfo.class));
                                        break;
                                    case 2:
                                        startActivity(new Intent(getActivity(), CalculateDepositeActivity.class));
                                        break;
                                    case 3:
                                        startActivity(new Intent(getActivity(), CalculateReturnActivity.class));
                                        break;
                                    case 4:
                                        startActivity(new Intent(getActivity(), BuildingsSearch.class));
                                        break;
                                    case 6:
                                        startActivity(new Intent(getActivity(), MyBrowser.class)
                                                .putExtra("web_url", Api.SIMPLE_SHEET_URL + "counter/wxGjjLilv?"));
                                        break;
                                    case 5:
                                        startActivity(new Intent(getActivity(), DocDownloads.class));
                                        break;

                                }
                            }
                        });
                    }

                    @Override
                    public boolean istype(String s, int i) {
                        return true;
                    }
                });
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(sAdapter);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home4;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
