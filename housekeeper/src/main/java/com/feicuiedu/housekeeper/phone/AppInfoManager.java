package com.feicuiedu.housekeeper.phone;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张超 on 2016/11/13.
 */

public class AppInfoManager {

    private static AppInfoManager appManager = null;
    private AppInfoManager(Context context) {
        this.context = context;
        packageManager = context.getPackageManager();
        activityManager = (ActivityManager)
                context.getSystemService(Context.ACTIVITY_SERVICE);
    }
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
    public Map<Integer, List<SpeedupActivity.RuningAppInfo>> getRuningAppInfos(){
        public static final int RUNING_APP_TYPE_SYS = 0;
        public static final int RUNING_APP_TYPE_USER = 1;
        public Map<Integer, List<SpeedupActivity.RuningAppInfo>> getRuningAppInfos() {
            Map<Integer, List<SpeedupActivity.RuningAppInfo>> runingAppInfos = new HashMap<Integer, List<SpeedupActivity.RuningAppInfo>>();
            List<SpeedupActivity.RuningAppInfo> sysapp = new ArrayList<SpeedupActivity.RuningAppInfo>();
            List<SpeedupActivity.RuningAppInfo> userapp = new ArrayList<SpeedupActivity.RuningAppInfo>();
            // 获取所有正在运行应用
            List<ActivityManager.RunningAppProcessInfo> appProcessInfos =
                    activityManager.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessInfos) {
                String packageName = appProcessInfo.processName; // 正在运行程序进程名
                int pid = appProcessInfo.pid; // 正在运行程序进程ID
                int importance = appProcessInfo.importance; // 正在运行程序进程级别
// 服务进程（包括）级别以下进程
                if (importance >= ActivityManager.RunningAppProcessInfo.IMPORTANCE_SERVICE) {
                    Drawable icon; // 所取数据：运行中程序图标
                    String lableName; // 所取数据：运行中程序名称
                    long size; // 所取数据：运行中程序所占内存
                    Debug.MemoryInfo[] memoryInfos =
                            activityManager.getProcessMemoryInfo(new int[]{pid});
                    size = (memoryInfos[0].getTotalPrivateDirty()) * 1024;
                    try {
                        icon = packageManager.getApplicationIcon(packageName);
                        ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA | PackageManager.GET_SHARED_LIBRARY_FILES | PackageManager.GET_UNINSTALLED_PACKAGES);
                        lableName = packageManager.getApplicationLabel(applicationInfo).toString();
                        SpeedupActivity.RuningAppInfo runingAppInfo = new SpeedupActivity.RuningAppInfo(packageName, icon, lableName, size);
// 系统进程
                        if ((applicationInfo.flags &
                                ApplicationInfo.FLAG_SYSTEM) != 0) {
                            runingAppInfo.setSystem(true);
                            runingAppInfo.setClear(false);
                            sysapp.add(runingAppInfo);
                        } else {
                            runingAppInfo.setSystem(false);
                            runingAppInfo.setClear(true);
                            userapp.add(runingAppInfo);
                        }
                    } catch (PackageManager.NameNotFoundException ex) {

                    }
                }
            }
            runingAppInfos.put(RUNING_APP_TYPE_SYS, sysapp);
            runingAppInfos.put(RUNING_APP_TYPE_USER, userapp);
            return runingAppInfos;

        }
    }
    public void killALLProcesses(){
        List<ActivityManager.RunningAppProcessInfo> appProcessInfos = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessInfos){
            if (appProcessInfo.importance >= ActivityManager.RunningAppProcessInfo.IMPORTANCE_SERVICE){
                String packageName = appProcessInfo.processName;
                try{
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA | PackageManager.GET_SHARED_LIBRARY_FILES | PackageManager.GET_SHARED_LIBRARY_FILES | PackageManager.GET_UNINSTALLED_PACKAGES);
                    if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0){

                    }
                    else {
                        activityManager.killBackgroundProcesses(packageName);
                    }
                }
                catch (PackageManager.NameNotFoundException e){
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    public void killProcesses(String packageName){
        activityManager.killBackgroundProcesses(packageName);
    }
}
