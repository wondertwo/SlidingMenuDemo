package com.wondertwo.app.dailynews.base.impl;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.wondertwo.app.dailynews.base.BasePager;

/**
 * 发现页面实现类
 * Created by wondertwo on 2016/3/8.
 */
public class FindPager extends BasePager {

    public FindPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        // 设置标题栏内容
        tvTitle.setText("发现");
        // btnLeftMenu.setVisibility(View.GONE);
        setSlidingMenuEnable(true);// 打开侧边栏

        // 设置内容区布局
        TextView text = new TextView(mActivity);
        text.setText("发现页");
        text.setTextColor(Color.RED);
        text.setTextSize(20);
        text.setGravity(Gravity.CENTER);

        // 向FrameLayout中动态的添加布局
        flContent.addView(text);
    }
}
