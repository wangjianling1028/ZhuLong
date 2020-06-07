package com.jiyun.zhulong.design;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.jiyun.zhulong.R;
import com.jiyun.zhulong.mypackage.MyTextWatcher;
import com.yiyatech.utils.ext.ToastUtils;
import com.yiyatech.utils.newAdd.RegexUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *    ：      --
 * 创建于： 2020/6/3 17:26
 *    邮箱：1750827655@qq.com
 */
public class LoginView extends RelativeLayout {

    private final boolean mIsMoreType;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_security)
    TextView tvSecurity;
    @BindView(R.id.et_account_username)
    EditText etAccountUsername;
    @BindView(R.id.et_account_password)
    EditText etAccountPassword;
    @BindView(R.id.tv_security_areacode)
    TextView tvSecurityAreacode;
    @BindView(R.id.et_security_phone)
    EditText etSecurityPhone;
    @BindView(R.id.et_security_authcode)
    EditText etSecurityAuthcode;
    @BindView(R.id.tv_security_get_authcode)
    public TextView tvSecurityGetAuthcode;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.view_account)
    View viewAccount;
    @BindView(R.id.view_security)
    View viewSecurity;
    @BindView(R.id.ll_security_top)
    LinearLayout llSecurityTop;
    @BindView(R.id.ll_security_bottom)
    LinearLayout llSecurityBottom;
    @BindView(R.id.img_account_delete)
    ImageView imgAccountDelete;
    @BindView(R.id.ll_account_top)
    LinearLayout llAccountTop;
    @BindView(R.id.img_account_look)
    ImageView imgAccountLook;
    @BindView(R.id.ll_account_bottom)
    LinearLayout llAccountBottom;
    private Context mContext;
    public final int ACCOUNT_TYPE = 1, SECURITY_TYPE = 2;
    public int mCurrentLoginType = ACCOUNT_TYPE;

    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.login_child_layout, this);
        ButterKnife.bind(this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoginView, 0, 0);
        mIsMoreType = typedArray.getBoolean(R.styleable.LoginView_isMoretype, true);
        initView();
        if (!mIsMoreType) {
            tvAccount.setVisibility(GONE);
            tvSecurity.setVisibility(GONE);
            viewAccount.setVisibility(GONE);
            viewSecurity.setVisibility(GONE);
        }
    }

    private void initView() {
        btLogin.setEnabled(false);
        etAccountUsername.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onMyTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0)
                    imgAccountDelete.setVisibility(VISIBLE);
                else imgAccountDelete.setVisibility(INVISIBLE);
            }
        });
        etAccountPassword.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onMyTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 5 && !TextUtils.isEmpty(etAccountUsername.getText().toString().trim()))
                    btLogin.setEnabled(true);
                else btLogin.setEnabled(false);
                if (s.length() == 0)
                    imgAccountLook.setVisibility(INVISIBLE);
                else imgAccountLook.setVisibility(VISIBLE);
            }
        });
        etSecurityAuthcode.addTextChangedListener(new MyTextWatcher() {
            @Override
            public void onMyTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 5 && RegexUtil.isPhone(etSecurityPhone.getText().toString().trim()))
                    btLogin.setEnabled(true);
                else btLogin.setEnabled(false);
            }
        });
    }

    @OnClick({R.id.tv_account, R.id.tv_security, R.id.tv_security_get_authcode, R.id.bt_login, R.id.img_account_delete, R.id.img_account_look})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_account:
                if (mCurrentLoginType != ACCOUNT_TYPE) {
                    tvAccount.setTextColor(ContextCompat.getColor(mContext, R.color.red423a));
                    tvSecurity.setTextColor(ContextCompat.getColor(mContext, R.color.color_gray_text));
                    viewAccount.setVisibility(VISIBLE);
                    viewSecurity.setVisibility(INVISIBLE);
                    etAccountUsername.setVisibility(VISIBLE);
                    etAccountPassword.setVisibility(VISIBLE);
                    llSecurityTop.setVisibility(INVISIBLE);
                    llSecurityBottom.setVisibility(INVISIBLE);
                    mCurrentLoginType = ACCOUNT_TYPE;
                }
                break;
            case R.id.tv_security:
                if (mCurrentLoginType != SECURITY_TYPE) {
                    tvSecurity.setTextColor(ContextCompat.getColor(mContext, R.color.red423a));
                    tvAccount.setTextColor(ContextCompat.getColor(mContext, R.color.color_gray_text));
                    viewSecurity.setVisibility(VISIBLE);
                    viewAccount.setVisibility(INVISIBLE);
                    etAccountUsername.setVisibility(INVISIBLE);
                    etAccountPassword.setVisibility(INVISIBLE);
                    llSecurityTop.setVisibility(VISIBLE);
                    llSecurityBottom.setVisibility(VISIBLE);
                    mCurrentLoginType = SECURITY_TYPE;
                }
                break;
            case R.id.tv_security_get_authcode:
                if (TextUtils.isEmpty(etSecurityPhone.getText().toString())) {
                    ToastUtils.show(mContext, "手机号码为空");
                    return;
                }
                if (!RegexUtil.isPhone(etSecurityPhone.getText().toString().trim())) {
                    ToastUtils.show(mContext, "手机号格式错误");
                    return;
                }
                if (mLoginViewCallBack != null)
                    mLoginViewCallBack.sendVerifyCode(tvSecurityAreacode.getText().toString() + etSecurityPhone.getText().toString().trim());
                break;
            case R.id.bt_login:
                String userName = "", passWord = "";
                if (mCurrentLoginType == ACCOUNT_TYPE) {
                    userName = etAccountUsername.getText().toString().trim();
                    passWord = etAccountPassword.getText().toString().trim();
                } else {
                    userName = etSecurityPhone.getText().toString().trim();
                    passWord = etSecurityAuthcode.getText().toString().trim();
                }
                if (mLoginViewCallBack != null)
                    mLoginViewCallBack.loginPress(mCurrentLoginType, userName, passWord);
                break;
            case R.id.img_account_delete:
                etAccountUsername.setText(null);
                break;
            case R.id.img_account_look:
                int inputType = etAccountPassword.getInputType();
                if (inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    etAccountPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    imgAccountLook.setSelected(false);
                } else {
                    etAccountPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    imgAccountLook.setSelected(true);
                }
                break;
        }
    }

    private LoginViewCallBack mLoginViewCallBack;

    public void setLoginViewCallBack(LoginViewCallBack pLoginViewCallBack) {
        mLoginViewCallBack = pLoginViewCallBack;
    }

    public interface LoginViewCallBack {
        void sendVerifyCode(String phoneNum);

        void loginPress(int type, String userName, String pwd);
    }
}
