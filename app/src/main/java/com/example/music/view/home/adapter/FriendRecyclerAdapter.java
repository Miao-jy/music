package com.example.music.view.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.lib_audio.app.AudioHelper;
import com.example.lib_commin_ui.MultiImageViewLayout;
import com.example.lib_image_loader.app.ImageLoaderManager;
import com.example.music.R;
import com.example.music.view.home.HomeActivity;
import com.example.music.view.home.modle.FriendBodyValue;
import com.example.music.view.login.LoginActivity;
import com.example.music.view.login.manager.UserManager;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class FriendRecyclerAdapter extends MultiItemTypeAdapter {

    public static final int MUSIC_TYPE = 0x01;
    public static final int VIDEO_TYPE = 0x02;

    private Context mContext;

    public FriendRecyclerAdapter(Context context, List datas) {
        super(context, datas);
        mContext = context;
        addItemViewDelegate(VIDEO_TYPE, new ItemViewDelegate<FriendBodyValue>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_friend_list_video_layout;
            }

            @Override
            public boolean isForViewType(FriendBodyValue item, int position) {
                return item.type == VIDEO_TYPE;
            }

            @Override
            public void convert(ViewHolder holder, FriendBodyValue friendBodyValue, int position) {

            }

        });
        addItemViewDelegate(MUSIC_TYPE, new ItemViewDelegate<FriendBodyValue>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.item_friend_list_picture_layout;
            }

            @Override
            public boolean isForViewType(FriendBodyValue item, int position) {
                return item.type == MUSIC_TYPE;
            }

            @Override
            public void convert(ViewHolder holder, FriendBodyValue friendBodyValue, int position) {
                holder.setText(R.id.name_view, friendBodyValue.name + "");
                holder.setText(R.id.fansi_view, friendBodyValue.fans + "");
                holder.setText(R.id.text_view, friendBodyValue.text);
                holder.setText(R.id.zan_view, friendBodyValue.zan);
                holder.setText(R.id.message_view, friendBodyValue.msg);
                holder.setText(R.id.audio_name_view, friendBodyValue.audioBean.name);
                holder.setText(R.id.audio_author_view, friendBodyValue.audioBean.author);
                holder.setOnClickListener(R.id.album_layout, v -> {
                    AudioHelper.addAudio((Activity) mContext, friendBodyValue.audioBean);
                });
                holder.setOnClickListener(R.id.guanzhu_view, v -> {
                    if (!UserManager.getInstance().hasLogin()) {
                        LoginActivity.start(mContext);
                    }
                });
                ImageView avatar = holder.getView(R.id.photo_view);
                ImageLoaderManager.getInstance().displayImageForCircle(avatar, friendBodyValue.avatr);
                ImageView albumPicView = holder.getView(R.id.album_view);
                ImageLoaderManager.getInstance().displayImageForView(albumPicView, friendBodyValue.audioBean.albumPic);
                // 多图自动布局
                MultiImageViewLayout imageViewLayout = holder.getView(R.id.image_layout);
                imageViewLayout.setList(friendBodyValue.pics);
            }
        });
    }
}
