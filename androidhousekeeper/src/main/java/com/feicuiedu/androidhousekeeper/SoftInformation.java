package com.feicuiedu.androidhousekeeper;

import android.content.pm.PackageInfo;

/**
 * Created by 张超 on 2016/11/3.
 */

public class SoftInformation {

    private boolean isDel;

    private PackageInfo packageInfo;

    public SoftInformation(){}

    public SoftInformation(boolean isDel){
        this.isDel=isDel;
    }

    public SoftInformation(PackageInfo packageInfo,boolean isDel){
        this.packageInfo=packageInfo;
        this.isDel=isDel;
    }

    public boolean isDel(){
        return isDel;
    }

    public void setIsDel(boolean isDel){
        this.isDel=isDel;
    }
    public PackageInfo getPackageInfo(){
        return packageInfo;
    }

    public void setPackageInfo(PackageInfo packageInfo){
        this.packageInfo=packageInfo;
    }

}
