package com.lecheng.hello.fzgjj.Activity.H3;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.lecheng.hello.fzgjj.Bean.CalculateBean;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Utils.RequestParams;

import java.util.Arrays;
import java.util.List;

import RxWeb.MyObserve;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalculateReturnActivity extends AppCompatActivity {

    @Bind(R.id.year_spinner)
    Spinner yearSpinner;
    @Bind(R.id.ze_editText)
    EditText zeEditText;
    @Bind(R.id.lv_spinner)
    Spinner lvSpinner;
    @Bind(R.id.radioGroup)
    RadioGroup radioGroup;


    int type = 0;//0:公积金 1：商业贷款
    @Bind(R.id.textView18)
    TextView textView18;
    @Bind(R.id.result_title)
    TextView resultTitle;
    @Bind(R.id.result_content)
    TextView resultContent;
    @Bind(R.id.hkze)
    TextView hkze;
    @Bind(R.id.zflx)
    TextView zflx;
    @Bind(R.id.sqfk)
    TextView sqfk;
    @Bind(R.id.dkys)
    TextView dkys;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.button2)
    Button button2;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_return);
        ButterKnife.bind(this);
        adapter1 = new ArrayAdapter<>(CalculateReturnActivity.this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.businessrate));
        adapter = new ArrayAdapter<>(CalculateReturnActivity.this, android.R.layout.simple_spinner_dropdown_item,
                Arrays.asList(new String[]{"按公积金贷款利率计算"}));
        lvSpinner.setAdapter(adapter);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.gjjk_radio) {
                    type = 0;
                    textView18.setText("公积金贷款利率：");
                    lvSpinner.setAdapter(adapter);
                } else {
                    type = 1;
                    textView18.setText("商业贷款利率：");

                    lvSpinner.setAdapter(adapter1);
                }

            }
        });
    }

    @OnClick({R.id.iv_back, R.id.button, R.id.button2})
    public void onViewClicked(View view) {
        KeyboardUtils.hideSoftInput(this);
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.button:
                doRepay(0);
                break;
            case R.id.button2:
                doRepay(1);
                break;
        }
    }

    /**
     * 0:等额本息 1：等额本金
     *
     * @param i
     */
    private void doRepay(final int i) {
        int year = yearSpinner.getSelectedItemPosition() + 1;
        String total = zeEditText.getText().toString();
        int loanPercentage = lvSpinner.getSelectedItemPosition();
        if (TextUtils.isEmpty(total)) {
            new MyToast(this, "请输入总金额", 1);
            return;
        }
        button.setSelected(i == 0 ? true : false);
        button2.setSelected(i == 0 ? false : true);

        RequestParams requestParams = new RequestParams();
        if(type==1) {
            requestParams.add("businessrate", loanPercentage);
            requestParams.add("gjjbal", total);
            requestParams.add("repaymentYears", year);
            requestParams.add("repaymentBal", total);

        }else{
            requestParams.add("gjjbal", total);
            requestParams.add("repaymentBal", total);
            requestParams.add("repaymentYears", year);
        }

        ApiService.CalculateRePay(requestParams, new MyObserve<CalculateBean>(this) {
            @Override
            public void onNext(CalculateBean bean) {

                String title = (type == 0 ? "公积金按" : "商业贷款按") + (i == 0 ? "等额本息\n计算结果：（元）" : "等额本金\n计算结果：（元）");
                resultTitle.setText(title);
                if (type == 0) {
                    hkze.setText("还款总额：" + (i == 0 ? bean.getGjjdebx().getHkze() : bean.getGjjdebj().getHkze()));
                    zflx.setText("支付利息：" + (i == 0 ? bean.getGjjdebx().getZflxk() : bean.getGjjdebj().getZflxk()));
                    sqfk.setText("首期付款：" + (i == 0 ? bean.getGjjdebx().getSqfk() : bean.getGjjdebj().getSqfk()));
                    dkys.setText("贷款月数：" + (i == 0 ? bean.getGjjdebx().getDkys() : bean.getGjjdebj().getDkys()));
                    List<String> strings = i == 0 ? bean.getGjjdebx().getYhje() : bean.getGjjdebj().getYhje();
                    StringBuilder builder = new StringBuilder();
                    for (String string : strings) {
                        builder.append(string + "\n");
                    }
                    resultContent.setText(builder.toString());
                } else {
                    hkze.setText("还款总额：" + (i == 0 ? bean.getSydebx().getHkze() : bean.getSydebj().getHkze()));
                    zflx.setText("支付利息：" + (i == 0 ? bean.getSydebx().getZflxk() : bean.getSydebj().getZflxk()));
                    sqfk.setText("首期付款：" + (i == 0 ? bean.getSydebx().getSqfk() : bean.getSydebj().getSqfk()));
                    dkys.setText("贷款月数：" + (i == 0 ? bean.getSydebx().getDkys() : bean.getSydebj().getDkys()));
                    List<String> strings = i == 0 ? bean.getSydebx().getYhje() : bean.getSydebj().getYhje();
                    StringBuilder builder = new StringBuilder();
                    for (String string : strings) {
                        builder.append(string + "\n");
                    }
                    resultContent.setText(builder.toString());
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.e(Constance.DEBUG, "onError: " + e.getMessage());

            }
        });

    }
}
