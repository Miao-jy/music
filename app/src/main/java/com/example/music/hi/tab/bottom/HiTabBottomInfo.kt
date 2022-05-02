package com.example.music.hi.tab.bottom

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.fragment.app.Fragment

class HiTabBottomInfo<Color>() {

    enum class TabType {
        BITMAP, ICON
    }

    var name: String = "name"
    lateinit var fragment: Class<out Fragment>
    var defaultBitmap: Bitmap? = null
    var selectedBitmap: Bitmap? = null
    var iconFont: String = "iconfont"
    var defaultIconName: String = "jian"
    var selectedIconName: String = "bing"
    var defaultColor: Color? = null
    var tintColor: Color? = null
    var tabType: TabType = TabType.ICON

    constructor(name: String, defaultBitmap: Bitmap, selectedBitmap: Bitmap) : this() {
        this.name = name
        this.defaultBitmap = defaultBitmap
        this.selectedBitmap = selectedBitmap
        this.tabType = TabType.BITMAP
    }

    constructor(
        name: String,
        iconFont: String,
        defaultIconName: String,
        selectedIconName: String,
        defaultColor: Color,
        tintColor: Color
    ) : this() {
        this.name = name
        this.iconFont = iconFont
        this.defaultIconName = defaultIconName
        this.selectedIconName = selectedIconName
        this.defaultColor = defaultColor
        this.tintColor = tintColor
    }

}