package com.example.mall.util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/10/17.
 */

public class ToastUtils {
    private static Context context = null;
    private static Toast toast = null;

    public static void showToast(Activity activity, String text) {
//
        context = activity.getBaseContext();
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();

    }

}
