package com.zhmf.library.bottomnavigation;


import android.graphics.drawable.Drawable;

/**
 * Created by leo on 2018/2/7.
 */

public class BottomNavigationItem {

    private Drawable mIconInactiveDrawable; // 闲置状态图标

    private Drawable mIconActiveDrawable;  // 激活状态图标

    private String mTitle; // 标题

    private int mLabelInActiveColor; // 闲置状态图标

    private int mLabelActiveColor;  // 激活状态图标

    private Drawable mBadgeDrawable;  // 右上角角标图

    private String position; // 对应位置  现有 1,2,3,4,5

    private int itemWidth = 25; // 默认给25的宽高


    public Drawable getIconInactiveDrawable() {
        return mIconInactiveDrawable;
    }

    public BottomNavigationItem setIconInactiveDrawable(Drawable mIconInactiveDrawable) {
        this.mIconInactiveDrawable = mIconInactiveDrawable;
        return this;
    }

    public Drawable getIconActiveDrawable() {
        return mIconActiveDrawable;
    }

    public BottomNavigationItem setIconActiveDrawable(Drawable mIconActiveDrawable) {
        this.mIconActiveDrawable = mIconActiveDrawable;
        return this;
    }

    public String getTitle() {
        return mTitle;
    }

    public BottomNavigationItem setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public int getLabelInActiveColor() {
        return mLabelInActiveColor;
    }

    public BottomNavigationItem setLabelInActiveColor(int mLabelInActiveColor) {
        this.mLabelInActiveColor = mLabelInActiveColor;
        return this;
    }

    public int getLabelActiveColor() {
        return mLabelActiveColor;
    }

    public BottomNavigationItem setLabelActiveColor(int mLabelActiveColor) {
        this.mLabelActiveColor = mLabelActiveColor;
        return this;
    }

    public Drawable getBadgeDrawable() {
        return mBadgeDrawable;
    }

    public BottomNavigationItem setBadgeDrawable(Drawable mBadgeDrawable) {
        this.mBadgeDrawable = mBadgeDrawable;
        return this;

    }

    public String getPosition() {
        return position;
    }

    public BottomNavigationItem setPosition(String position) {
        this.position = position;
        return this;
    }

    public BottomNavigationItem setIconWidth(int dp) {
        this.itemWidth = dp;
        return this;
    }

    public int getIconWidth() {
        return itemWidth;
    }


}
