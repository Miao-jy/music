package com.example.music.view.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.lib_network.okhttp.listener.DisposeDataListener;
import com.example.music.R;
import com.example.music.api.RequestCenter;
import com.example.music.view.home.adapter.FriendRecyclerAdapter;
import com.example.music.view.home.modle.BaseFriendModel;
import com.example.music.view.home.modle.FriendBodyValue;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class FriendFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private static volatile FriendFragment friendFragment;

    private Context mContext;

    // views
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private FriendRecyclerAdapter mAdapter;

    // data
    private BaseFriendModel mRecommandData;
    private List<FriendBodyValue> mDatas = new ArrayList<>();

    private FriendFragment() {
    }

    public static FriendFragment newInstance() {
        if (friendFragment == null) {
            synchronized (FriendFragment.class) {
                if (friendFragment == null) {
                    friendFragment = new FriendFragment();
                }
            }
        }
        return friendFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friend_layout, container, false);
        mSwipeRefreshLayout = rootView.findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_red_light)
        );
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView = rootView.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requestData();
    }

    /**
     * swipeRefreshLayout 的滑动监听
     */
    @Override
    public void onRefresh() {
        requestData();
    }

    /**
     * 发送网络请求，更新UI
     */
    private void requestData() {
        RequestCenter.friend(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                mRecommandData = (BaseFriendModel) responseObj;
                updateUI();
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });
    }

    /**
     * 更新UI
     */
    private void updateUI() {
        mSwipeRefreshLayout.setRefreshing(false);
        mDatas = mRecommandData.data.list;
        mAdapter = new FriendRecyclerAdapter(mContext, mDatas);
        mRecyclerView.setAdapter(mAdapter);
    }
}
