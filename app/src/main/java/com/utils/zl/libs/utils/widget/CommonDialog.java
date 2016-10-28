package com.utils.zl.libs.utils.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.utils.zl.libs.R;


/**
 * Created by TANG on 2016/3/29.
 */
public class CommonDialog extends Dialog {


    protected final Button mConfirmBtn;
    protected final Button mCancelBtn;
    private EditText mEditTv;
    private OnDialogLCickLisener mDialogLisener;
    private MyTextWatcher mTextWatcher;


    public CommonDialog(Context context, String title, String message,
                        String editHint, String confirm, String cancel,
                        OnDialogLCickLisener mLisener) {

        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        View view = LayoutInflater.from(context).
                inflate(R.layout.common_notice_dialog, null, false);
        setContentView(view);

        setCanceledOnTouchOutside(false);

        mTextWatcher = new MyTextWatcher(context);
        TextView mTitleTv = (TextView) findViewById(R.id.dialog_title);
        if (title == null) {
            mTitleTv.setVisibility(View.GONE);
        } else {
            mTitleTv.setText(title);
        }


        TextView mMessageTv = (TextView) findViewById(R.id.dialog_message);
        if (message == null) {
            mMessageTv.setVisibility(View.GONE);
        } else {
            mMessageTv.setText(message);
        }

        mEditTv = (EditText) findViewById(R.id.dialog_edittext);
        if (editHint == null) {
            mEditTv.setVisibility(View.GONE);
        } else {
            mEditTv.setHint(editHint);
            mEditTv.addTextChangedListener(mTextWatcher);
        }


        mConfirmBtn = (Button) findViewById(R.id.dialog_confirm);
        ;
        if (confirm == null) {
            mConfirmBtn.setVisibility(View.GONE);
        } else {
            mConfirmBtn.setText(confirm);
        }

        mCancelBtn = (Button) findViewById(R.id.dialog_cancel);
        if (confirm == null) {
            mCancelBtn.setVisibility(View.GONE);
        } else {
            mCancelBtn.setText(cancel);
        }


        mDialogLisener = mLisener;


        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialogLisener != null) {
                    mDialogLisener.onConfirm(mEditTv.getText().toString());
                    dismiss();
                }
            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialogLisener != null) {
                    mDialogLisener.onCancle();
                }
                dismiss();
            }
        });

    }


}
