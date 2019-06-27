package gaomingyang.myapplication.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import gaomingyang.myapplication.Controller.Base.BaseActivity;
import gaomingyang.myapplication.R;
import gaomingyang.myapplication.utils.Userutils;

import static android.content.ContentValues.TAG;

public class WelcomeActivity extends BaseActivity {
    private Timer   mTimer;
    @Override
    protected void onCreate(@androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        commoninit();
    }

    private void commoninit() {
        final  boolean isLogin =  Userutils.validateUserLogin(this);
        this.mTimer = new Timer();
        this.mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG, "run: welComeAcitvity");
//
                if (isLogin == true){
                    goMainActivity();
                }else {
                    gLoginActivity();
                }
            }
        },3000);

    }

    private  void goMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        this.startActivity(intent);
        this.finish();
    }

    private  void gLoginActivity(){
        Intent intent = new Intent(this,LoginActivity.class);
        this.startActivity(intent);
        this.finish();
    }
}
