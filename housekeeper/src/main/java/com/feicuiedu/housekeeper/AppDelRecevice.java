package com.feicuiedu.housekeeper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by 张超 on 2016/11/9.
 */

public class AppDelRecevice extends BroadcastReceiver {

    public static final String ACTION_APPDEL = "com.androidy.app.phone.del";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_PACKAGE_REMOVED)||action.equals(ACTION_APPDEL)){
            asynLoadApp();
        }
    }
}
