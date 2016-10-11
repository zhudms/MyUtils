package com.utils.zl.myutils.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by TANG on 2016/3/14.
 */
public class ToastUtil {

    public static void toast(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context mContext, int message) {
        Toast.makeText(mContext, mContext.getResources().getString(message), Toast.LENGTH_SHORT).show();
    }

}
