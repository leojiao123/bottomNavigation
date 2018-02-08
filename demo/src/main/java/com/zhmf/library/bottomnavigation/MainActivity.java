package com.zhmf.library.bottomnavigation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    BottomNavigationBar bottom_navigation_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottom_navigation_bar = findViewById(R.id.bottom_navigation_bar);



        BottomNavigationItem item1 = new BottomNavigationItem()
                .setIconActiveDrawable(getResources().getDrawable(R.mipmap.tab_home_selected))
                .setIconInactiveDrawable(getResources().getDrawable(R.mipmap.tab_home))
                .setLabelInActiveColor(Color.parseColor("#000000"))
                .setLabelActiveColor(Color.parseColor("#FF0000"))
                .setTitle("首页")
                .setIconWidth(25);

        BottomNavigationItem item2 = new BottomNavigationItem()
                .setIconActiveDrawable(getResources().getDrawable(R.mipmap.tab_city_selected))
                .setIconInactiveDrawable(getResources().getDrawable(R.mipmap.tab_city))
                .setLabelInActiveColor(Color.parseColor("#000000"))
                .setLabelActiveColor(Color.parseColor("#FF0000"))
                .setTitle("城市")
                .setIconWidth(25);

        BottomNavigationItem item3 = new BottomNavigationItem()
                .setIconActiveDrawable(getResources().getDrawable(R.mipmap.tab_post))
                .setIconInactiveDrawable(getResources().getDrawable(R.mipmap.tab_post))
                .setLabelInActiveColor(Color.parseColor("#000000"))
                .setLabelActiveColor(Color.parseColor("#FF0000"))
                .setTitle("发布")
                .setIconWidth(65);

        BottomNavigationItem item4 = new BottomNavigationItem()
                .setIconActiveDrawable(getResources().getDrawable(R.mipmap.tab_find_selected))
                .setIconInactiveDrawable(getResources().getDrawable(R.mipmap.tab_find))
                .setLabelInActiveColor(Color.parseColor("#000000"))
                .setLabelActiveColor(Color.parseColor("#FF0000"))
                .setTitle("发现")
                .setIconWidth(25);

        BottomNavigationItem item5 = new BottomNavigationItem()
                .setIconActiveDrawable(getResources().getDrawable(R.mipmap.tab_person_selected))
                .setIconInactiveDrawable(getResources().getDrawable(R.mipmap.tab_person))
                .setLabelInActiveColor(Color.parseColor("#000000"))
                .setLabelActiveColor(Color.parseColor("#FF0000"))
                .setTitle("我的")
                .setIconWidth(25);

        bottom_navigation_bar.addItem(item1).addItem(item2).addItem(item3).addItem(item4).addItem(item5);
        bottom_navigation_bar.setFirstSelectedPosition(0);
        bottom_navigation_bar.initialise();

        bottom_navigation_bar.setTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                Log.i("当前选中", String.valueOf(position));
            }

            @Override
            public void onTabUnselected(int position) {
                Log.i("当前取消选中", String.valueOf(position));
            }

            @Override
            public void onTabReselected(int position) {
                Log.i("当前再次选中", String.valueOf(position));
            }
        });

    }
}
