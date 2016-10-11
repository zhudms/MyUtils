package com.utils.zl.myutils.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Log;

import com.utils.zl.myutils.R;


/**
 * 获取设备硬件信息
 * Created by TANG on 2016/6/16.
 * PatientLogInDemo
 */
public class LocalDeviceUtil {

    public static String getMacString(Context context) {

        String result = "";
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        result = wifiInfo.getMacAddress();

        if (TextUtils.isEmpty(result)) return result;
        if (result.length() == 0) {
            ToastUtil.toast(context, R.string.get_macid_error);
        }
        Log.e("mac",result);

         return result;

    }


}
