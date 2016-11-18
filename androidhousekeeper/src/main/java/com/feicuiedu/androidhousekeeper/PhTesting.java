package com.feicuiedu.androidhousekeeper;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.hardware.Camera;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import java.io.File;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.List;



/**
 * Created by 张超 on 2016/11/3.
 */

public class PhTesting{

    //设备型号
    public String getDeviceMode(Context context){
        String device_model= Build.MODEL;
        return device_model;
    }

    //设备SDK版本
    public String getVersionSDK(Context context){
        String version_sdk=Build.VERSION.SDK;
        return version_sdk;
    }

    //设备的系统版本
    public String getVersionRelease(Context context){
        String version_release=Build.VERSION.RELEASE;
        return version_release;
    }


    //获取基带版本

    public String getBaseband_Ver(Context context){
        String Version = "";
        try {
            Class cl = Class.forName("android.os.SystemProperties");
            Object invoker = cl.newInstance();
            Method m = cl.getMethod("get", new Class[] { String.class,String.class });
            Object result = m.invoke(invoker, new Object[]{"gsm.version.baseband", "no message"});
            Version = (String)result;
        } catch (Exception e) {
        }
        return Version;
    }


    //基带版本
    public String getIncrementalRelease(Context context){
        String incremental_release=Build.VERSION.INCREMENTAL;
        return incremental_release;
    }

    //cup名称
    public String getCpuAbi(Context context){
        String cpu_abi=Build.CPU_ABI;
        return cpu_abi;
    }

    //cpu数
    public int getCpuNum(Context context){
        int cpu_num=Runtime.getRuntime().availableProcessors();
        return cpu_num;
    }

    //相机分辨率
    public String getCfbl(Context context){

        String str=null;
        Camera camera = Camera.open();
        Camera.Parameters parameters = camera.getParameters();
        List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
        for(int i = 0 ; i < supportedPictureSizes.size() ; i ++){
            Camera.Size s = supportedPictureSizes.get(i);
            str=(i+1)*s.width+"*"+(i+1)*s.height;
        }
        camera.stopPreview();
        camera.release();
        camera = null;
        return str;
    }



    //是否root
    public boolean checkRootMethod1(){
        String buildTags = android.os.Build.TAGS;

        if (buildTags != null && buildTags.contains("test-keys")) {
            return true;
        }
        return false;
    }

    //主板
    public String getDeviceBoard(Context context){
        String device_board= Build.BOARD;
        return device_board;
    }

    //版本号
    public String getDisPlay(Context context){
        String display= Build.DEVICE;
        return display;
    }

}
