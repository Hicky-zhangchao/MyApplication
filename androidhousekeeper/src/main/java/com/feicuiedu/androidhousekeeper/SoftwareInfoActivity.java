package com.feicuiedu.androidhousekeeper;

/**
 * Created by 张超 on 2016/11/2.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



/**
 * Created by 张超 on 2016/11/2.
 */
public class SoftwareInfoActivity extends Activity implements View.OnClickListener {

    //返回监听
    private ImageView iv_fanhui=null;
    //logo图片
    private ImageView imageView=null;
    //app名
    private TextView tv_appname=null;
    //版本名
    private TextView tv_versions=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.softwareinfo_activity);

        init();
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        //版本名
        String versionName=SoftInfo.getVersionName(this);
        //应用名称
        String appName=SoftInfo.getAppName(this);
        tv_appname.setText(appName);
        tv_versions.setText(versionName);

    }

    private void init(){

        iv_fanhui=(ImageView)findViewById(R.id.iv_fanhui);
        imageView=(ImageView)findViewById(R.id.imageView);
        tv_appname=(TextView)findViewById(R.id.tv_appname);
        tv_versions=(TextView)findViewById(R.id.tv_versions);

        iv_fanhui.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        if(v == iv_fanhui){

            this.finish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iv_fanhui=null;
        imageView=null;
        tv_versions=null;
        tv_appname=null;

    }
}
