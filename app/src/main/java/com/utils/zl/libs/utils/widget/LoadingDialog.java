package com.utils.zl.libs.utils.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.utils.zl.libs.R;


/**
 * Created by TANG on 2016/5/22.
 */
public class LoadingDialog extends Dialog {
    public LoadingDialog(Context context) {
        super(context);
        View v= LayoutInflater.from(context).inflate(R.layout.loading_dialog,null,false);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(v);
    }
}
