package com.utils.zl.libs.utils.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.zxyilian.nurse.R;

//import com.zxyilian.nurse.Events.IPDialogOnBackE;

/**
 * Created by TANG on 2016/7/19.
 * Nurse
 */
public class MySingleButtonDialog extends Dialog {
    protected final Button mConfirmBtn;
    protected Context mContext;

    private TextView mMessageTv;
    private OnDialogLCickLisener mDialogLisener;


    public MySingleButtonDialog(Context context, String title,
                                String message, String confirm,
                                OnDialogLCickLisener mLisener) {

        super(context);
        mContext = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        View view = LayoutInflater.from(context).
                inflate(R.layout.singlebutton_dialog, null, false);
        setContentView(view);

        setCanceledOnTouchOutside(false);

        TextView mTitleTv = (TextView) findViewById(R.id.singlebutton_dialog_title);
        mMessageTv = (TextView) findViewById(R.id.singlebutton_dialog_edittext);

        if (title == null) {
            mTitleTv.setVisibility(View.GONE);
        } else {
            mTitleTv.setText(title);
        }

        mMessageTv.setText(message);

        mConfirmBtn = (Button) findViewById(R.id.singlebutton_dialog_confirm);
        ;
        if (confirm == null) {
            mConfirmBtn.setVisibility(View.GONE);
        } else {
            mConfirmBtn.setText(confirm);
        }


        mDialogLisener = mLisener;


        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mDialogLisener != null) {

                    mDialogLisener.onConfirm(mMessageTv.getText().toString());

                }

                dismiss();
            }
        });


    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        EventBusHelper.post(new IPDialogOnBackE());
//        return false;
//    }
}
