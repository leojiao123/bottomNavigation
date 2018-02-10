#BottomNavigation
仿照闲鱼的底部菜单栏，可以自定义数目大小,借鉴了以下项目，对原项目感兴趣的的可以去逛下
[https://github.com/Ashok-Varma/BottomNavigation](https://github.com/Ashok-Varma/BottomNavigation "BottomNavigation")

## 主要功能
 - 数目随意定制
 - 顺序随意调整
 - 大小可自定义
 - 文字颜色自定义
## 效果图

![1](https://github.com/leojiao123/bottomNavigation/blob/master/sceenshots/demo.jpg?raw=true)

## 基本使用

	
#### 1.添加maven依赖

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

#### 2.添加Gradle依赖

	dependencies {
	        compile 'com.github.leojiao123:bottomNavigation:v1.0.2'
	}

#### 3.布局中添加

	<?xml version="1.0" encoding="utf-8"?>
	<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
	    xmlns:app="http://schemas.android.com/apk/res-auto"
	    xmlns:tools="http://schemas.android.com/tools"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    tools:context="com.zhmf.library.bottomnavigation.MainActivity">
	
	    <com.zhmf.library.bottomnavigation.BottomNavigationBar
	        android:id="@+id/bottom_navigation_bar"
	        android:layout_width="match_parent"
	        android:layout_height="wrap_content"
	        android:layout_alignParentBottom="true">
	        
	    </com.zhmf.library.bottomnavigation.BottomNavigationBar>
	
	</RelativeLayout>

#### 4.activity 中配置

> 初始化 item:直接 new BottomNavigationItem

 	BottomNavigationItem item1 = new BottomNavigationItem()
                .setIconActiveDrawable(getResources().getDrawable(R.mipmap.tab_home_selected))
                .setIconInactiveDrawable(getResources().getDrawable(R.mipmap.tab_home))
                .setLabelInActiveColor(Color.parseColor("#000000"))
                .setLabelActiveColor(Color.parseColor("#FF0000"))
                .setTitle("首页")
                .setIconWidth(25);

参数说明 
			
	setIconActiveDrawable 选中时显示的图片
	setIconInactiveDrawable 未选中时显示的图片
	setLabelActiveColor 选中时的文字染色
	setLabelInActiveColor 未选中时的文字颜色
	setTitle 标题
	setIconWidth  图片的宽度（宽高1：1）单位 dp

> 添加item到navagationBar,设置默认选中
> 
	  	bottom_navigation_bar.addItem(item1).addItem(item2).addItem(item3).addItem(item4).addItem(item5);
        bottom_navigation_bar.setFirstSelectedPosition(0); // 设置默认选中
        bottom_navigation_bar.initialise(); // 开始绘制并显示


> 设置点击事件

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
