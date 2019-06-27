package gaomingyang.myapplication.Controller.Base;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import gaomingyang.myapplication.Controller.MeActivity;
import gaomingyang.myapplication.R;

public class BaseActivity extends Activity {


    private ImageView mImvBack,mIvMe;
    private TextView  mTvTitle;
    protected  void initNavBar(boolean isShowBack,String title,boolean isShowMe){

        this.mImvBack  = this.findViewById(R.id.iv_back);
        this.mIvMe    = this.findViewById(R.id.iv_me);
        this.mTvTitle = this.findViewById(R.id.tv_title);

        this.mImvBack.setVisibility(isShowBack ? View.VISIBLE :View.GONE);
        this.mIvMe.setVisibility(isShowMe ? View.VISIBLE : View.GONE);
        this.mTvTitle.setText(title);

        this.mImvBack.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        this.mIvMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BaseActivity.this,MeActivity.class));
            }
        });
    }
}
