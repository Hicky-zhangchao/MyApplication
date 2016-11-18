package com.feicuiedu.androidhousekeeper;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by 张超 on 2016/11/2.
 */

public class SoftwareManagementActivity extends Activity  implements View.OnClickListener {

    //返回
    private ImageView ll_return;
    //所有软件
    private RelativeLayout rl_softall;
    //系统软件
    private RelativeLayout rl_server;
    //应用软件监听
    private RelativeLayout rl_apply;

    private TextView tv_card1=null;
    private TextView tv_sdcard=null;
    private TextView tv_card2=null;
    private TextView tv_sdcardout=null;
    private ProgressBar pb_progressbar=null;
    private ProgressBar pb_progressbar2=null;

    private DrawView drawView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.softwaremanagement_activity);

        init();





        tv_card1.setText("手机内置空间");
        tv_sdcard.setText("可用：" + Mutil.getPhoneSelfSdcardFreeSize() + "G/" + Mutil.getPhoneSelfSdcardSize() + "G");
        int cun1=(int) Mutil.getPhoneSelfSdcardSize();
        int cun11=(int)Mutil.getPhoneSelfSdcardFreeSize();
        pb_progressbar.setMax(cun1);
        pb_progressbar.setProgress(cun11);


        tv_card2.setText("外置存储空间");
        if(Mutil.getPhoneOutSdcardSize() !=0) {
            tv_sdcardout.setText("可用：" + Mutil.getPhoneOutSdcardFreeSize() + "G/" + Mutil.getPhoneOutSdcardSize() + "G");
        }else {
            tv_sdcardout.setText("可用：" + 0 + "B/" +0+ "B");
        }

        int cun2=(int) Mutil.getPhoneOutSdcardSize();
        int cun22=(int)Mutil.getPhoneOutSdcardFreeSize();
        pb_progressbar2.setMax(cun2);
        pb_progressbar2.setProgress(cun22);

        int a=0,b=0;
        a=cun1*1024;
        b=cun2*1024;
        int k=a+b;
        int s=k/360;
        //利用存储卡信息画圆
        drawView.setParamsWithAnim((b/s+1),(a/s));


    }

    private void init(){

        ll_return=(ImageView)findViewById(R.id.iv_fanhui);
        rl_softall=(RelativeLayout)findViewById(R.id.rl_softall);
        rl_server=(RelativeLayout)findViewById(R.id.rl_server);
        rl_apply=(RelativeLayout)findViewById(R.id.rl_apply);
        tv_card1=(TextView)findViewById(R.id.tv_card1);
        tv_sdcard=(TextView)findViewById(R.id.tv_sdcard);
        tv_card2=(TextView)findViewById(R.id.tv_card2);
        tv_sdcardout=(TextView)findViewById(R.id.tv_sdcardout);
        pb_progressbar=(ProgressBar)findViewById(R.id.pb_progressbar);
        pb_progressbar2=(ProgressBar)findViewById(R.id.pb_progressbar2);

        drawView=(DrawView)findViewById(R.id.drawView);


        //注册监听器
        ll_return.setOnClickListener(this);
        rl_softall.setOnClickListener(this);
        rl_server.setOnClickListener(this);
        rl_apply.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        if(v == ll_return){
            this.finish();
        }
        if(v == rl_softall){
            Intent intent=new Intent(this,SoftAllActivity.class);
            startActivity(intent);
        }

        if(v == rl_server){
            Intent intent=new Intent(this,SoftServerActivity.class);
            startActivity(intent);
        }

        if(v == rl_apply){
            Intent intent=new Intent(this,SoftApplyActivity.class);
            startActivity(intent);
        }
    }
}
