package com.feicuiedu.androidhousekeeper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by 张超 on 2016/11/3.
 */

public class SoftInfo extends Activity {


    public static String getVersionName(Context context){
        String versionName =null;
        //获取项目包的信息
        PackageManager pm=context.getPackageManager();

        try {
            PackageInfo pi=pm.getPackageInfo(context.getPackageName(),0);
            versionName=pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static String getAppName(Context context){

        String appName=null;
        //获取项目包的信息
        PackageManager pm=context.getPackageManager();

        try {
            PackageInfo pi=pm.getPackageInfo(context.getPackageName(),0);
            appName=pi.applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appName;
    }

    public static int getVersionCode(Context context){
        int versionCode=0;
        //获取项目包的信息
        PackageManager pm=context.getPackageManager();

        try {
            PackageInfo pi=pm.getPackageInfo(context.getPackageName(),0);
            versionCode=pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

}
