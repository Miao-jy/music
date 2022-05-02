package com.example.music.hi.tab.common

import android.view.ViewGroup
import androidx.annotation.NonNull

interface IHiTabLayout<Tab : ViewGroup, D> {
    fun findTab(@NonNull data: D): Tab?

    fun addTabSelectedChangeListener(listener: OnTabSelectedListener<D>)

    fun defaultSelected(@NonNull defaultInfo: D)

    fun inflateInfo(@NonNull infoList: List<D>)

    interface OnTabSelectedListener<D> {
        fun onTabSelectedChange(index: Int, @NonNull prevInfo: D, @NonNull nextInfo: D)
    }
}