package com.example.music.view.home;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.lib_audio.app.AudioHelper;
import com.example.lib_audio.mediaplayer.model.AudioBean;
import com.example.lib_commin_ui.base.BaseActivity;
import com.example.lib_image_loader.app.ImageLoaderManager;
import com.example.music.R;
import com.example.music.hi.activity.HiBottomLayoutActivity;
import com.example.music.view.home.adapter.HomePagerAdapter;
import com.example.music.view.home.modle.CHANNEL;
import com.example.music.view.leafloading.LeafLoadingActivity;
import com.example.music.view.login.LoginActivity;
import com.example.music.view.login.entity.LoginEvent;
import com.example.music.view.login.manager.UserManager;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    // 制定首页要出现的卡片
    private static final CHANNEL[] CHANNELS = new CHANNEL[]{CHANNEL.MY, CHANNEL.DISCOVERY, CHANNEL.FRIEND};

    /**
     * Views
     */
    private DrawerLayout mDrawerLayout;
    private View mToggleView;
    private View mSearchView;
    private ViewPager mViewPager;
    private HomePagerAdapter mHomePagerAdapter;
    private LinearLayout unLoginLayout;
    private ImageView mPhotoView;

    private ArrayList<AudioBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_home);
        initData();
        initView();
    }

    private void initData() {
        mList.add(new AudioBean("10001",
                "http://music-miao.oss-cn-beijing.aliyuncs.com/mp3/amani.mp3",
                "amani",
                "beyond",
                "光辉岁月","呼吁战争和平呼吁战争和平呼吁战争和平呼吁战争和平",
                "https://p2.music.126.net/XcSfbUzatJ5dHBVj4VwZYA==/109951166291796934.jpg?param=90y90", "05:15"));

        mList.add(new AudioBean("10002",
                "http://music-miao.oss-cn-beijing.aliyuncs.com/mp3/what%20could%20have%20been%20feat.mp3",
                "what could have been feat",
                "sting",
                "Arcane", "双城之战双城之战双城之战双城之战双城之战",
                "http://p2.music.126.net/tou7ElOOrFaJ7ICkNMmUkA==/109951166688022119.jpg?param=130y130", "03:35"));
        mList.add(new AudioBean("10003",
                "http://music-miao.oss-cn-beijing.aliyuncs.com/mp3/Guns%20for%20Hire.mp3",
                "Guns for Hire",
                "Woodkid",
                "Arcane", "双城之战双城之战双城之战双城之战双城之战双城之战",
                "http://p2.music.126.net/tou7ElOOrFaJ7ICkNMmUkA==/109951166688022119.jpg?param=130y130", "03:46"));
        AudioHelper.startMusicService(mList);
    }

    private void initView() {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToggleView = findViewById(R.id.toggle_view);
        mToggleView.setOnClickListener(this);
        mSearchView = findViewById(R.id.search_view);
        mSearchView.setOnClickListener(this);
        mViewPager = findViewById(R.id.view_pager);
        mHomePagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), CHANNELS);
        mViewPager.setAdapter(mHomePagerAdapter);
        initMagicIndicator();
        // 登录相关UI
        unLoginLayout = findViewById(R.id.unloggin_layout);
        unLoginLayout.setOnClickListener(this);
        mPhotoView = findViewById(R.id.avatr_view);
        // 点击跳转到bottomLayoutDemo
        findViewById(R.id.linkToHiBottomDemo).setOnClickListener(this);
    }

    // 初始化指示器
    private void initMagicIndicator() {
        MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator);
        magicIndicator.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return CHANNELS == null ? 0 : CHANNELS.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.parseColor("#999999"));
                colorTransitionPagerTitleView.setSelectedColor(Color.parseColor("#333333"));
                colorTransitionPagerTitleView.setText(CHANNELS[index].getKey());
                colorTransitionPagerTitleView.setTextSize(19);
                colorTransitionPagerTitleView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.unloggin_layout:
                if (!UserManager.getInstance().hasLogin()) {
                    LoginActivity.start(this);
                } else {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }
            case R.id.search_view:
                LeafLoadingActivity.start(this);
            case R.id.linkToHiBottomDemo:
                HiBottomLayoutActivity.start(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent event) {
        unLoginLayout.setVisibility(View.GONE);
        mPhotoView.setVisibility(View.VISIBLE);
        ImageLoaderManager.getInstance()
                .displayImageForCircle(
                        mPhotoView, UserManager.getInstance().getUser().data.photoUrl);
    }
}
