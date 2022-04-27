package com.example.music.hi.tab.common;

import androidx.annotation.NonNull;
import androidx.annotation.Px;

public interface IHiTabItem<D> extends IHiTabLayout.OnTabSelectedListener<D> {
    void setHiTabInfo(@NonNull D data);

    void resetHeight(@Px int height);

}
