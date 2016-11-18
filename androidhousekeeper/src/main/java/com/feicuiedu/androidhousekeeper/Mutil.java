package com.feicuiedu.androidhousekeeper;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Map;

/**
 * Created by 张超 on 2016/11/3.
 */

public class Mutil {

    //获取内置sdcard的绝对路径
    public static String getPhoneSdcardPath(){

        String path=null;
        String state= Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            path=Environment.getExternalStorageDirectory().getAbsolutePath();
        }else {
            path=null;
        }

        return path;
    }

    //获取内置sdcard的全部空间大小
    public static double getPhoneSelfSdcardSize(){

        long size=0;
        File file=Environment.getDataDirectory();
        StatFs sf=new StatFs(file.getPath());
        long blocksize=sf.getBlockSize();
        long blockCount=sf.getBlockCount();
        size=blocksize*blockCount;

        Double a1=Double.valueOf(size);
        DecimalFormat df=new DecimalFormat("0.00");
        Double a3=(a1)/1024/1024/1024;
        Double ncsize=new Double(df.format(a3)).doubleValue();

        return ncsize ;
    }

    //获取内置sdcard的闲置空间大小
    public static double getPhoneSelfSdcardFreeSize(){

        long size=0;
        File file=Environment.getDataDirectory();
        StatFs sf=new StatFs(file.getPath());
        long blockSize=sf.getBlockSize();
        long availableCount=sf.getAvailableBlocks();
        size=blockSize*availableCount;

        Double a1=Double.valueOf(size);
        DecimalFormat df=new DecimalFormat("0.00");
        Double a3=(a1)/1024/1024/1024;
        Double ncsize=new Double(df.format(a3)).doubleValue();

        return ncsize;
    }

    //获取外置sdcard的路径
    public static String getPhoneOutSdcardPath(){

        String path=null;
        Map<String,String> map=System.getenv();
        if(map.containsKey("SECONDARY_STORAGE")){

            String paths=map.get("SECONDARY_STORAGE");
            String[] pathh=paths.split(":");
            if(pathh == null || pathh.length <= 0){
                path = null;
            }else {
                path=pathh[0];
            }

        }

        return path;
    }


    //外置sdcard全部存储空间大小
    public static double getPhoneOutSdcardSize(){

        long size=0;
        File file=new File(getPhoneOutSdcardPath());
        if(file == null){
            size=0;
        }else {
            StatFs sf=new StatFs(file.getPath());
            long blockSize=sf.getBlockSize();
            long blockCount=sf.getBlockCount();
            size=blockSize*blockCount;
        }

        Double a1=Double.valueOf(size);
        DecimalFormat df=new DecimalFormat("0.00");
        Double a3=(a1)/1024/1024/1024;
        Double ncsize=new Double(df.format(a3)).doubleValue();

        return ncsize;
    }


    //外置sdcard全部存储空间空闲大小
    public static double getPhoneOutSdcardFreeSize(){

        long size=0;
        File file=new File(getPhoneOutSdcardPath());
        if(file == null){
            size=0;
        }else{
            StatFs sf=new StatFs(file.getPath());
            long blackSize=sf.getBlockSize();
            long availableCount=sf.getAvailableBlocks();
            size=blackSize*availableCount;
        }

        Double a1=Double.valueOf(size);
        DecimalFormat df=new DecimalFormat("0.00");
        Double a3=(a1)/1024/1024/1024;
        Double ncsize=new Double(df.format(a3)).doubleValue();

        return ncsize;
    }


}
