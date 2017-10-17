package com.example.bachk.playvideodemo.customview;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.VideoView;

import com.example.bachk.playvideodemo.R;

/**
 * Created by bachk on 10/17/2017.
 */

public class CustomVideoView extends RelativeLayout implements SeekBar.OnSeekBarChangeListener {
    private static final String TAG = CustomVideoView.class.getSimpleName();
    private IeOnImgBtnFullScreenClick listener;

    private VideoView videoView;
    private ImageView imgBtnPlay;
    private ImageView imgBtnFull;
    private SeekBar seekBar;
    private ImageView imgPreview;
    private Handler mHandler;

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CustomVideoView(Context context) {
        super(context);
        initView(context);
    }

    private void initView(final Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_video_view, this, true);
        setBackgroundColor(getResources().getColor(R.color.colorBlack));

        videoView = (VideoView) getChildAt(0);
        imgPreview = (ImageView) getChildAt(1);
        imgBtnPlay = (ImageView) getChildAt(2);
        imgBtnFull = (ImageView) getChildAt(3);
        seekBar = (SeekBar) getChildAt(4);

        mHandler = new Handler();

        imgBtnFull.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onImgBtnFullClick();
            }
        });

        imgBtnPlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                playVideo();
            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                imgPreview.setVisibility(GONE);
            }
        });

        videoView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.d(TAG, "onTouch: down called");
                    if (imgBtnPlay.getVisibility() == GONE) {
                        imgBtnPlay.setVisibility(VISIBLE);
                        imgBtnFull.setVisibility(VISIBLE);
                        seekBar.setVisibility(VISIBLE);
                        hideController();
                    } else {
                        imgBtnPlay.setVisibility(GONE);
                        imgBtnFull.setVisibility(GONE);
                        seekBar.setVisibility(GONE);
                    }
                }
                return false;
            }
        });

        seekBar.setOnSeekBarChangeListener(this);
    }

    public void setVideoUrl(String url) {
        videoView.setVideoURI(Uri.parse(url));
        videoView.pause();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            imgPreview.setBackground(getResources().getDrawable(R.drawable.ic_launcher));
        } else {
            imgPreview.setBackgroundResource(R.drawable.ic_launcher);
        }
        imgPreview.setVisibility(VISIBLE);
    }

    public void playVideo() {
        if (videoView.isPlaying()) {
            videoView.pause();
            imgBtnPlay.setBackgroundResource(R.drawable.ic_media_play);
        } else {
            videoView.start();
            updateProgressBar();
            imgBtnPlay.setBackgroundResource(R.drawable.ic_media_pause);
        }

        hideController();
    }

    private void updateProgressBar() {
        mHandler.postDelayed(updateTimeTask, 100);
    }

    private Runnable updateTimeTask = new Runnable() {
        public void run() {
            seekBar.setProgress(videoView.getCurrentPosition());
            seekBar.setMax(videoView.getDuration());
            mHandler.postDelayed(this, 100);
        }
    };

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mHandler.removeCallbacks(updateTimeTask);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mHandler.removeCallbacks(updateTimeTask);
        videoView.seekTo(seekBar.getProgress());
        updateProgressBar();
    }


    public interface IeOnImgBtnFullScreenClick {
        void onImgBtnFullClick();
    }

    public void setOnImgBtnFullClick(IeOnImgBtnFullScreenClick click) {
        this.listener = click;
    }

    private void hideController() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgBtnPlay.setVisibility(GONE);
                imgBtnFull.setVisibility(GONE);
                seekBar.setVisibility(GONE);
            }
        }, 3000);

    }
}
