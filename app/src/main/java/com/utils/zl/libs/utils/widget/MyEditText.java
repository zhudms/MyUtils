package com.utils.zl.libs.utils.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * Created by TANG on 2016/7/16.
 * Nurse
 */
public class MyEditText extends EditText {
    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()){
            return false;
        }
        return super.onTouchEvent(event);
    }
}
