package gaomingyang.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.util.List;

import gaomingyang.myapplication.Controller.LoginActivity;
import gaomingyang.myapplication.Help.RealmHelp;
import gaomingyang.myapplication.Help.UserHelp;
import gaomingyang.myapplication.Model.UserModel;
import gaomingyang.myapplication.R;

public class Userutils {
    private final static String TAG = "Userutils";
    /**
     * 验证登录用户输入的合法性
     */
    public  static  boolean validateLogin(Context context,String phone,String password){
        //简单的
//        RegexUtils.isMobileSimple(phone);
        //精确的
          if (!RegexUtils.isMobileExact(phone)) {
              ToastUtils.showShort("无效手机号!");
              Log.e(TAG,"无效手机号!");
              return false;
          }
          if (TextUtils.isEmpty(password)){
              ToastUtils.showShort("密码不能为空!");
              Log.e(TAG,"密码不能为空!");
              return false;
          }

        /**
         * 1.判断用户当前手机号是否已经被注册了
         * 2.用户输入的手机号和密码是否匹配
         */
        if (!Userutils.userExistFromPhone(phone)){
            ToastUtils.showShort("当前手机号未注册!");
            Log.e(TAG,"当前手机号未注册!");
            return false;
        }
        RealmHelp realmHelp = new RealmHelp();
        boolean result = realmHelp.validateUser(phone,EncryptUtils.encryptMD5ToString(password));
        realmHelp.closeRealm();
        if (result == false){
            ToastUtils.showShort("手机号或者密码不正确!");
            Log.e(TAG,"手机号或者密码不正确!");
            return false;
        }

        //保存用户登录标记
       boolean isSave =   SPUtils.saveUser(context,phone);
        if (!isSave){
            ToastUtils.showShort("系统错误，请稍后重试!");
            Log.e(TAG,"系统错误，请稍后重试!");
            return false;
        }
        //保存用户标记,在全局单例类中
        UserHelp.getInstance().setPhone(phone);
        return true;
    }


    /**
     * 退出登录
     */
    public  static  void logout(Context context){
        //删除 SharedPreferences 的用用户信息
        boolean isRemove = SPUtils.removeUser(context);
        if (!isRemove){
            ToastUtils.showShort("系统错误，请稍后重试!");
            Log.e(TAG,"系统错误，请稍后重试!");
        }
        Intent intent = new Intent(context,LoginActivity.class);
        //清理task栈，并且新生成一个task栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        ///一定要在startActivity之后调用。定义activity跳转动画
        ((Activity)context).overridePendingTransition(R.anim.open_enter,R.anim.open_exit);
    }

    /**
     * 注册用户
     * @param context
     * @param phone
     * @param password
     * @param passwordConfirm
     */
    public  static  boolean registerUser(Context context,String phone,String password,String passwordConfirm){
        if (!RegexUtils.isMobileExact(phone)) {
            ToastUtils.showShort("无效手机号!");
            Log.e(TAG,"无效手机号!");
            return false;
        }
        if (StringUtils.isEmpty(password) || !password.equals(passwordConfirm)){
            ToastUtils.showShort("请确认密码!");
            Log.e(TAG,"请确认密码!");
            return false;
        }
        //用户当前输入的手机号是否已经被注册了
        if (Userutils.userExistFromPhone(phone)){
            ToastUtils.showShort("该手机号已存在!");
            Log.e(TAG,"该手机号已存在!");
            return false;
        }

        UserModel userModel = new UserModel();
        userModel.setPhone(phone);
        userModel.setPassword(EncryptUtils.encryptMD5ToString(password));

        saveUser(userModel);

        return true;
    }

    /**
     * 保存用户到数据库
     * @param userModel
     */
    public  static  void saveUser(UserModel userModel){
        RealmHelp realmHelp = new RealmHelp();
        realmHelp.saveUser(userModel);
        realmHelp.closeRealm();
    }

    /**
     * 根据手机号判断是否存在该用户
     * @param phone
     * @return
     */
    public  static  boolean userExistFromPhone(String phone){
        boolean result = false;
        RealmHelp realmHelp = new RealmHelp();
        List<UserModel> allUsers = realmHelp.getAllUser();
        for(UserModel userModel :allUsers){
            if (userModel.getPhone().equals(phone)){
///             当前手机号已经存在于数据库了
                result = true;
                break;
            }
        }

        realmHelp.closeRealm();
        return result;
    }

    /**
     * 验证是否存在已登录
     * @param context
     * @return
     */
    public static boolean validateUserLogin(Context context){
       return SPUtils.isLoginUser(context);
    }

    public static  boolean changePassword(Context context,String oldPassword,String newPassword,String newPasswordConfirm){
        /**
         * 1.数据验证，a。原密码是否输入，b。新密码是否输入。c。新密码与确定密码是否相同。d。原密码是否输入正确,e,根据用户模型判断原密码是否匹配
         * 2.利用Realm 模型自动更新特性完成密码的修改
         */
        if (TextUtils.isEmpty(oldPassword)){
            ToastUtils.showShort("请输入原密码!");
            Log.e(TAG,"请输入原密码!");
            return  false;
        }
        if (TextUtils.isEmpty(newPassword)){
            ToastUtils.showShort("请输入新密码!");
            Log.e(TAG,"请输入新密码!");
            return  false;
        }
        if (!newPassword.equals(newPasswordConfirm)){
            ToastUtils.showShort("两次输入的新密码不一致!");
            Log.e(TAG,"两次输入的新密码不一致!");
            return  false;
        }
        RealmHelp realmHelp =  new RealmHelp();
        UserModel userModel = realmHelp.getUser();
        if (userModel != null){
            if (!EncryptUtils.encryptMD5ToString(oldPassword).equals(userModel.getPassword())){
                ToastUtils.showShort("原密码不正确!");
                Log.e(TAG,"原密码不正确!");
                realmHelp.closeRealm();
                return false;
            }else {
                //修改用户密码
                realmHelp.changePassword(EncryptUtils.encryptMD5ToString(newPassword));
                realmHelp.closeRealm();
                return true;
            }

        }else{
            realmHelp.closeRealm();
            return false;
        }
    }
}
