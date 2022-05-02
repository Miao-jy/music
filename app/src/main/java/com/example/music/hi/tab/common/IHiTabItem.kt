package com.example.music.hi.tab.common

import androidx.annotation.NonNull
import androidx.annotation.Px

interface IHiTabItem<D> : IHiTabLayout.OnTabSelectedListener<D>{
    fun setHiTabInfo(@NonNull info: D)
    fun resetHeight(@Px height: Int)
}