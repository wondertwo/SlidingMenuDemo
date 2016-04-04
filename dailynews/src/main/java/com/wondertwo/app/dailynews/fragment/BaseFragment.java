package com.wondertwo.app.dailynews.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * BaseFragment基类，所有fragment都要继承自BaseFragment
 * Created by wondertwo on 2016/3/6.
 */
public abstract class BaseFragment extends Fragment {

    // fragment所依附的activity对象
    public Activity mActivity;

    // fragment创建
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    // 创建fragment布局
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return initViews();
    }

    // fragment所依附的activity被创建
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    // 初始化View的抽象方法，由子类实现
    public abstract View initViews();
    // 初始化数据的方法
    public void initData() {}
}
