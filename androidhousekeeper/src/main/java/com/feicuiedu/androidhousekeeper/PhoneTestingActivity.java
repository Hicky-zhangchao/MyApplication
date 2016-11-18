package com.feicuiedu.androidhousekeeper;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by 张超 on 2016/11/2.
 */

public class PhoneTestingActivity extends Activity implements View.OnClickListener {




    //定义返回主页面监听对象
    private ImageView ll_return;
    //设备名称
    private TextView tv_sbname;
    //系统版本
    private TextView tv_bbname;
    //运行内存
    private TextView tv_ncall;
    //内存剩余
    private TextView tv_ncsy;
    //cpu名称
    private TextView tv_cpuname;
    //cpu数量
    private TextView tv_cpnumber;
    //手机分辨率
    private TextView tv_phonefbl;
    //相机分辨率
    private TextView tv_xjfbl;
    //基带版本
    private TextView tv_jdbb;
    //是否root
    private TextView tv_root;
    //主板
    private TextView tv_mainboard;
    //驱动信息
    private TextView tv_display;
    //存储卡总容量
    private TextView tv_neicunsum;
    //存储卡剩余容量
    private TextView tv_shengyucun;

    private ProgressBar progress_pb=null;
    private TextView tv_text=null;
    private BatteryBroadReceiver broad=null;
    private TextView tv_info=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phonetesting_activity);


        init();

        //电池信息
        broad=new BatteryBroadReceiver();
        IntentFilter filter=new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(broad,filter);

        //获取运行内存容量
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        String availMem = Formatter.formatFileSize(getBaseContext(), mi.availMem);// 可用内存
        String totalMem = Formatter.formatFileSize(getBaseContext(), mi.totalMem);//全部内存


        //获取手机分辨率
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        int w = mDisplayMetrics.widthPixels;
        int h = mDisplayMetrics.heightPixels;


        PhTesting pht=new PhTesting();
        tv_sbname.setText("设备名称：" + pht.getDeviceMode(this));
        tv_bbname.setText("系统版本：Android "+pht.getVersionRelease(this));
        tv_ncall.setText("全部运行内存："+totalMem);
        tv_ncsy.setText("剩余运行内存："+availMem);
        tv_cpuname.setText("CPU名称：" + pht.getCpuAbi(this));
        tv_cpnumber.setText("CPU数量：" + pht.getCpuNum(this));
        tv_phonefbl.setText("手机分辨率：" + w + "*" + h);
        tv_xjfbl.setText("相机分辨率：" + pht.getCfbl(this));
        tv_jdbb.setText("基带版本："+pht.getBaseband_Ver(this));

        //检测是否root
        String root_str=null;
        if(pht.checkRootMethod1() == true){
            root_str="是";
        }else{
            root_str="否";
        }
        tv_root.setText("是否ROOT："+root_str);

        tv_mainboard.setText("主板信息："+pht.getDeviceBoard(this));
        tv_display.setText("驱动信息："+pht.getDisPlay(this));
        tv_neicunsum.setText("内置存储卡容量："+ Mutil.getPhoneSelfSdcardSize()+"GB");
        tv_shengyucun.setText("存储卡剩余容量："+ Mutil.getPhoneSelfSdcardFreeSize() +"GB");




    }




    //初始化监听器
    private void init(){

        //获取监听对象
        progress_pb=(ProgressBar)findViewById(R.id.progress_pb);
        tv_info=(TextView)findViewById(R.id.tv_info);
        tv_text=(TextView)findViewById(R.id.tv_text);
        ll_return=(ImageView)findViewById(R.id.ll_return);
        tv_sbname=(TextView)findViewById(R.id.tv_sbname);
        tv_bbname=(TextView)findViewById(R.id.tv_bbname);
        tv_ncall=(TextView)findViewById(R.id.tv_ncall);
        tv_ncsy=(TextView)findViewById(R.id.tv_ncsy);
        tv_cpuname=(TextView)findViewById(R.id.tv_cpuname);
        tv_cpnumber=(TextView)findViewById(R.id.tv_cpunumber);
        tv_phonefbl=(TextView)findViewById(R.id.tv_phonefbl);
        tv_xjfbl=(TextView)findViewById(R.id.tv_xjfbl);
        tv_jdbb=(TextView)findViewById(R.id.tv_jdbb);
        tv_root=(TextView)findViewById(R.id.tv_root);
        tv_mainboard=(TextView)findViewById(R.id.tv_mainboard);
        tv_display=(TextView)findViewById(R.id.tv_display);
        tv_neicunsum=(TextView)findViewById(R.id.tv_neicunsum);
        tv_shengyucun=(TextView)findViewById(R.id.tv_shengyucun);


        //注册监听器
        ll_return.setOnClickListener(this);

    }

    //监听处理
    @Override
    public void onClick(View v) {

        if(v == ll_return){
            this.finish();
        }


    }

    //获取电池电量信息
    private class BatteryBroadReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){

                Bundle bundle=intent.getExtras();
                //全部电量
                // int maxBattery=(int)bundle.get(BatteryManager.EXTRA_SCALE);
                int maxBattery=(int)bundle.get("scale");
                //当前电量
//                int nowBattery=(int)bundle.get(BatteryManager.EXTRA_LEVEL);
                int nowBattery=(int)bundle.get("level");

                int num=nowBattery*100/maxBattery;

                if(num < 10){
                    tv_info.setText("电量低于10%请及时充电······");
                }

                progress_pb.setMax(maxBattery);
                progress_pb.setProgress(nowBattery);
                tv_text.setText(num+"%");

            }

        }
    }


}