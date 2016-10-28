package com.utils.zl.libs.utils.widget;

import android.os.CountDownTimer;

public class MyTimer extends CountDownTimer {


    private boolean isRunning;

    public interface OnCountdownLisener {
        void onDown();

        void onTick();
    }

    private OnCountdownLisener lisener;

    public void setOnCountdownLisener(OnCountdownLisener countdownLisener) {
        this.lisener = countdownLisener;
    }

    public MyTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        // TODO Auto-generated constructor stub

    }

    @Override
    public void onTick(long millisUntilFinished) {
        // TODO Auto-generated method stub
        if (this.lisener != null) {
            lisener.onTick();
        }
    }

    @Override
    public void onFinish() {
        // TODO Auto-generated method stub
        if (this.lisener != null) {
            lisener.onDown();
        }
    }

}
