package com.wondertwo.app.dailynews.base.impl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wondertwo.app.dailynews.activity.ShowPhoto;
import com.wondertwo.app.dailynews.base.BasePager;

/**
 * 新闻页实现类
 * Created by wondertwo on 2016/3/8.
 */
public class NewsPager extends BasePager {

    public NewsPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        // 设置标题栏内容
        tvTitle.setText("新闻");
        // btnLeftMenu.setVisibility(View.GONE);
        setSlidingMenuEnable(true);// 打开侧边栏

        // 设置内容区布局
        TextView text = new TextView(mActivity);
        text.setText("新闻页");
        text.setTextColor(Color.RED);
        text.setTextSize(20);
        text.setGravity(Gravity.CENTER);



        // 向FrameLayout中动态的添加布局
        flContent.addView(text);
    }
}
