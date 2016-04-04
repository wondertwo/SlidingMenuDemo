package com.wondertwo.app.dailynews.fragment;

import android.view.View;

import com.wondertwo.app.dailynews.R;

/**
 * LeftFragment左侧边栏fragment
 * Created by wondertwo on 2016/3/6.
 */
public class LeftFragment extends BaseFragment {

    @Override
    public View initViews() {
        View view = View.inflate(mActivity, R.layout.fragment_left, null);
        return view;
    }
}
