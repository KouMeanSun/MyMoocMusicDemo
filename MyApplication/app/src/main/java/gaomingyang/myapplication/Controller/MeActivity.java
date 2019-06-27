package gaomingyang.myapplication.Controller;

import gaomingyang.myapplication.Controller.Base.BaseActivity;
import gaomingyang.myapplication.Help.UserHelp;
import gaomingyang.myapplication.R;
import gaomingyang.myapplication.utils.Userutils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MeActivity extends BaseActivity {
    private TextView userNameTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        initView();
    }

    private void initView() {
        initNavBar(true,"个人中心",false);
        this.userNameTV = this.findViewById(R.id.tv_user_name);
        this.userNameTV.setText("用户名:"+UserHelp.getInstance().getPhone());
    }

    /**
     * 修改了密码
     * @param view
     */
    public void onChangeClick(View view) {
        startActivity(new Intent(this,ChangePasswordActivity.class));
    }

    /**
     * 退出登录
     * @param view
     */
    public void onLogoutClick(View view) {
        Userutils.logout(this);

    }
}
