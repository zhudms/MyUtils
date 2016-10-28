package com.utils.zl.libs.utils.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.utils.zl.libs.R;


/**
 * 添加患者界面床位控件
 * Created by TANG on 2016/3/16.
 */
public class TendBedTextView extends RelativeLayout {


    public static final int STATE_UNABLE = 0;
    public static final int STATE_NORMAL = 1;
    public static final int STATE_DEL = 3;
//    public static final int STATE_DELABLE = 4;

    private ImageView mDelImg;
    private TextView numbTv;

    private int mState = STATE_NORMAL;

    public TendBedTextView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.my_bed_text, this, true);
        numbTv = (TextView) findViewById(R.id.my_board_text);
        mDelImg=(ImageView)findViewById(R.id.add_dialog_del);
        flushShow();
    }

    public void flushShow() {

        switch (mState) {
            case STATE_UNABLE:
                numbTv.setEnabled(false);
                mDelImg.setVisibility(View.INVISIBLE);
                break;
            case STATE_DEL:
                mDelImg.setVisibility(View.VISIBLE);
                break;
            case STATE_NORMAL:
                numbTv.setEnabled(true);
                mDelImg.setVisibility(View.INVISIBLE);
                break;

        }
    }


    public void setText(String text) {
        numbTv.setText(text + "");
    }

    public int getmState() {
        return mState;
    }

    public void setmState(int mState) {
        this.mState = mState;
        flushShow();
    }
}
