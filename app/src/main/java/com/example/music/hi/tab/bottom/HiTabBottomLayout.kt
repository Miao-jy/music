package com.example.music.hi.tab.bottom

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.FrameLayout
import android.widget.ScrollView
import androidx.annotation.NonNull
import androidx.core.view.forEach
import androidx.recyclerview.widget.RecyclerView
import com.example.lib_hi.util.HiDisplayUtil
import com.example.lib_hi.util.HiViewUtil
import com.example.music.R
import com.example.music.hi.tab.common.IHiTabLayout

class HiTabBottomLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr), IHiTabLayout<HiTabItemBottom, HiTabBottomInfo<*>> {

    var selectInfo: HiTabBottomInfo<*> = HiTabBottomInfo<String>()
    var bottomAlpha = 1f
    var tabBottomHeight = 50f
    var bottomLineHeight = 0.5f
    var bottomLineColor = "#dfe0e1"
    var infoList: List<HiTabBottomInfo<*>>? = null
    private val tabSelectedChangeListeners: MutableList<IHiTabLayout.OnTabSelectedListener<HiTabBottomInfo<*>>> = ArrayList()

    companion object {
        const val TAG_TAB_BOTTOM = "TAG_TAB_BOTTOM"
    }

    override fun findTab(data: HiTabBottomInfo<*>): HiTabItemBottom? {
        val ll = findViewWithTag<ViewGroup>(TAG_TAB_BOTTOM)
        ll.forEach {
            if (it is HiTabItemBottom && it.tabInfo == data) {
                return it
            }
        }
        return null
    }

    override fun addTabSelectedChangeListener(listener: IHiTabLayout.OnTabSelectedListener<HiTabBottomInfo<*>>) {
        this.tabSelectedChangeListeners.add(listener)
    }

    override fun defaultSelected(defaultInfo: HiTabBottomInfo<*>) {
        onSelected(defaultInfo)
        this.selectInfo = defaultInfo
    }

    override fun inflateInfo(infoList: List<HiTabBottomInfo<*>>) {
        this.infoList = infoList
        // 移除之前界面上的内容，第0个不移除，表示上方内容容器
        for (i in childCount - 1 downTo 1) {
            removeViewAt(i)
        }
        this.tabSelectedChangeListeners.forEach {
            if (it is HiTabItemBottom) {
                tabSelectedChangeListeners.remove(it)
            }
        }

        // 添加背景
        addBackground()

        // 创建承载Item的FrameLayout ll，并逐个添加tabItemBottom
        val height = HiDisplayUtil.dp2px(tabBottomHeight, resources)
        val ll = FrameLayout(context)
        ll.tag = TAG_TAB_BOTTOM
        val width = HiDisplayUtil.getDisplayWidthInPx(context) / infoList.size
        this.infoList?.forEachIndexed { i, info ->
            val params = LayoutParams(width, height, Gravity.BOTTOM)
            params.leftMargin = i * width
            val tabItemBottom = HiTabItemBottom(context)
            tabItemBottom.setHiTabInfo(info)
            tabItemBottom.setOnClickListener { onSelected(info) }
            tabSelectedChangeListeners.add(tabItemBottom)
            ll.addView(tabItemBottom, params)
        }
        val llParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, Gravity.BOTTOM)
        addView(ll, llParams)

        // 加条线
        addBottomLine()

        // 修复最后一个view的展示
        fixContentView()
    }

    private fun addBackground() {
        val view = LayoutInflater.from(context).inflate(R.layout.hi_bottom_layout_bg, null)
        val params = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            HiDisplayUtil.dp2px(this.tabBottomHeight, resources),
            Gravity.BOTTOM)
        addView(view, params)
        view.alpha = this.bottomAlpha
    }

    private fun addBottomLine() {
        val bottomLine = View(context)
        bottomLine.setBackgroundColor(Color.parseColor(this.bottomLineColor))
        val bottomLineParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            HiDisplayUtil.dp2px(this.bottomLineHeight, resources),
            Gravity.BOTTOM)
        addView(bottomLine, bottomLineParams)
        bottomLine.alpha = this.bottomAlpha
    }

    private fun onSelected(@NonNull nextInfo: HiTabBottomInfo<*>) {
        tabSelectedChangeListeners.forEach {
            infoList?.indexOf(nextInfo)?.let { it1 -> it.onTabSelectedChange(it1, selectInfo, nextInfo) }
        }
        this.selectInfo = nextInfo
    }

    private fun fixContentView() {
        if (getChildAt(0) !is ViewGroup) return
        val rootView : ViewGroup = getChildAt(0) as ViewGroup
        val targetView = HiViewUtil.findTypeView(rootView, RecyclerView::class.java)
                ?: HiViewUtil.findTypeView(rootView, ScrollView::class.java)
                ?: HiViewUtil.findTypeView(rootView, AbsListView::class.java)
        targetView?.setPadding(0, 0, 0, HiDisplayUtil.dp2px(this.tabBottomHeight, resources))
        targetView?.clipToPadding = false
    }
}