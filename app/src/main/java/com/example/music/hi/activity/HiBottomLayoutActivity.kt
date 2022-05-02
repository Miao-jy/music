package com.example.music.hi.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.FragmentManager
import com.example.music.R
import com.example.music.hi.component.HiBaseFragment
import com.example.music.hi.fragment.*
import com.example.music.hi.tab.bottom.HiTabBottomInfo
import com.example.music.hi.tab.bottom.HiTabBottomLayout
import com.example.music.hi.tab.common.IHiTabLayout
import com.example.music.hi.tab.fragmentstructure.HiFragmentTabView
import com.example.music.hi.tab.fragmentstructure.HiTabViewAdapter

class HiBottomLayoutActivity : AppCompatActivity() {

    private val hiTabBottomInfoList: MutableList<HiTabBottomInfo<*>> = ArrayList()
    private val hiFragmentTabList: List<Class<out HiBaseFragment>> = arrayListOf(
        HomePageFragment::class.java,
        CategoryFragment::class.java,
        FavoriteFragment::class.java,
        ProfileFragment::class.java,
        RecommendedFragment::class.java
    )

    companion object {
        @JvmStatic
        fun start(context: Activity) {
            val intent = Intent(context, HiBottomLayoutActivity::class.java)
            ActivityCompat.startActivity(
                context, intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(context).toBundle()
            )
        }
    }

    private val TAG = "HiBottomLayoutActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hi_bottom_layout)
        initBottomTabItem()
    }

    private fun initBottomTabItem() {
        for (i in 1..5) {
            val info = HiTabBottomInfo(
                "Item$i", "fonts/iconfont.ttf",
                getString(R.string.jianbing), getString(R.string.jianbing),
                "#FF91FF3$i", "#FFd44949"
            )
            info.fragment = hiFragmentTabList[i - 1]
            hiTabBottomInfoList.add(info)
        }
        val hiTabBottomLayout = findViewById<HiTabBottomLayout>(R.id.hi_tab_bottom_layout_demo)
        val hiFragmentTabView = findViewById<HiFragmentTabView>(R.id.hi_fragment_tab_view)
        hiFragmentTabView.adapter = HiTabViewAdapter(supportFragmentManager, hiTabBottomInfoList)
        hiTabBottomLayout.bottomAlpha = 0.85F
//        hiTabBottomLayout.addTabSelectedChangeListener { position, _, nextInfo ->
//            hiFragmentTabView.currentItem = position
//        }
        hiTabBottomLayout.addTabSelectedChangeListener (object: IHiTabLayout.OnTabSelectedListener<HiTabBottomInfo<*>> {
            override fun onTabSelectedChange(
                index: Int,
                prevInfo: HiTabBottomInfo<*>,
                nextInfo: HiTabBottomInfo<*>
            ) {
                hiFragmentTabView.currentItem = index
            }
        })
        hiTabBottomLayout.inflateInfo(hiTabBottomInfoList)
        hiTabBottomLayout.defaultSelected(hiTabBottomInfoList[0])
    }
}