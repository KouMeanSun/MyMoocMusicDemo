package gaomingyang.myapplication.Controller;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import gaomingyang.myapplication.Controller.Base.BaseActivity;
import gaomingyang.myapplication.R;
import gaomingyang.myapplication.View.InputView;
import gaomingyang.myapplication.utils.Userutils;

public class RegisterActivity extends BaseActivity  {
    private InputView mInputPhone,mInputPassword,mInputPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        initNavBar(true,"注册",false);
        this.mInputPhone = this.findViewById(R.id.input_phone);
        this.mInputPassword = this.findViewById(R.id.input_password);
        this.mInputPasswordConfirm = this.findViewById(R.id.input_password2);
    }

    /**
     * 注册按钮
     * @param view
     */
    public void onRegisterAction(View view) {
        String phone = this.mInputPhone.getInputStr();
        String password = this.mInputPassword.getInputStr();
        String passwordConfirm = this.mInputPasswordConfirm.getInputStr();

        boolean result =  Userutils.registerUser(this,phone,password,passwordConfirm);
        if (result == true){
            ToastUtils.showShort("注册成功!");
            onBackPressed();
        }else {

            return;
        }

    }

}
