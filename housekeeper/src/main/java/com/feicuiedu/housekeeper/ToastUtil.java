package com.feicuiedu.housekeeper;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 张超 on 2016/11/4.
 */

public class ToastUtil {

    private static Toast toast;
    public static void show(Context context, String text, int duration){
        if (toast == null){
            toast = Toast.makeText(context, text, duration);
        }
        toast.setText(text);
        toast.setDuration(duration);
        toast.show();
    }
}
