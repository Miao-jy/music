package com.example.music.view.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.music.R;
import com.example.music.hi.tab.bottom.HiTabBottomInfo;
import com.example.music.hi.tab.common.IHiTabItem;

public class MineFragment extends Fragment {

    private static volatile MineFragment mineFragment;

    private MineFragment() {
    }

    public static MineFragment newInstance() {
        if (mineFragment == null) {
            synchronized (MineFragment.class) {
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                }
            }
        }
        return mineFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IHiTabItem<HiTabBottomInfo<String>> bottomView = view.findViewById(R.id.hi_tab_item_bottom);
        bottomView.setHiTabInfo(new HiTabBottomInfo<>(
                "煎饼", "fonts/iconfont.ttf",
                getString(R.string.jianbing), null,
                "#FF91FF33", "#FFd44949"
        ));
    }
}
