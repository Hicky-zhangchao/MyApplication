package com.feicuiedu.androidhousekeeper;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by 张超 on 2016/11/2.
 */

public class StartActivity extends Activity implements View.OnClickListener {

    /** 软件版本信息 */
    private ImageView softwareinfo=null;
    /** 软件设置 */
    private ImageView ll_set=null;
    /** 手机加速 */
    private LinearLayout ll_11=null;
    /** 软件管理 */
    private LinearLayout ll_12=null;
    /** 手机检测 */
    private LinearLayout ll_13=null;
    /** 通讯大全 */
    private LinearLayout ll_21=null;
    /** 文件管理 */
    private LinearLayout ll_22=null;
    /** 垃圾检测 */
    private LinearLayout ll_23=null;

//    private DrawView drawView=null;

    private ImageView iv_youhua=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        TextView tv_mainnum = (TextView)findViewById(R.id.tv_mainnum);
        DrawView drawView = (DrawView)findViewById(R.id.drawView);
        drawView.setParamsWithAnim(200,200);
        tv_mainnum.setText("100");


        // 获取对象ID
        softwareinfo=(ImageView)findViewById(R.id.softwareinfo);
        ll_set=(ImageView)findViewById(R.id.ll_set);
        ll_11=(LinearLayout)findViewById(R.id.ll_b11);
        ll_12=(LinearLayout)findViewById(R.id.ll_b12);
        ll_13=(LinearLayout)findViewById(R.id.ll_b13);
        ll_21=(LinearLayout)findViewById(R.id.ll_b21);
        ll_22=(LinearLayout)findViewById(R.id.ll_b22);
        ll_23=(LinearLayout)findViewById(R.id.ll_b23);
        iv_youhua=(ImageView)findViewById(R.id.iv_youhua);

//        drawView=(DrawView)findViewById(R.id.drawView);
//        drawView.setParamsWithAnim(120, 240);


        //注册监听器
        softwareinfo.setOnClickListener(this);
        ll_set.setOnClickListener(this);
        ll_11.setOnClickListener(this);
        ll_12.setOnClickListener(this);
        ll_13.setOnClickListener(this);
        ll_21.setOnClickListener(this);
        ll_22.setOnClickListener(this);
        ll_23.setOnClickListener(this);
        iv_youhua.setOnClickListener(this);


//        init();

    }

    //初始化控件
    protected  void init(){

        // 获取对象ID
        softwareinfo=(ImageView)findViewById(R.id.softwareinfo);
        ll_set=(ImageView)findViewById(R.id.ll_set);
        ll_11=(LinearLayout)findViewById(R.id.ll_b11);
        ll_12=(LinearLayout)findViewById(R.id.ll_b12);
        ll_13=(LinearLayout)findViewById(R.id.ll_b13);
        ll_21=(LinearLayout)findViewById(R.id.ll_b21);
        ll_22=(LinearLayout)findViewById(R.id.ll_b22);
        ll_23=(LinearLayout)findViewById(R.id.ll_b23);
        iv_youhua=(ImageView)findViewById(R.id.iv_youhua);

//        drawView=(DrawView)findViewById(R.id.drawView);
//        drawView.setParamsWithAnim(120, 240);


        //注册监听器
        softwareinfo.setOnClickListener(this);
        ll_set.setOnClickListener(this);
        ll_11.setOnClickListener(this);
        ll_12.setOnClickListener(this);
        ll_13.setOnClickListener(this);
        ll_21.setOnClickListener(this);
        ll_22.setOnClickListener(this);
        ll_23.setOnClickListener(this);
        iv_youhua.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

//        if(v == iv_youhua){
//            drawView.setParamsWithAnim(160, 200);
//        }

        if(v == softwareinfo){
            Toast.makeText(this,"手机管家信息",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,SoftwareInfoActivity.class);
            startActivity(intent);
        }
        if(v == ll_set){
            Intent intent=new Intent(StartActivity.this,SoftwaresetActivity.class);
            startActivity(intent);

        }
        if(v == ll_11){

            Toast.makeText(this,"手机加速",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,SpeedActivity.class);
            startActivity(intent);

        }
        if(v == ll_12){
            Toast.makeText(this,"软件管理",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,SoftwareManagementActivity.class);
            startActivity(intent);
        }
        if(v == ll_13){
            Toast.makeText(this,"手机检测",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,PhoneTestingActivity.class);
            startActivity(intent);
        }
        if(v == ll_21){

            Intent intent=new Intent(this,CommuniCationActivity.class);
            startActivity(intent);
        }
        if(v == ll_22){
            Toast.makeText(this,"文件管理",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,FileManagementActivity.class);
            startActivity(intent);
        }
        if(v == ll_23){
            Toast.makeText(this,"垃圾清理",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,CCleanerActivity.class);
            startActivity(intent);
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

