package com.lecheng.hello.fzgjj.Activity.H2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ck.hello.nestrefreshlib.View.Adpater.SLoading;
import com.lecheng.hello.fzgjj.Activity.Unit.ActionBar;
import com.lecheng.hello.fzgjj.Adpt.Common.UnityAdapter;
import com.lecheng.hello.fzgjj.Adpt.Common.ViewHolder;
import com.lecheng.hello.fzgjj.Bean.TQInfo;
import com.lecheng.hello.fzgjj.Constance;
import com.lecheng.hello.fzgjj.Net.ApiService;
import com.lecheng.hello.fzgjj.R;
import com.lecheng.hello.fzgjj.Utils.ActivityManager;
import com.lecheng.hello.fzgjj.Utils.MySP;
import com.lecheng.hello.fzgjj.Utils.MyToast;
import com.lecheng.hello.fzgjj.Utils.RequestParams;

import java.util.ArrayList;
import java.util.List;

import RxWeb.MyObserve;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CreditDocument extends AppCompatActivity {
    @Bind(R.id.lv1)
    ListView lv1;
    @Bind(R.id.check1)
    TextView check1;
    @Bind(R.id.check2)
    TextView check2;
    @Bind(R.id.choose)
    LinearLayout choose;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.notice)
    TextView notice;
    ActionBar frag;
    @Bind(R.id.ll)
    LinearLayout ll;
    private int checked = 0;
