package com.lerong.workerloantvapp.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

/**
 * Created by rongyile on 2018/1/9.
 */

public class BitmapUtil {

    /**
     * 根据原图和变长绘制圆形图片
     *
     * @param source
     * @param tagSize 传入的图片需要和此尺寸相同
     * @return
     */
    private Bitmap createCircleImage(Bitmap source, int tagSize)
    {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(tagSize, tagSize, Bitmap.Config.ARGB_8888);
        /**
         * 产生一个同样大小的画布
         */
        Canvas canvas = new Canvas(target);
        /**
         * 首先绘制圆形
         */
        canvas.drawCircle(tagSize / 2, tagSize / 2, tagSize / 2, paint);
        /**
         * 使用SRC_IN
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        /**
         * 绘制图片
         */
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

}
