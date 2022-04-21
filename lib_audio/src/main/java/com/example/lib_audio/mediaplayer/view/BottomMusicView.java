package com.example.lib_audio.mediaplayer.view;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lib_audio.R;
import com.example.lib_audio.app.AudioHelper;
import com.example.lib_audio.mediaplayer.core.AudioController;
import com.example.lib_audio.mediaplayer.core.MusicService;
import com.example.lib_audio.mediaplayer.events.AudioLoadEvent;
import com.example.lib_audio.mediaplayer.events.AudioPauseEvent;
import com.example.lib_audio.mediaplayer.events.AudioStartEvent;
import com.example.lib_audio.mediaplayer.model.AudioBean;
import com.example.lib_image_loader.app.ImageLoaderManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class BottomMusicView extends RelativeLayout {
    private Context mContext;

    private ImageView mLeftView;
    private TextView mTitleView;
    private TextView mAlbumView;
    private ImageView mPlayView;
    private ImageView mRightView;

    private AudioBean audioBean;


    public BottomMusicView(Context context) {
        this(context, null);
    }

    public BottomMusicView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomMusicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.bottom_view, this);
        rootView.setOnClickListener(v -> {
            // 跳转到播放activity
            MusicPlayerActivity.start((Activity) mContext);
        });
        mLeftView = findViewById(R.id.album_view);

        // 专辑图片旋转
        ObjectAnimator animator = ObjectAnimator.ofFloat(mLeftView, View.ROTATION.getName(), 0f, 360f);
        animator.setDuration(10000);
        animator.setRepeatCount(-1);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();

        mTitleView = findViewById(R.id.audio_name_view);
        mAlbumView = findViewById(R.id.audio_album_view);
        mPlayView = findViewById(R.id.play_view);
        mPlayView.setOnClickListener(v -> {
            AudioController.getInstance().playOrPause();
        });
        mRightView = findViewById(R.id.content_view);
        mRightView.setOnClickListener(v -> {
            // 显示音乐列表对话框
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAudioLoadEvent(AudioLoadEvent event) {
        showLoadView(event.mAudioBean);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAudioStartEvent(AudioStartEvent event) {
        showPauseView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAudioPauseEvent(AudioPauseEvent event) {
        showPlayView();
    }

    private void showLoadView(AudioBean audioBean) {
        ImageLoaderManager.getInstance().displayImageForCircle(mLeftView, audioBean.albumPic);
        mTitleView.setText(audioBean.name);
        mAlbumView.setText(audioBean.album);
        mPlayView.setImageResource(R.drawable.ic_baseline_pause_24);
    }

    private void showPauseView() {
        mPlayView.setImageResource(R.drawable.ic_baseline_pause_24);
    }

    private void showPlayView() {
        mPlayView.setImageResource(R.drawable.ic_baseline_play_arrow_24);
    }

}
