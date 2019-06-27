package gaomingyang.myapplication.Help;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

public class MediaPlayerHelp {
    public static   MediaPlayerHelp instance;
    private Context mContext;
    private String  mPath;
    private MediaPlayer mMediaPlayer;

    public OnMediaPlayerHelperListener  onMediaPlayerHelperListener;

    public  static  MediaPlayerHelp getInstances(Context context){
        if (instance == null){
            synchronized (MediaPlayerHelp.class){
                if (instance == null){
                    instance = new MediaPlayerHelp(context);
                }
            }
        }
        return instance;
    }
    private  MediaPlayerHelp(Context context){
        this.mContext = context;
        this.mMediaPlayer = new MediaPlayer();
    }

    /**
     * 1.setPath：当前需要播放的音乐路径
     * 2.start:开始播放音乐
     * 3.pause:暂止播放音乐
     */

    public  void setPath(String path){
        /**
         * 1.如果音乐正在播放，则需要重置播放状态
         * 2.设置音乐播放路径
         * 3.准备播放
         */
        this.mPath = path;
        if (this.mMediaPlayer.isPlaying()){
            this.mMediaPlayer.reset();
        }
        try {
            this.mMediaPlayer.setDataSource(this.mContext, Uri.parse(path));
            this.mMediaPlayer.prepareAsync();
            this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    if (onMediaPlayerHelperListener != null){
                        onMediaPlayerHelperListener.onPrepared(mp);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 返回正在播放的音乐路径
     * @return
     */
    public String getPath(){
        return this.mPath;
    }
    public void start(){
        if (this.mMediaPlayer.isPlaying()) return;
        this.mMediaPlayer.start();
    }

    public void pause(){
        this.mMediaPlayer.pause();
    }

    public interface  OnMediaPlayerHelperListener{
        void onPrepared(MediaPlayer mp);
    }
}
