package gaomingyang.myapplication.Controller;

import gaomingyang.myapplication.Controller.Base.BaseActivity;
import gaomingyang.myapplication.R;
import gaomingyang.myapplication.View.PlayMusicView;
import jp.wasabeef.glide.transformations.BlurTransformation;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class PlayMusicActivity extends BaseActivity {
    private ImageView mIVBg;
    private PlayMusicView mPlayMusicView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
    }

    private void initView() {
        this.mIVBg = this.findViewById(R.id.iv_bg);
        this.mPlayMusicView = this.findViewById(R.id.play_music_view);
        this.mPlayMusicView.setMusicIcon("http://musicugc.qianqian.com/ugcdiy/pic/e9d826ebd4f77e33af2fa379d8d7de6f.jpg");
        this.mPlayMusicView.playMusic("http://res.lgdsunday.club/Nostalgic%20Piano.mp3");
        // 1.处理之后的模糊成都，2.生成的图片在原图片的宽高比，10代表十分之一
        //BlurTransformation(25,10)

        Glide.with(this)
                .load("http://musicugc.qianqian.com/ugcdiy/pic/e9d826ebd4f77e33af2fa379d8d7de6f.jpg")
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25,10)))
                .into(this.mIVBg);
    }

    public void onBackClick(View view) {
        finish();
    }
}
