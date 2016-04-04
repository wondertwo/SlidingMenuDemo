package com.wondertwo.app.dailynews.base.impl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import com.wondertwo.app.dailynews.activity.ShowPhoto;
import com.wondertwo.app.dailynews.base.BasePager;

/**
 * 我的页面实现类
 * Created by wondertwo on 2016/3/8.
 */
public class MinePager extends BasePager {

    public MinePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        // 设置标题栏内容
        tvTitle.setText("设置");
        btnLeftMenu.setVisibility(View.GONE);
        setSlidingMenuEnable(false);// 关闭侧边栏

        /*// 设置内容区布局
        TextView text = new TextView(mActivity);
        text.setText("设置页");
        text.setTextColor(Color.RED);
        text.setTextSize(20);
        text.setGravity(Gravity.CENTER);*/

        Button showPhoto = new Button(mActivity);
        showPhoto.setText("show my photo");
        showPhoto.setTextColor(Color.RED);
        showPhoto.setHeight(24);
        // 设置按钮点击事件
        showPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到显示图片
                mActivity.startActivity(new Intent(mActivity, ShowPhoto.class));
            }
        });

        // 向FrameLayout中动态的添加布局
        // flContent.addView(text);
        flContent.addView(showPhoto);
    }
}
