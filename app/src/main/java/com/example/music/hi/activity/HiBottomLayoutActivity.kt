package com.example.music.hi.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import com.example.music.R
import com.example.music.hi.tab.bottom.HiTabBottomInfo
import com.example.music.hi.tab.bottom.HiTabBottomLayout

class HiBottomLayoutActivity : AppCompatActivity() {

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
        val infoList: MutableList<HiTabBottomInfo<*>> = ArrayList()
        for (i in 1..5) {
            infoList.add(
                HiTabBottomInfo(
                    "Item$i",  "fonts/iconfont.ttf",
                    getString(R.string.jianbing), null,
                    "#FF91FF3$i", "#FFd44949")
            )
        }
        Log.d(TAG, "initBottomTabItemList: $infoList" )
        val hiTabBottomLayout = findViewById<HiTabBottomLayout>(R.id.hi_tab_bottom_layout_demo)
        hiTabBottomLayout.setBottomAlpha(0.85F)
        hiTabBottomLayout.addTabSelectedChangeListener { _, _, nextInfo ->
            Toast.makeText(this@HiBottomLayoutActivity, nextInfo.name, Toast.LENGTH_SHORT).show()
        }
        hiTabBottomLayout.inflateInfo(infoList)
    }
}