package com.wondertwo.app.dailynews.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Activity基类
 * Created by wondertwo on 2016/3/10.
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}
