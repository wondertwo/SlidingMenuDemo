package com.wondertwo.app.news.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.wondertwo.app.news.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wondertwo on 2016/3/2.
 */
public class GuideActivity extends BaseActivity {

    // 获取引导页背景图片资源
    private static final int[] ivGuides = new int[]{R.drawable.guide_1,
                                                    R.drawable.guide_2,
                                                    R.drawable.guide_3};
    private ViewPager vpGuide;
    private Button btStart;
    List<ImageView> ivGuideList;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.guide_activity);

        vpGuide = (ViewPager) findViewById(R.id.vp_guide);
        btStart = (Button) findViewById(R.id.bt_start);

        // 初始化引导页背景
        initViews();
        vpGuide.setAdapter(new GuideAdapter());
    }

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
     * 初始化界面
     */
    private void initViews() {
        ivGuideList = new ArrayList<>();
        // for循环初始化三个引导页背景
        for (int i = 0; i < ivGuides.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(ivGuides[i]);// 设置引导页背景
            ivGuideList.add(iv);
        }
    }
}
