package com.example.music.view.home.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.music.R;
import com.example.music.db.MyDatabaseHelper;
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
                getString(R.string.jianbing), "bingjian",
                "#FF91FF33", "#FFd44949"
        ));
        Context context = getContext();
        if (context != null) {
            MyDatabaseHelper dbHelper = new MyDatabaseHelper(context, "photo", 1);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues value1 = new ContentValues();
            value1.put("name", "kotlin");
            value1.put("time", "2020-10-10");
            value1.put("width", 2000f);
            value1.put("height", 3000f);
            value1.put("address", "沈阳");
            db.insert("photo", null, value1);
            ContentValues value2 = new ContentValues();
            value2.put("name", "java");
            value2.put("time", "2022-12-19");
            value2.put("width", 1500f);
            value2.put("height", 1500f);
            value2.put("address", "北京");
            db.insert("photo", null, value2);
        }
    }
}
