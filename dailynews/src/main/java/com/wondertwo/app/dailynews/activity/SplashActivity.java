package com.wondertwo.app.dailynews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.wondertwo.app.dailynews.R;
import com.wondertwo.app.dailynews.utils.PrefeUtils;

/**
 * 闪屏页
 * Created by wondertwo on 2016/3/3.
 */
public class SplashActivity extends BaseActivity {

    private RelativeLayout rlSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        rlSplash = (RelativeLayout) findViewById(R.id.rl_splash);

        // 开启闪屏页动画
        startSplashAnimation();
    }

    /**
     * 闪屏页启动动画
     */
    private void startSplashAnimation() {
        // 创建动画集合
        AnimationSet aniSet = new AnimationSet(false);// false表示不共享动画插补器

        // 旋转动画
        RotateAnimation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(4000);// 设置时间
        rotate.setFillAfter(true);

        // 缩放动画
        ScaleAnimation scale = new ScaleAnimation(0.5f, 1, 0.5f, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(4000);
        scale.setFillAfter(true);

        // 渐变动画
        AlphaAnimation alpha = new AlphaAnimation(0.5f, 1);// 透明度从0.5到1
        alpha.setDuration(4000);
        alpha.setFillAfter(true);

        // 把动画添加到集合
        aniSet.addAnimation(rotate);
        aniSet.addAnimation(scale);
        aniSet.addAnimation(alpha);

        // 设置动画监听，动画结束后跳转到新手引导页
        aniSet.setAnimationListener(new Animation.AnimationListener() {
            // 动画开始
            @Override
            public void onAnimationStart(Animation animation) {
            }

            // 动画重复
            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            // 动画结束，跳转到新手引导页
            @Override
            public void onAnimationEnd(Animation animation) {
                // 调用startNextActivity()方法，跳转到下一个Activity
                startNextActivity();
            }
        });
        // 通过根布局调用startAnimation()方法，开启动画
        rlSplash.startAnimation(aniSet);
    }

    /**
     * 跳转到下一个Activity,在这里判断是否要展示新手引导页
     */
    private void startNextActivity() {
        // 判断是否要显示新手引导页
        boolean isGuideShow = PrefeUtils.getBoolean(this, "guide_config", false);

        if (!isGuideShow) {
            // 新手引导页没有展示过，跳转到新手引导页
            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
        } else {
            // 新手引导页已经展示过，跳转到主页面
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }
        finish();
    }
}
