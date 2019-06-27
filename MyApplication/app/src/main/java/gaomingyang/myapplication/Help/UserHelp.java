package gaomingyang.myapplication.Help;

/**
 * 1.用户登录
 *    1.当用户登录应用程序时，利用 SharedPreferences 保存登录用户的登录标记（手机号）
 *    2.利用全局单例类UserHelp保存登录用户信息
 *          1.当用户登录之后，保存用户的登录信息
 *          2.当用户重新打开应用程序的时候，检测利用SharedPreferences是否存在登录标记，存在为userhelp负值，进入主页，没有的话进入登录页面
 * 2.用户退出
 *   1.删除 SharedPreferences 保存的登录信息，退出到登录页面
 */
public class UserHelp {

    private static  UserHelp instance;

    private UserHelp() {

    }

    public static  UserHelp getInstance(){
        if (instance == null){
            synchronized (UserHelp.class){
                if (instance == null){
                    instance = new UserHelp();
                }
            }
        }
        return instance;
    }

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
