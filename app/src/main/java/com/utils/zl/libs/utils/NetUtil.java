package com.utils.zl.libs.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 判断当前网络状况
 * Created by TANG on 2016/3/9.
 */
public class NetUtil {

    public static boolean isNetworkUseful(Context mContext) {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        boolean isUseful = ni != null && ni.isConnectedOrConnecting();
//        ToastUtil.toast(mContext,"isUseful="+isUseful);
        LogUtil.d(VALUES.TAG_FILTER, "isNetUseful=" + isUseful);
        return isUseful;

    }
}
