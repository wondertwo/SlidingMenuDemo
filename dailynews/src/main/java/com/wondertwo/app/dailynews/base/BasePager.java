package com.wondertwo.app.dailynews.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.wondertwo.app.dailynews.R;
import com.wondertwo.app.dailynews.activity.MainActivity;

/**
 * 主页面基类
 * Created by wondertwo on 2016/3/8.
 */
public abstract class BasePager {

    // 定义Activity接收传入的Context对象的activity
    public Activity mActivity;
    // 定义主页面根布局对象
    public View mRootView;
    // 标题
    public TextView tvTitle;
    // 内容
    public FrameLayout flContent;
    // 左侧边栏图片按钮
    public ImageButton btnLeftMenu;


    // 构造方法
    public BasePager(Activity activity) {
        mActivity = activity;
        intiViews();
    }

    // 初始化布局
    public void intiViews() {
        mRootView = View.inflate(mActivity, R.layout.base_pager, null);

        tvTitle = (TextView) mRootView.findViewById(R.id.tv_title_text);
        flContent = (FrameLayout) mRootView.findViewById(R.id.fl_base_pager);
        btnLeftMenu = (ImageButton) mRootView.findViewById(R.id.ib_left_menu);

        btnLeftMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSlidingMenu();
            }
        });
    }
    // 切换SlidingMenu的状态
    protected void toggleSlidingMenu() {
        MainActivity newsUi = (MainActivity) mActivity;
        SlidingMenu slidingMenu = newsUi.getSlidingMenu();
        slidingMenu.toggle();// 切换状态，显示时隐藏，隐藏时显示
    }

    // 初始化数据
    public abstract void initData();

    // 设置左侧边栏开或关闭
    public void setSlidingMenuEnable(boolean enable) {
        MainActivity newsUi = (MainActivity) mActivity;
        SlidingMenu slidingMenu = newsUi.getSlidingMenu();
        if (enable) {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }

}
