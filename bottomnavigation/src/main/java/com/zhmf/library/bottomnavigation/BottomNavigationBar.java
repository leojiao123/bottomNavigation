package com.zhmf.library.bottomnavigation;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by leo on 2018/2/7.
 */

public class BottomNavigationBar extends FrameLayout {


    private LinearLayout mTabContainer; // 底部布局的container

    ArrayList<BottomNavigationTab> mBottomNavigationTabs = new ArrayList<>();
    ArrayList<BottomNavigationItem> mBottomNavigationItems = new ArrayList<>();

    private static final int DEFAULT_SELECTED_POSITION = -1;
    private int mFirstSelectedPosition = 0; // 初始化定位位置
    private int mSelectedPosition = DEFAULT_SELECTED_POSITION; // 当前选择位置

    private OnTabSelectedListener mTabSelectedListener;

    public BottomNavigationBar(Context context) {
        this(context, null);
    }

    public BottomNavigationBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BottomNavigationBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    /*
    * 初始化
    * */
    private void init() {
        setLayoutParams(new ViewGroup.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)));
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View parentView = inflater.inflate(R.layout.bottom_navigation_bar_container, this, true);
        mTabContainer = (LinearLayout) parentView.findViewById(R.id.bottom_navigation_bar_item_container);
        setClipToPadding(false); // 设置滚动区域是否包含padding
    }

    public BottomNavigationBar setTabSelectedListener(OnTabSelectedListener tabSelectedListener) {
        this.mTabSelectedListener = tabSelectedListener;
        return this;
    }


    /*
    *  在所有的配置文件完成之后调用
    *  调用此方法会重汇tab
    * */

    public void initialise() {

        mBottomNavigationTabs.clear();

        if (mBottomNavigationItems != null && mBottomNavigationItems.size() > 0) {
            mTabContainer.removeAllViews();

            int screenWidth = Utils.getScreenWidth(getContext());
            int itemWidth = screenWidth / mBottomNavigationItems.size();

            /*
            * 创建tab
            * */

            for (BottomNavigationItem currentItem : mBottomNavigationItems) {
                BottomNavigationTab bottomNavigationTab = new BottomNavigationTab(getContext());
                setUpTab(bottomNavigationTab, currentItem, itemWidth);
            }

            /*
            * 处理点击事件
            * */

            if (mBottomNavigationTabs.size() > mFirstSelectedPosition) {
                selectTabInternal(mFirstSelectedPosition, true, false, false);
            } else if (!mBottomNavigationTabs.isEmpty()) {
                selectTabInternal(0, true, false, false);
            }


        }


    }


    private void setUpTab(BottomNavigationTab bottomNavigationTab, BottomNavigationItem currentItem, int itemWidth) {
        bottomNavigationTab.setPosition(mBottomNavigationItems.indexOf(currentItem));

        bottomNavigationTab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavigationTab bottomNavigationTabView = (BottomNavigationTab) v;
                selectTabInternal(bottomNavigationTabView.getPosition(), false, true, false); // 处理点击事件
            }
        });

        mBottomNavigationTabs.add(bottomNavigationTab);

        bottomNavigationTab.setTitle(currentItem.getTitle());

        bottomNavigationTab.setIcon(currentItem.getIconActiveDrawable());

        bottomNavigationTab.setInactiveIcon(currentItem.getIconInactiveDrawable());

        bottomNavigationTab.setActiveColor(currentItem.getLabelActiveColor());

        bottomNavigationTab.setInactiveColor(currentItem.getLabelInActiveColor());

        bottomNavigationTab.setBadgeIcon(currentItem.getBadgeDrawable());

        bottomNavigationTab.setItemWith(itemWidth);

        bottomNavigationTab.setIconWidth(currentItem.getIconWidth());

        bottomNavigationTab.initialise();

        mTabContainer.addView(bottomNavigationTab);
    }


    /**
     * 处理点击事件
     *
     * @param newPosition
     * @param firstTab
     * @param callListener
     * @param forcedSelection
     */
    private void selectTabInternal(int newPosition, boolean firstTab, boolean callListener, boolean forcedSelection) {
        int oldPosition = mSelectedPosition;

        if (mSelectedPosition != newPosition) {
            // 将原来的置为未选中
            if (mSelectedPosition != -1) {
                mBottomNavigationTabs.get(mSelectedPosition).unSelect(true);
            }
            // 现有的置为选中
            mBottomNavigationTabs.get(newPosition).select(true);
            mSelectedPosition = newPosition;
        }

        if (callListener) {
            sendListenerCall(oldPosition, newPosition, forcedSelection);
        }

    }


    private void sendListenerCall(int oldPosition, int newPosition, boolean forcedSelection) {
        if (mTabSelectedListener != null) {
            if (forcedSelection) {
                mTabSelectedListener.onTabSelected(newPosition);
            } else {
                if (oldPosition == newPosition) {
                    mTabSelectedListener.onTabReselected(newPosition);
                } else {
                    mTabSelectedListener.onTabSelected(newPosition);
                    if (oldPosition != -1) {
                        mTabSelectedListener.onTabUnselected(oldPosition);
                    }
                }
            }
        }
    }


    public BottomNavigationBar setFirstSelectedPosition(int firstSelectedPosition) {
        this.mFirstSelectedPosition = firstSelectedPosition;
        return this;
    }

    /*
    * 清空状态
    * */
    public void clearAll() {
        mTabContainer.removeAllViews();
        mBottomNavigationTabs.clear();
        mBottomNavigationItems.clear();
        mSelectedPosition = DEFAULT_SELECTED_POSITION;
    }

    /**
     * 添加一个 item
     *
     * @param item
     * @return
     */
    public BottomNavigationBar addItem(BottomNavigationItem item) {
        mBottomNavigationItems.add(item);
        return this;
    }

    /**
     * 移除一个item
     *
     * @param item
     * @return
     */
    public BottomNavigationBar removeItem(BottomNavigationItem item) {
        mBottomNavigationItems.remove(item);
        return this;
    }

}
