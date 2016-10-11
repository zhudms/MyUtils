package com.utils.zl.myutils.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 控制软键盘
 * Created by TANG on 2016/8/29.
 * newest
 */
public class SoftKeyBoardHelper {

    public static  void showSoftKeyBoard(Context context, View v) {

        if (context==null){
            return;
        }

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);


    }

    public static  void hideSoftKeyBoard(Context context, View v) {
        if (context==null){
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }


}
