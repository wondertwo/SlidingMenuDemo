package com.wondertwo.app.dailynews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wondertwo.app.dailynews.R;
import com.wondertwo.app.dailynews.utils.PrefeUtils;

import java.util.ArrayList;

/**
 * 新手引导页
 * Created by wondertwo on 2016/3/3.
 */
public class GuideActivity extends BaseActivity {

    private static final int[] ivGuides = new int[]{R.drawable.guide_1,
            R.drawable.guide_2,
            R.drawable.guide_3};
    private ViewPager vpGuide;
    private ArrayList<ImageView> ivGuideList;

    private RelativeLayout rlGuide;// 新手引导页根布局对象

    private LinearLayout llPointGroup;// 引导页小圆点父控件
    private View rlRedPoint;// 引导页的小红圆点
    private int pointWidth;// 引导页两个小圆点间距
    private Button btStart;// 开始体验按钮

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);

        vpGuide = (ViewPager) findViewById(R.id.vp_guide);
        llPointGroup = (LinearLayout) findViewById(R.id.ll_guide_point);
        rlGuide = (RelativeLayout) findViewById(R.id.rl_guide);
        rlRedPoint = findViewById(R.id.rl_red_point);
        btStart = (Button) findViewById(R.id.bt_start);

        // 设置开始体验按钮的点击监听事件
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 更新SharedPreferences，表示已经展示过新手引导页
                PrefeUtils.setBoolean(GuideActivity.this, "guide_config", true);

                // 跳转到主页面
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                // 结束当前Activity
                finish();
            }
        });

        // 初始化引导页的3个背景
        initViews();
        // 给ViewPager设置页面适配器
        vpGuide.setAdapter(new GuideAdapter());
        // 给ViewPager设置页面变化监听
        vpGuide.addOnPageChangeListener(new GuideChangeListener());
    }

    /**
     * ViewPager适配器
     */
    class GuideAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return ivGuides.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(ivGuideList.get(position));
            return ivGuideList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    /**
     * 为新手引导页的ViewPager准备数据
     */
    private void initViews() {
        // 创建ImageView集合实例，收集ImageView对象
        ivGuideList = new ArrayList<>();

        // for循环遍历图片数组，将图片设置为ImageView的背景
        for (int i = 0; i < ivGuides.length; i++) {
            ImageView view = new ImageView(this);
            view.setBackgroundResource(ivGuides[i]);
            ivGuideList.add(view);
        }

        // for循环设置引导页小圆点
        for (int i = 0; i < ivGuides.length; i++) {
            View point = new View(this);
            point.setBackgroundResource(R.drawable.guide_point_gray);// 设置小圆点资源
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);// 创建params
            if (i > 0) {
                // 从第二个小圆点开始，左边距设置为14
                params.leftMargin = 14;
            }
            point.setLayoutParams(params);
            llPointGroup.addView(point);
        }

        // 通设置GlobalLayoutListener全局布局监听的onGlobalLayout()方法，动态获取小圆点间距
        llPointGroup.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        pointWidth = llPointGroup.getChildAt(1).getLeft() -
                                llPointGroup.getChildAt(0).getLeft();
                        llPointGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        // Log.d("获取小圆点间距", "" + pointWidth);
                    }
                });
    }

    /**
     * ViewPager.OnPageChangeListener实现类，监听页面滑动事件
     */
    class GuideChangeListener implements ViewPager.OnPageChangeListener {

        // 监听页面滑动,参数分别是当前位置(0,1,2),滑动的百分比，百分比像素
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // 通过给小红点设置左边距实现
            int leftWidth = (int) (pointWidth * positionOffset) + (pointWidth * position);// 计算小红点移动的宽度值
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)
                    rlRedPoint.getLayoutParams();// 获取小红点的layoutparams参数
            params.leftMargin = leftWidth;
            rlRedPoint.setLayoutParams(params);// 动态修改小红圆点的左边距，实现滑动翻页效果
        }

        // 监听页面被选中事件,只在最后那个引导页显示开始体验Button
        @Override
        public void onPageSelected(int position) {
            if (position == ivGuides.length - 1) {
                btStart.setVisibility(View.VISIBLE);
            } else {
                btStart.setVisibility(View.INVISIBLE);
            }
        }

        // 监听页面滑动状态变化事件
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}