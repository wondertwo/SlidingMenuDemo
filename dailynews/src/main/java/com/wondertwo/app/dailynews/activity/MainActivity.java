package com.wondertwo.app.dailynews.activity;

import android.os.Bundle;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.wondertwo.app.dailynews.R;
import com.wondertwo.app.dailynews.fragment.LeftFragment;
import com.wondertwo.app.dailynews.fragment.ContentFragment;

/**
 * 主页面
 * Created by wondertwo on 2016/3/5.
 */
public class MainActivity extends SlidingFragmentActivity {

    private static final String FRAGMENT_LEFT_MENU = "fragment_left";
    private static final String FRAGMENT_NEWS = "fragment_content";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 取消标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置全屏显示
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //         WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // 隐藏NavigationBar
        // View decorView = getWindow().getDecorView();
        // int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        //         | View.SYSTEM_UI_FLAG_FULLSCREEN;
        // decorView.setSystemUiVisibility(uiOptions);

        setBehindContentView(R.layout.activity_left);// 设置侧边栏布局
        // 获取侧边栏对象
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setTouchModeAbove(slidingMenu.TOUCHMODE_FULLSCREEN);// 设置模式为全屏触摸
        // 设置屏幕预留宽度=屏幕宽度的1/3
        slidingMenu.setBehindOffset(getWindowManager().getDefaultDisplay().getWidth() / 3);

        // 初始化Fragment
        initFragment();
    }

    // 初始化Fragment
    private void initFragment() {
        // 获取FragmentManager
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction =
                fm.beginTransaction();// 开启fragment事务

        // 用Fragment实例替换空布局
        transaction.replace(R.id.fl_news, new ContentFragment(), FRAGMENT_NEWS);
        transaction.replace(R.id.fl_left, new LeftFragment(), FRAGMENT_LEFT_MENU);

        transaction.commit();
    }
}
