package com.example.music.hi.tab.bottom

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.music.R
import com.example.music.hi.tab.common.IHiTabItem

class HiTabItemBottom @JvmOverloads constructor (context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RelativeLayout(
    context,
    attrs,
    defStyleAttr
), IHiTabItem<HiTabBottomInfo<*>>{

    var tabInfo: HiTabBottomInfo<*> = HiTabBottomInfo<String>()
    var tabImageView: ImageView
    var tabIconView: TextView
    var tabNameView: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.hi_tab_bottom, this)
        tabImageView = findViewById(R.id.iv_image)
        tabIconView = findViewById(R.id.tv_icon)
        tabNameView = findViewById(R.id.tv_name)
    }

    private fun inflateInfo(selected: Boolean, init: Boolean) {
        if (tabInfo.tabType == HiTabBottomInfo.TabType.ICON) {
            if (init) {
                tabImageView.visibility = GONE
                tabIconView.visibility = VISIBLE
                tabIconView.typeface = Typeface.createFromAsset(context.assets, tabInfo.iconFont)
                tabNameView.text = tabInfo.name
            }

            if (selected) {
                tabIconView.text = tabInfo.selectedIconName
                tabIconView.setTextColor(tabInfo.tintColor?.let { getTextColor(it) }!!)
                tabNameView.setTextColor(tabInfo.tintColor?.let { getTextColor(it) }!!)
            } else {
                tabIconView.text = tabInfo.defaultIconName
                tabIconView.setTextColor(tabInfo.defaultColor?.let { getTextColor(it) }!!)
                tabNameView.setTextColor(tabInfo.defaultColor?.let { getTextColor(it) }!!)
            }
        } else if (tabInfo.tabType == HiTabBottomInfo.TabType.BITMAP) {
            if (init) {
                tabIconView.visibility = GONE
                tabImageView.visibility = VISIBLE
                tabNameView.text = tabInfo.name
            }

            if (selected) {
                tabImageView.setImageBitmap(tabInfo.selectedBitmap)
            } else {
                tabImageView.setImageBitmap(tabInfo.defaultBitmap)
            }
        }
    }

    override fun setHiTabInfo(info: HiTabBottomInfo<*>) {
        this.tabInfo = info
        inflateInfo(false, true)
    }

    override fun resetHeight(height: Int) {
        TODO("Not yet implemented")
    }

    override fun onTabSelectedChange(
        index: Int,
        prevInfo: HiTabBottomInfo<*>,
        nextInfo: HiTabBottomInfo<*>
    ) {
        if (prevInfo != tabInfo && nextInfo != tabInfo || prevInfo == nextInfo) {
            return;
        }
        if (prevInfo == tabInfo) {
            inflateInfo(selected = false, init = false);
        } else {
            inflateInfo(selected = true, init = false);
        }
    }

    private fun getTextColor(color: Any): Int {
        return if (color is String) Color.parseColor(color) else color as Int
    }

}