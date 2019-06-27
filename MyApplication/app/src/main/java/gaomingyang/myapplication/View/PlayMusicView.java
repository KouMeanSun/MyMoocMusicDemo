package gaomingyang.myapplication.View;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import de.hdodenhof.circleimageview.CircleImageView;
import gaomingyang.myapplication.Help.MediaPlayerHelp;
import gaomingyang.myapplication.R;

public class PlayMusicView  extends FrameLayout implements MediaPlayerHelp.OnMediaPlayerHelperListener {
    private Context mContext;
    private MediaPlayerHelp mMediaPlayerHelper;
    private boolean isPlaying;
    private String mPath;
    private View    mView;
    private FrameLayout mFlPlayMusic;

    private CircleImageView mIvIcon ;
    private ImageView mIvNeedle,mIvPlay;
    private Animation mPlaymusicAnim,mPlayNeedleAnim,mStopNeedleAnim;

    public PlayMusicView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        this.mContext = context;

        this.mView = LayoutInflater.from(this.mContext).inflate(R.layout.play_music,this,false);
        this.mFlPlayMusic = this.mView.findViewById(R.id.fl_play_music);
        this.mIvNeedle = this.mView.findViewById(R.id.iv_needle);
        this.mIvIcon = this.mView.findViewById(R.id.iv_icon);
        this.mIvPlay = this.mView.findViewById(R.id.iv_play);

        /**
         * 1.光盘需要转动的动画
         * 2.指针指向光盘的动画
         * 3.指针离开光盘的的动画
         */
        this.mPlaymusicAnim = AnimationUtils.loadAnimation(this.mContext,R.anim.play_music_anim);
        this.mPlayNeedleAnim = AnimationUtils.loadAnimation(this.mContext,R.anim.play_reedle_anim);
        this.mStopNeedleAnim = AnimationUtils.loadAnimation(this.mContext,R.anim.stop_needle_anim);


        this.addView(this.mView);

        this.mFlPlayMusic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMusic();
            }
        });

        this.mMediaPlayerHelper = MediaPlayerHelp.getInstances(this.mContext);

    }

    private void toggleMusic(){
        if (this.isPlaying){
            this.stopMusic();
        }else{
            this.playMusic(this.mPath);
        }
    }
    public  void playMusic(String path){
        this.mPath = path;
        this.isPlaying = true;
        this.mIvPlay.setVisibility(View.GONE);
        this.mFlPlayMusic.startAnimation(this.mPlaymusicAnim);
        this.mIvNeedle.startAnimation(this.mPlayNeedleAnim);

        /**
         * 1.判断当前音乐是否已经进行了播放
         * 2.如果当前音乐是已经在播放的音乐，则直接执行start
         * 3.如果当前播放的音乐不是正在执行的音乐，则重置
         */
        if (this.mMediaPlayerHelper.getPath() !=null && this.mMediaPlayerHelper.getPath().equals(path)){
            this.mMediaPlayerHelper.start();
        }else{
            this.mMediaPlayerHelper.setPath(path);
            this.mMediaPlayerHelper.onMediaPlayerHelperListener = this;
        }
    }
    public void stopMusic(){
        this.isPlaying = false;
        this.mFlPlayMusic.clearAnimation();
        this.mIvNeedle.startAnimation(this.mStopNeedleAnim);
        this.mIvPlay.setVisibility(View.VISIBLE);

        this.mMediaPlayerHelper.pause();
    }
    /**
     * 设置光盘中显示的音乐封面图片
     * @param iconUrl 图片地址
     */
    public void setMusicIcon(String iconUrl){
        Glide.with(this.mContext)
                .load(iconUrl)
                .into(this.mIvIcon);

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        this.mMediaPlayerHelper.start();
    }
}
