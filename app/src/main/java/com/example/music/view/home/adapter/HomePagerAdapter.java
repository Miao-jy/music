package com.example.music.view.home.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.music.view.home.fragment.DiscoveryFragment;
import com.example.music.view.home.fragment.FriendFragment;
import com.example.music.view.home.fragment.MineFragment;
import com.example.music.view.home.modle.CHANNEL;

public class HomePagerAdapter extends FragmentPagerAdapter {
    private CHANNEL[] mList;

    public HomePagerAdapter(@NonNull FragmentManager fm, CHANNEL[] datas) {
        super(fm);
        this.mList = datas;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        int type = mList[position].getValue();
        switch (type) {
            case CHANNEL.MINE_ID:
                return MineFragment.newInstance();
            case CHANNEL.FRIEND_ID:
                return FriendFragment.newInstance();
            case CHANNEL.DISCOVERY_ID:
                return DiscoveryFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.length;
    }
}
