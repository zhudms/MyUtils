package com.utils.zl.libs.utils;

import android.util.Log;

/**
 * 带控制的Log类，统一控制
 * Created by Administrator on 2016/3/2.
 */
public class LogUtil {

    public static void d(String TAG, String message) {
        if (VALUES.IS_DUBUGING) {
            Log.d(TAG, message);
        }

    }

    public static void e(String TAG, String message) {
        if (VALUES.IS_DUBUGING) {
            Log.e(TAG, message);
        }

    }

    public static void w(String TAG, String message) {
        if (VALUES.IS_DUBUGING) {
            Log.w(TAG, message);
        }

    }

    public static void i(String TAG, String message) {
        if (VALUES.IS_DUBUGING) {
            Log.i(TAG, message);
        }

    }
}
