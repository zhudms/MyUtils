package com.fangyuan.fanvideo.common.net;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import androidx.annotation.NonNull;

public class MD5U {

    @NonNull
    public static String md5(String string) {

        if (TextUtils.isEmpty(string)) {
            return "";
        }

        byte[] out = (byte[]) null;
        try {
            byte[] btInput = string.getBytes("utf-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(btInput);
            out = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return toHexString(out);
    }


    private static String toHexString(byte[] out) {

        StringBuilder buf = new StringBuilder();
        for (byte b : out)
            buf.append(String.format("%02X", new Object[]{Byte.valueOf(b)}));

        return buf.toString();
    }
}
