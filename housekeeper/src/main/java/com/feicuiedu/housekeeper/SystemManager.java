package com.feicuiedu.housekeeper;

import android.os.Build;

/**
 * Created by 张超 on 2016/11/13.
 */

public class SystemManager {

    public static String getPhoneName(){
        return Build.BRAND;
    }
    public static String getPhoneModelName(){
        return Build.MODEL + " Android" + getPhoneSystemVersion();
    }
}
