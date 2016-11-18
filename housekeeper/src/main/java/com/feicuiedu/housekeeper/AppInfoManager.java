package com.feicuiedu.housekeeper;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张超 on 2016/11/9.
 */

public class AppInfoManager {

    private Context context;
    private PackageManager packageManager;
    private ActivityManager activityManager;
    /**
     * 用来保存所以应用程序包（Activity的）列表
     */
    private List<AppInfo> allPackageInfos = new ArrayList<AppInfo>();
    private List<AppInfo> userPackageInfos = new ArrayList<AppInfo>();
    private List<AppInfo> systemPackageInfos = new ArrayList<AppInfo>();
    /**
     * 用来返回类的唯一对象（单态模块  且做了同步处理，还优化了一下同步处理）
     */
    private static AppInfoManager appManager = null;

    public static AppInfoManager getAppInfoManager(Context context) {
        if (appManager == null) {
            synchronized (context) {
                if (appManager == null) {
                    appManager = new AppInfoManager(context);
                }
            }
        }
        return appManager;
    }

    /**
     * 用来返回所有应用程序列表
     */
    public List<AppInfo> getAllPackageInfo(boolean isReset) {
        if (isReset) {
            loadAllActivityPackager();
        }
        return allPackageInfos;
    }

    /**
     * 用来返回所有系统应用程序列表
     */
    public List<AppInfo> getSystemPackageInfo(boolean isReset) {
        if (isReset) {
            loadAllActivityPackager();
            systemPackageInfos.clear();
            for (AppInfo appInfo : allPackageInfos) {
                if ((appInfo.getPackageInfo().applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                    systemPackageInfos.add(appInfo);// 系统软件
                } else {

                }
            }
        }
        return systemPackageInfos;
    }

    /**
     * 用来返回所有用户应用程序列表
     */
    public List<AppInfo> getUserPackageInfo(boolean isReset) {
        if (isReset) {
            loadAllActivityPackager();
            userPackageInfos.clear();
            for (AppInfo appInfo : allPackageInfos) {
                if ((appInfo.getPackageInfo().applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {

                } else {
                    userPackageInfos.add(appInfo);// 用户软件

                }
            }
        }
        return userPackageInfos;
    }

    // 加载所有 Activity 应用程序包
    private void loadAllActivityPackager() {
        List<PackageInfo> infos = packageManager.getInstalledPackages(PackageManager.GET_ACTIVITIES | PackageManager.GET_UNINSTALLED_PACKAGES);
        allPackageInfos.clear();
        for (PackageInfo packageInfo : infos) {
            allPackageInfos.add(new AppInfo(packageInfo));
        }
    }

    AppAdapter appListAdapter = (AppAdapter) appListView.getAdapter();
    List<AppInfo> appInfos = appListAdapter.getDataList();
        for(AppInfo appInfo:appInfos){
          if (appInfo.isDel()){
            String packageName = appInfo.getPackageInfo().packageName;
              Intent intent = new Intent(Intent.ACTION_DELETE);
              intent.setData(Uri.parse("package:" + packageName));
              startActivity(intent);
          }
    }
}