//    private String[] docs = {
//            "[" +
//                    "{" +
//                    "\"name\": \"购买一手房所需材料：\"," +
//                    "\"date\": [" +
//                    "\"购房合同原件和复印件\"," +
//                    "\"房屋预告登记证（不动产登记证明）原件和复印件\"," +
//                    "\"购房首付款发票原件和复印件\"," +
//                    "\"身份证原件和复印件\"," +
//                    "\"结婚证原件和复印件（单身不必提供）\"" +
//                    "]" +
//                    "}," +
//                    "{" +
//                    "\"name\": \"购买二手房所需材料：\"," +
//                    "\"date\": [" +
//                    "\"产权证(不动产登记证)原件和复印件\"," +
//                    "\"增值税普通发票原件和复印件\"," +
//                    "\"身份证原件和复印件\"," +
//                    "\"结婚证原件和复印件（单身不必提供）\"" +
//                    "]" +
//                    "}" +
//                    "]",
//
//            "[" +
//                    "{" +
//                    "\"name\": \"在城市规划区内：\"," +
//                    "\"date\": [" +
//                    "\"原产权证原件和复印件\"," +
//                    "\"建设用地规划许可证原件和复印件\"," +
//                    "\"国有土地使用证原件和复印件\"," +
//                    "\"建设工程规划许可证原件和复印件\"," +
//                    "\"工程预（决）算书原件和复印件\"," +
//                    "\"身份证原件和复印件\"," +
//                    "\"结婚证原件和复印件（单身不必提供）\"" +
//                    "]" +
//                    "}," +
//                    "{" +
//                    "\"name\": \"在城市规划区外：\"," +
//                    "\"date\": [" +
//                    "\"建设用地批准书或集体土地建设用地使用证原件和复印件\"," +
//                    "\"村镇个人住宅建设许可证原件和复印件\"," +
//                    "\"工程预（决）算书原件和复印件\"," +
//                    "\"身份证原件和复印件\"," +
//                    "\"结婚证原件和复印件（单身不必提供）\"" +
//                    "]" +
//                    "}" +
//                    "]",//在城市规划区内
//
//            "[" +
//                    "{" +
//                    "\"name\":\"省直中心纯公积金贷款：\"," +
//                    "\"date\":[" +
//                    "\"银行购房借款合同原件\"," +
//                    "\"身份证原件和复印件\"," +
//                    "\"结婚证原件和复印件（单身不必提供）\"" +
//                    "]" +
//                    "}," +
//                    "{" +
//                    "\"name\": \"其他贷款类型：\"," +
//                    "\"date\": [" +
//                    "\"银行购房借款合同、抵押合同、授信合同(含借款借据或转账凭证)原件和复印件\"," +
//                    "\"银行出具的现时贷款对账单原件或合同约定的还款存折明细原件和复印件\"," +
//                    "\"二手房贷款另须提供产权证原件和复印件\"," +
//                    "\"身份证原件和复印件\"," +
//                    "\"必要时根据要求提供人民银行出具的个人征信查询记录\"," +
//                    "\"结婚证原件和复印件（单身不必提供）\"" +
//                    "]" +
//                    "}" +
//                    "]",
//
//            "[" +
//                    "{" +
//                    "\"name\": \"夫妻双方无房且在福州市区租房\n公租房\"," +
//                    "\"date\": [" +
//                    "\"公租房有效合同原件和复印件\"," +
//                    "\"公租房租金发票原件和复印件\"," +
//                    "\"身份证原件和复印件\"," +
//                    "\"结婚证原件和复印件\"" +
//                    "]" +
//                    "}," +
//                    "{" +
//                    "\"name\": \"夫妻双方无房且在福州市区租房\n非公租房\"," +
//                    "\"date\": [" +
//                    "\"福州市房地产交易登记中心档案馆出具的夫妻无房证明原件\"," +
//                    "\"份证原件和复印件\"," +
//                    "\"结婚证原件和复印件\"" +
//                    "]" +
//                    "}" +
//                    "]",
//
//            "[" +
//                    "{" +
//                    "\"name\": \"\"," +
//                    "\"date\": [" +
//                    "\"拆迁安置协议书（含决算书）原件和复印件\"," +
//                    "\"交款发票原件和复印件\"," +
//                    "\"身份证原件和复印件\"," +
//                    "\"结婚证原件和复印件（单身不必提供）\"" +
//                    "]" +
//                    "}]",
//            "[" +
//                    "{" +
//                    "\"name\": \"\"," +
//                    "\"date\": [" +
//                    "\"离退休证（或批准文件）原件和复印件\"," +
//                    "\"身份证原件和复印件\"" +
//                    "]" +
//                    "}]",
//            "[" +
//                    "{" +
//                    "\"name\": \"\"," +
//                    "\"date\": [" +
//                    "\"定居地护照或定居地身份证原件和复印件\"," +
//                    "\"户口注销证明或身份证注销证明原件和复印件\"" +
//                    "]" +
//                    "}]",
//
//            "[" +
//                    "{" +
//                    "\"name\": \"\"," +
//                    "\"date\": [" +
//                    "\"工作调动证明文件（或异地劳动合同）原件和复印件\"," +
//                    "\"身份证原件和复印件\"" +
//                    "]" +
//                    "}]",
//
//            "[" +
//                    "{" +
//                    "\"name\": \"\"," +
//                    "\"date\": [" +
//                    "\"失业证或解除劳动关系证明原件和复印件\"," +
//                    "\"身份证原件和复印件。办理时需另签一份“两年内不得以同一理由再次申请提取”的承诺书\"" +
//                    "]" +
//                    "}]",
//
//            "[" +
//                    "{" +
//                    "\"name\": \"\"," +
//                    "\"date\": [" +
//                    "\"死亡证明原件和复印件\"," +
//                    "\"申请人身份证原件和复印件\"," +
//                    "\"申请人和死亡人关系证明原件和复印件\"" +
//                    "]" +
//                    "}]",
//
//            "[" +
//                    "{" +
//                    "\"name\": \"\"," +
//                    "\"date\": [" +
//                    "\"县级以上医院特殊病症审批表（或病历）原件和复印件\"," +
//                    "\"医保中心特殊病症确认表原件和复印件\"," +
//                    "\"医院出具的医疗费用等有效票据原件和复印件\"," +
//                    "\"夫妻双方单位出具收入及家庭生活严重困难证明原件\"," +
//                    "\"申请人身份证原件和复印件\"," +
//                    "\"亲属患病的亲属关系证明原件和复印件\"" +
//                    "]" +
//                    "}]",
//
//            "[" +
//                    "{" +
//                    "\"name\": \"\"," +
//                    "\"date\": [" +
//                    "\"劳动和社会保障部门出具的部分或者全部丧失劳动能力鉴定书原件和复印件\"," +
//                    "\"夫妻双方单位出具家庭生活严重困难证明原件\"," +
//                    "\"有效身份证原件和复印件\"," +
//                    "\"结婚证原件和复印件（单身不必提供）\"" +
//                    "]" +
//                    "}]",
//
//            "[" +
//                    "{" +
//                    "\"name\": \"\"," +
//                    "\"date\": [" +
//                    "\"民政部门出具的城镇最低生活保障证明原件和复印件\"," +
//                    "\"申请人身份证原件和复印件\"," +
//                    "\"结婚证原件和复印件（单身不必提供）\"" +
//                    "]" +
//                    "}]",
//
//            "[" +
//                    "{" +
//                    "\"name\": \"\"," +
//                    "\"date\": [" +
//                    "\"乡、镇、街道以上民政部门或公安机关出具的遭遇自然灾害或突发事件、地点、损失情况证明原件和复印件\"," +
//                    "\"夫妻双方单位出具收入及家庭生活严重困难证明原件\"," +
//                    "\"申请人身份证原件和复印件\"," +
//                    "\"结婚证原件和复印件\"" +
//                    "]" +
//                    "}]"};


    private UnityAdapter<String> adapter;

    private List<String> checkList = new ArrayList<>(8);
    private List<String> checkList2 = new ArrayList<>(8);
    List<TQInfo> infolist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home2bc);
        ButterKnife.bind(this);
      final SLoading sLoading= (SLoading) findViewById(R.id.sloading);
        sLoading.startAnimator();
        frag = (ActionBar) getFragmentManager().findFragmentById(R.id.frag);
        frag.setTitle("4.办理所需材料");
        int selection = (int) MySP.loadData(this, "h2b_selection", 0);
        ApiService.getTQInfo(new RequestParams().add("code_id", selection), new MyObserve<List<TQInfo>>(this) {
            @Override
            public void onNext(List<TQInfo> infos) {
                if (infos == null || infos.isEmpty())
                    return;

                sLoading.stopAnimator();
                ((ViewGroup)getWindow().getDecorView().getRootView()).removeView(sLoading);

                infolist=infos;
                check1.setSelected(true);
                if (infos.size() == 2) {
                    choose.setVisibility(View.VISIBLE);
                    check1.setText(infos.get(0).getParent_name());
                    check2.setText(infos.get(1).getParent_name());
                }


                name.setText(infos.get(0).getParent_name() + "所需办理材料");
                notice.setText(infos.get(0).getAttention());
                List<String> condition = infos.get(0).getCondition();
                adapter = new UnityAdapter<String>(CreditDocument.this, condition, R.layout.item0b) {

                    @Override
                    public void convert(ViewHolder helper, final String item, int position) {
                        helper.setCbtext(R.id.check, item);
                        final CheckBox cb = helper.getView(R.id.check);
                        //勾选记录
                        setChecked(checked == 0 ? checkList : checkList2, item, cb);
                        cb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (cb.isChecked()) {
                                    add(checked == 0 ? checkList : checkList2, item);
                                } else {
                                    remove(checked == 0 ? checkList : checkList2, item);


                                }
                            }
                        });
                    }
                };
                lv1.setAdapter(adapter);

            }

            @Override
            public void onError(Throwable e) {
                Log.e(Constance.TAG, "onError: ", e);
            }
        });

    }

    private void setChecked(List<String> checkList, String item, CheckBox cb) {

        if (checkList.contains(item))
            cb.setChecked(true);
        else
            cb.setChecked(false);

    }

    private void add(List<String> checkList, String item) {
        if (!checkList.contains(item))
            checkList.add(item);

    }

    private void remove(List<String> checkList, String item) {
        if (checkList.contains(item))
            checkList.remove(item);
    }


    @OnClick({R.id.btnBack, R.id.btnNext})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnNext:
                if (checked == 0) {
                    int i = check(checkList);

                    if (checkList.size() < infolist.get(0).getCondition().size() - i) {
                        new MyToast(this, "请在对应的材料前打钩", 1);
                        return;
                    }
                } else {
                    int i = check(checkList2);

                    if (checkList2.size() < infolist.get(1).getCondition().size() - i) {
                        new MyToast(this, "请在对应的材料前打钩", 1);
                        return;
                    }
                }
                Intent intent = new Intent(CreditDocument.this, Appointment.class);
                intent.putExtra("index",infolist.get(checked).getCode_id());
                startActivity(intent);
                ActivityManager.get().add(CreditDocument.this);
