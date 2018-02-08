package com.zhmf.library.bottomnavigation;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by leo on 2018/2/7.
 */

public class BottomNavigationTab extends FrameLayout {

    private LinearLayout mContainerView;
    private TextView tv_title;
    private ImageView iv_badge;
    private ImageView iv_icon;

    private String title;

    private int mPosition;
    private int mActiveColor;
    private int mInActiveColor;
    private Drawable mCompactIcon;
    private Drawable mCompactInActiveIcon;
    private Drawable mBadgeIcon;
    private boolean isActive = false;
    private boolean isInActiveIconSet = false;
    private int itemWidth = -1;
    private int iconWidth = 25;


    public BottomNavigationTab(@NonNull Context context) {
        this(context, null);
    }

    public BottomNavigationTab(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavigationTab(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BottomNavigationTab(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.bottom_navigation_item, this, true);
        mContainerView = (LinearLayout) view.findViewById(R.id.ll_bottom_navigation_item_container);
        iv_icon = (ImageView) view.findViewById(R.id.bottom_navigation_icon);
        iv_badge = (ImageView) view.findViewById(R.id.bottom_navigation_badge_icon);
        tv_title = (TextView) view.findViewById(R.id.bottom_navigation_title);
    }


    public void setTitle(String title) {
        this.title = title;
        tv_title.setText(title);
    }

    /*
    * 设置激活状态颜色
    * */
    public void setActiveColor(int activeColor) {
        mActiveColor = activeColor;
    }

    /*
    * 设置normal 颜色
    * */
    public void setInactiveColor(int inActiveColor) {
        mInActiveColor = inActiveColor;
        tv_title.setTextColor(inActiveColor);
    }

    public void setIcon(Drawable drawable) {
        mCompactIcon = DrawableCompat.wrap(drawable);
    }

    public void setInactiveIcon(Drawable icon) {
        mCompactInActiveIcon = DrawableCompat.wrap(icon);
        isInActiveIconSet = true;
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    public int getPosition() {

        return mPosition;
    }


    public void setBadgeIcon(Drawable badgeIcon) {
        this.mBadgeIcon = badgeIcon;
        iv_badge.setImageDrawable(badgeIcon);
    }

    /*
    * 选中状态
    * */
    public void select(boolean setActiveColor) {
        isActive = true;
        iv_icon.setSelected(true);
        //判断是否设置了激活时的颜色
        if (setActiveColor) {
            tv_title.setTextColor(mActiveColor);
        } else {
            tv_title.setTextColor(mInActiveColor);
        }
    }

    /*
    * 未选中状态
    * */
    public void unSelect(boolean setActiveColor) {
        isActive = false;
        tv_title.setTextColor(mInActiveColor);
        iv_icon.setSelected(false);
    }

    public void setIconWidth(int widthDP) {
        this.iconWidth = widthDP;
    }


    public int getItemWidth() {
        return iconWidth;
    }


    @CallSuper
    public void initialise() {

        if (itemWidth != -1) {
            setLayoutParams(new ViewGroup.LayoutParams(itemWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        ViewGroup.LayoutParams layoutParams = iv_icon.getLayoutParams();
        layoutParams.width = Utils.dp2px(getContext(), iconWidth);
        layoutParams.height = Utils.dp2px(getContext(), iconWidth);
        iv_icon.setLayoutParams(layoutParams);

        iv_icon.setSelected(false); // 初始化未选中
        if (isInActiveIconSet) {
            StateListDrawable states = new StateListDrawable();
            states.addState(new int[]{android.R.attr.state_selected},
                    mCompactIcon);
            states.addState(new int[]{-android.R.attr.state_selected},
                    mCompactInActiveIcon);
            states.addState(new int[]{},
                    mCompactInActiveIcon);
            iv_icon.setImageDrawable(states);
        } else {  // 没有配置激活状态颜色的话，自己改变drawable的颜色
            DrawableCompat.setTintList(mCompactIcon, new ColorStateList(
                    new int[][]{
                            new int[]{android.R.attr.state_selected}, //1
                            new int[]{-android.R.attr.state_selected}, //2
                            new int[]{}
                    },
                    new int[]{
                            mActiveColor, //1
                            mInActiveColor, //2
                            mInActiveColor //3
                    }
            ));
            iv_icon.setImageDrawable(mCompactIcon);
        }
        // no title model 暂未启用
    }

    public void setItemWith(int itemWidth) {
        this.itemWidth = itemWidth;
    }


}
