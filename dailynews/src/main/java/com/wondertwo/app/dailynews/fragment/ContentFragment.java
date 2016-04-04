package com.wondertwo.app.dailynews.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.wondertwo.app.dailynews.R;
import com.wondertwo.app.dailynews.base.BasePager;
import com.wondertwo.app.dailynews.base.impl.FindPager;
import com.wondertwo.app.dailynews.base.impl.MinePager;
import com.wondertwo.app.dailynews.base.impl.NewsPager;

import java.util.ArrayList;

/**
 * NewsFragment主页面新闻页fragment
 * Created by wondertwo on 2016/3/6.
 */
public class ContentFragment extends BaseFragment {

    // 拿到RadioGroup对象
    private RadioGroup rgBottomBar;
    // 拿到ViewPager对象
    private ViewPager mViewPager;
    // 定义集合mPagerList，接收BasePager对象
    private ArrayList<BasePager> mPagerList;

    // 初始化View
    @Override
    public View initViews() {
        View view  = View.inflate(mActivity, R.layout.fragment_content, null);

        rgBottomBar = (RadioGroup) view.findViewById(R.id.rg_bottom_tab);// 拿到RadioGroup对象
        mViewPager = (ViewPager) view.findViewById(R.id.vp_content);// 拿到ViewPager对象

        return view;
    }

    // 初始化数据
    @Override
    public void initData() {
        rgBottomBar.check(R.id.rb_news);// 表示默认进入页面是新闻页

        mPagerList = new ArrayList<>();
        mPagerList.add(new FindPager(mActivity));
        mPagerList.add(new NewsPager(mActivity));
        mPagerList.add(new MinePager(mActivity));

        // ViewPager设置适配器
        mViewPager.setAdapter(new ContentAdapter());

        // 监听RadioGroup的选择事件
        rgBottomBar.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rb_find:
                                mViewPager.setCurrentItem(0, false);// 设置当前页面
                                break;
                            case R.id.rb_news:
                                mViewPager.setCurrentItem(1, false);// 设置当前页面
                                break;
                            case R.id.rb_mine:
                                mViewPager.setCurrentItem(2, false);// 设置当前页面
                                break;
                            default:
                                break;
                        }
                    }
                }
        );

        // 默认初始化新闻页数据
        // mPagerList.get(1).initData();
        // new NewsPager(mActivity).initData();
        getNewsPager().initData();

        // 设置ViewPager的页面变化监听
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // 当页面被选中，初始化页面数据
                mPagerList.get(position).initData();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    // 获取新闻页对象,并初始化新闻页数据
    public NewsPager getNewsPager() {
        return (NewsPager) mPagerList.get(1);
    }

    /**
     * ViewPager适配器
     */
    class ContentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) { return view == object; }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager basePager = mPagerList.get(position);
            container.addView(basePager.mRootView);
            // basePager.initData();// 注意不能在这里initData(),否则会预加载一个页面
            return basePager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