//                new AlertDialog.Builder(this)
//                        .setItems(new CharSequence[]{"请确认材料"}, null)
//                        .setCancelable(true)
//                        .setPositiveButton("是，我已确认", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                startActivity(new Intent(CreditDocument.this, Appointment.class));
//                                ActivityManager.get().add(CreditDocument.this);
//                            }
//                        }).setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        try {
//                            Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
//                            field.setAccessible(true);
//                            field.set(dialog, true);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).create().show();
                break;
        }
    }

    /**
     * 走后一想是否为不必提供
     *
     * @param a
     * @return
     */
    private int check(List<String> a) {
        int i = 0;
        List<String> condition = infolist.get(0).getCondition();
        String s = condition.get(condition.size() - 1);
        if (s.contains("不必提供")) {
            i = 1;
            for (String s1 : a) {
                if (s1.contains("不必提供")) {
                    return 0;
                }
            }
        }
        return i;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.get().remove();
    }

    @OnClick({R.id.check1, R.id.check2, R.id.choose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.check1:
                if (checked != 0) {
                    checked = 0;
                    check1.setSelected(true);
                    check2.setSelected(false);
                    name.setText(infolist.get(0).getParent_name() + "所需办理材料");
                    adapter.setList(infolist.get(0).getCondition());
                }
                break;
            case R.id.check2:
                if (checked != 1) {
                    check2.setSelected(true);
                    check1.setSelected(false);
                    checked = 1;
                    name.setText(infolist.get(1).getParent_name() + "所需办理材料");
                    adapter.setList(infolist.get(1).getCondition());
                }
                break;
            case R.id.choose:
                break;
        }
    }
}
