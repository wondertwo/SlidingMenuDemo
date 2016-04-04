package com.wondertwo.app.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.wondertwo.app.news.R;

/**
 * Created by wondertwo on 2016/3/2.
 */
public class SplashActivity extends BaseActivity {

    private RelativeLayout rlSplash;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.d("SplashActivity", "这是闪屏页");
        setContentView(R.layout.splash_activity);

        rlSplash = (RelativeLayout) findViewById(R.id.rl_splash);

        // 开启动画
        startAnimation();
    }

    /**
     * 闪屏页启动动画
     */
    private void startAnimation() {
        // 创建动画集合
        AnimationSet aniSet = new AnimationSet(false);// false表示不共享动画插补器

        // 旋转动画
        RotateAnimation rotate = new RotateAnimation(0, 360,
                                    Animation.RELATIVE_TO_SELF, 0.5f,
                                    Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(5000);// 设置时间
        rotate.setFillAfter(true);

        // 缩放动画
        ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1,
                                    Animation.RELATIVE_TO_SELF, 0.5f,
                                    Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(5000);
        scale.setFillAfter(true);

        // 渐变动画
        AlphaAnimation alpha = new AlphaAnimation(0, 1);// 透明度从0到1
        alpha.setDuration(5000);
        alpha.setFillAfter(true);

        // 把动画添加到集合
        aniSet.addAnimation(rotate);
        aniSet.addAnimation(scale);
        aniSet.addAnimation(alpha);

        // 设置动画监听，动画结束后跳转到新手引导页
        aniSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            // 动画结束，跳转到新手引导页
            @Override
            public void onAnimationRepeat(Animation animation) {
                startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                // 结束当前Activity
                finish();
            }
        });

        // 根布局调用startAnimation()方法，开启动画
        rlSplash.startAnimation(aniSet);
    }
}
