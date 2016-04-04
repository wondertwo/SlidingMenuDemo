package com.wondertwo.app.dailynews.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 封装SharedPreferences工具类
 *
 * Created by wondertwo on 2016/3/5.
 */
public class PrefeUtils {

    public static final String PREFE_NAME = "guide_config";

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(PREFE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(PREFE_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }
}
