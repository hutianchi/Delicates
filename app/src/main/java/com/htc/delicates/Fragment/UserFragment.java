package com.htc.delicates.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.htc.delicates.Activity.CommonProblemActivity;
import com.htc.delicates.Activity.CouponActivity;
import com.htc.delicates.Activity.FeedbackActivity;
import com.htc.delicates.Activity.InviteActivity;
import com.htc.delicates.Activity.MessageActivity;
import com.htc.delicates.Activity.MyWalletActivity;
import com.htc.delicates.Activity.PersonalActivity;
import com.htc.delicates.Activity.SettingsActivity;
import com.htc.delicates.Activity.ToBeMemberActivity;
import com.htc.delicates.MyView.VerificationCodeView;
import com.htc.delicates.R;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class UserFragment extends Fragment implements View.OnClickListener{

    RelativeLayout login;

    TextView userName;

    TextView memberLevel;

    Button toBeMember;

    TextView wallet;

    TextView coupon;

    TextView settings;

    TextView invite;

    TextView personal;

    TextView common_problem;

    TextView feedback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        login = (RelativeLayout) view.findViewById(R.id.login);
        userName = (TextView) view.findViewById(R.id.user_name);
        memberLevel = (TextView) view.findViewById(R.id.member_level);
        wallet = (TextView) view.findViewById(R.id.wallet);
        coupon = (TextView) view.findViewById(R.id.coupon);
        settings = (TextView) view.findViewById(R.id.settings);
        invite = (TextView) view.findViewById(R.id.invite);
        personal = (TextView) view.findViewById(R.id.personal);
        common_problem = (TextView) view.findViewById(R.id.common_problem);
        feedback = (TextView) view.findViewById(R.id.feedback);
        toBeMember = (Button) view.findViewById(R.id.to_be_member);
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar_user);
        mToolbar.inflateMenu(R.menu.menu_user);
        mToolbar.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.item_message:
                    Intent intent = new Intent(getActivity(), MessageActivity.class);
                    startActivity(intent);
                    break;
            }
            return true;
        });
        login.setOnClickListener(this);
        toBeMember.setOnClickListener(this);
        invite.setOnClickListener(this);
        personal.setOnClickListener(this);
        common_problem.setOnClickListener(this);
        feedback.setOnClickListener(this);
        settings.setOnClickListener(this);
        wallet.setOnClickListener(this);
        coupon.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                showLoginPopWindow();
                break;
            case R.id.to_be_member:
                Intent intent0 = new Intent(getActivity(), ToBeMemberActivity.class);
                startActivity(intent0);
                break;
            case R.id.invite:
                Intent intent1 = new Intent(getActivity(), InviteActivity.class);
                startActivity(intent1);
                break;
            case R.id.personal:
                Intent intent2 = new Intent(getActivity(), PersonalActivity.class);
                startActivity(intent2);
                break;
            case R.id.common_problem:
                Intent intent3 = new Intent(getActivity(), CommonProblemActivity.class);
                startActivity(intent3);
                break;
            case R.id.feedback:
                Intent intent4 = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(intent4);
                break;
            case R.id.settings:
                Intent intent5 = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent5);
                break;
            case R.id.wallet:
                Intent intent6 = new Intent(getActivity(), MyWalletActivity.class);
                startActivity(intent6);
                break;
            case R.id.coupon:
                Intent intent7 = new Intent(getActivity(), CouponActivity.class);
                startActivity(intent7);
                break;
        }
    }

    private void showLoginPopWindow(){
        View popView = View.inflate(getActivity(),R.layout.popupwindow_verify,null);
        VerificationCodeView verificationcodeview = popView.findViewById(R.id.verificationcodeview);
        EditText phoneNum = (EditText) popView.findViewById(R.id.phone_num);
        TextView text1 = (TextView) popView.findViewById(R.id.text1);
        TextView text2 = (TextView) popView.findViewById(R.id.text2);
        TextView resend = (TextView) popView.findViewById(R.id.text3);
        TextView back = (TextView) popView.findViewById(R.id.text4);
        TextView smsSend = (TextView) popView.findViewById(R.id.sms_send);

        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels*4/5;
        int height = getResources().getDisplayMetrics().heightPixels*1/3;

        final PopupWindow popupWindow = new PopupWindow(popView,weight,height);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);

        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
            lp.alpha = 1.0f;
            getActivity().getWindow().setAttributes(lp);
        });
        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.5f;
        getActivity().getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.CENTER,0,-200);

        verificationcodeview.setOnCodeFinishListener(content ->{
                    // 提交验证码，其中的code表示验证码，如“1357”
                    String phone = phoneNum.getText().toString();
                    SMSSDK.submitVerificationCode("86", phone, content);
                });

        verificationcodeview.setVisibility(View.GONE);

        text2.setVisibility(View.GONE);
        resend.setVisibility(View.GONE);
        back.setVisibility(View.GONE);

        final CountDownTimer timer = new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                resend.setText(millisUntilFinished/1000 + "秒");
            }

            @Override
            public void onFinish() {
                resend.setEnabled(true);
                resend.setText("重新发送");
            }
        };

        EventHandler eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // afterEvent会在子线程被调用，因此如果后续有UI相关操作，需要将数据发送到UI线程
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                new Handler(Looper.getMainLooper(), msg1 -> {
                    int event1 = msg1.arg1;
                    int result1 = msg1.arg2;
                    Object data1 = msg1.obj;
                    if (event1 == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        if (result1 == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理成功得到验证码的结果
                            // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                            timer.start();
                            phoneNum.setVisibility(View.GONE);
                            smsSend.setVisibility(View.GONE);
                            text1.setVisibility(View.GONE);
                            verificationcodeview.setVisibility(View.VISIBLE);
                            String phone = phoneNum.getText().toString();
                            text2.setText(phone);

                            text2.setVisibility(View.VISIBLE);
                            resend.setVisibility(View.VISIBLE);
                            back.setVisibility(View.VISIBLE);
                        } else {
                            // TODO 处理错误的结果
                            ((Throwable) data1).printStackTrace();
                        }
                    } else if (event1 == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        if (result1 == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理验证码验证通过的结果
                            SMSSDK.unregisterAllEventHandler();
                            popupWindow.dismiss();
                            Toast.makeText(getActivity(), "登录成功!", Toast.LENGTH_SHORT).show();

                            //
                        } else {
                            // TODO 处理错误的结果
                            Toast.makeText(getActivity(), "验证码输入错误!", Toast.LENGTH_SHORT).show();
                            ((Throwable) data1).printStackTrace();
                        }
                    }
                    // TODO 其他接口的返回结果也类似，根据event判断当前数据属于哪个接口
                    return false;
                }).sendMessage(msg);
            }
        };
// 注册一个事件回调，用于处理SMSSDK接口请求的结果
        SMSSDK.registerEventHandler(eventHandler);

        smsSend.setOnClickListener(v1 -> {
            String phone = phoneNum.getText().toString();
            if (phone.length()==11&&phone.charAt(0)=='1'){
                // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
                SMSSDK.getVerificationCode("86", phone);
            }else {
                Toast.makeText(getActivity(), "请输入正确的手机号码！",Toast.LENGTH_SHORT).show();
            }

        });
        resend.setOnClickListener(v1 -> {
            if ("重新发送".equals(resend.getText())){
                String phone = phoneNum.getText().toString();
                SMSSDK.getVerificationCode("86", phone);
            }
        });
        back.setOnClickListener(v1 -> {
            phoneNum.setVisibility(View.VISIBLE);
            smsSend.setVisibility(View.VISIBLE);
            text1.setVisibility(View.VISIBLE);
            verificationcodeview.setVisibility(View.GONE);
            text2.setVisibility(View.GONE);
            resend.setVisibility(View.GONE);
            back.setVisibility(View.GONE);
        });
    }
}
