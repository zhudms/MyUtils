package com.utils.zl.libs.utils.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

import com.zxyilian.nurse.R;
import com.zxyilian.nurse.util.ToastUtil;
import com.zxyilian.nurse.util.VALUES;

/**
 * Created by TANG on 2016/7/19.
 * Nurse
 */
public class MyTextWatcher implements TextWatcher {


    private Context mContext;

    public MyTextWatcher(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (start+count>= VALUES.MAXEDIT_LENGTH){
            ToastUtil.toast(mContext, R.string.input_max);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}

