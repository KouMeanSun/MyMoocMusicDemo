package gaomingyang.myapplication.Controller;

import androidx.appcompat.app.AppCompatActivity;
import gaomingyang.myapplication.Controller.Base.BaseActivity;
import gaomingyang.myapplication.R;
import gaomingyang.myapplication.View.InputView;
import gaomingyang.myapplication.utils.Userutils;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;

public class ChangePasswordActivity extends BaseActivity {
    private InputView mOldPassword,mNewPassword,mNewPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
    }

    private void initView() {
        initNavBar(true,"修改密码",false);
        this.mOldPassword = this.findViewById(R.id.input_old_password);
        this.mNewPassword = this.findViewById(R.id.input_new_password);
        this.mNewPasswordConfirm = this.findViewById(R.id.input_new_password_confirm);
    }

    /**
     * 修改密码
     * @param view
     */
    public void onChangePasswordClick(View view) {
        String oldPassword = this.mOldPassword.getInputStr();
        String newPassword = this.mNewPassword.getInputStr();
        String newPasswordConfirm = this.mNewPasswordConfirm.getInputStr();

        boolean result = Userutils.changePassword(this,oldPassword,newPassword,newPasswordConfirm);
        if (result == true){
            ToastUtils.showShort("密码修改成功!");
            Userutils.logout(this);
        }else{

        }
    }
}
