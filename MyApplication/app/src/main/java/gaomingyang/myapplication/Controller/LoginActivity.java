package gaomingyang.myapplication.Controller;

import androidx.appcompat.app.AppCompatActivity;
import gaomingyang.myapplication.Controller.Base.BaseActivity;
import gaomingyang.myapplication.R;
import gaomingyang.myapplication.View.InputView;
import gaomingyang.myapplication.utils.Userutils;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import static android.content.ContentValues.TAG;

public class LoginActivity extends BaseActivity  {

    private InputView mInputPhone,mInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.initView();
    }

    private  void initView(){
            initNavBar(false,"登录",false);
            mInputPhone = this.findViewById(R.id.input_phone);
            mInputPassword = this.findViewById(R.id.input_password);

    }



    /*
    * 跳转注册页面
    * */
    public void onRegisterClick(View view) {
        Log.d(TAG, "onRegisterClick: 点击了注册！");
        ///跳转到注册
        Intent intent = new  Intent(this,RegisterActivity.class);
        startActivity(intent);
//        finish();
    }

    /**
     * 登录按钮点击事件
     * @param view
     */
    public void onLoginClick(View view) {
        Log.d(TAG, "onLoginClick: 点击了登录按钮!");
        String phone = mInputPhone.getInputStr();
        String password = mInputPassword.getInputStr();
        ///验证用户输入是否合法
        if (!Userutils.validateLogin(this,phone,password)){
            return;
        }


        ///跳转到应用主页
        Intent intent = new  Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}
