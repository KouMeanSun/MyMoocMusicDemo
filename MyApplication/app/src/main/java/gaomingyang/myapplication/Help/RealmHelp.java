package gaomingyang.myapplication.Help;

import java.util.List;

import gaomingyang.myapplication.Model.UserModel;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmHelp {
    private Realm mRealm;


    public RealmHelp(){
        this.mRealm = Realm.getDefaultInstance();

    }

    /**
     * 关闭数据库
     */
    public void closeRealm(){
        if (this.mRealm != null && !this.mRealm.isClosed()){
            this.mRealm.close();
        }
    }
    /**
     * 保存用户信息
     * @param userModel
     */
    public void saveUser(UserModel userModel){
        this.mRealm.beginTransaction();
        this.mRealm.insert(userModel);
//        this.mRealm.insertOrUpdate(userModel);
        this.mRealm.commitTransaction();
    }

    /**
     * 返回所有用户
     * @return
     */
    public List<UserModel> getAllUser(){
        RealmQuery<UserModel>  query =  this.mRealm.where(UserModel.class);
        RealmResults<UserModel>  results = query.findAll();
        return results;
    }

    /**
     * 验证用户信息
     * @param phone
     * @param password
     * @return
     */
    public boolean validateUser(String phone,String password){
       RealmQuery<UserModel>  query =  this.mRealm.where(UserModel.class);
       query.equalTo("phone",phone).equalTo("password",password);
       UserModel userModel = query.findFirst();
       if (userModel != null){
           return true;
       }
       return false;
    }

    /**
     * 获取当前登录用户
     * @return
     */
    public UserModel getUser(){
        RealmQuery<UserModel> query =  this.mRealm.where(UserModel.class);
        UserModel userModel =query.equalTo("phone",UserHelp.getInstance().getPhone()).findFirst();
        return userModel;
    }

    /**
     * 修改密码
     */
    public void changePassword(String newPassword){
        UserModel userModel =  this.getUser();
        this.mRealm.beginTransaction();
        userModel.setPassword(newPassword);

        this.mRealm.commitTransaction();

    }
}
