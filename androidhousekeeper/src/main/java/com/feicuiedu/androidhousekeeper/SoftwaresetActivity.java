package com.feicuiedu.androidhousekeeper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;




/**
 * Created by 张超 on 2016/11/2.
 */

public class SoftwaresetActivity extends Activity implements View.OnClickListener {

    //返回图片
    private ImageView iv_fanhui=null;
    //启动文本
    private TextView tv_qidong=null;
    //启动图片
    private CheckBox cb_01=null;

    //通知图标文本
    private TextView tv_tongzhi=null;
    //通知图片
    private CheckBox cb_02=null;

    //消息推送文本
    private TextView tv_info=null;
    //消息推送图片
    private CheckBox cb_03=null;

    private boolean flag=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.softwareset_activity);

        //引用监听对象
        init();
        SharedPreferences spf=getSharedPreferences("login",MODE_PRIVATE);
        String flag=spf.getString("flag","");
        if(flag.equals("0")){
            cb_01.setBackgroundResource(R.drawable.check_switch_on);
        }else {
            cb_01.setBackgroundResource(R.drawable.check_switch_off);
        }

        SharedPreferences spf2=getSharedPreferences("tongzhi",MODE_PRIVATE);
        String flag2=spf2.getString("flag1","");
        if(flag2.equals("0")){
            cb_02.setBackgroundResource(R.drawable.check_switch_on);
        }else {
            cb_02.setBackgroundResource(R.drawable.check_switch_off);
        }
        SharedPreferences spf3=getSharedPreferences("tuisong",MODE_PRIVATE);
        String flag3=spf3.getString("flag2","");
        if(flag3.equals("0")){
            cb_03.setBackgroundResource(R.drawable.check_switch_on);

        }else {
            cb_03.setBackgroundResource(R.drawable.check_switch_off);
        }


    }

    //初始化控件
    private void init(){


        iv_fanhui=(ImageView)findViewById(R.id.iv_fanhui);
        ///通过findViewById获得CheckBox对象
        tv_qidong=(TextView)findViewById(R.id.tv_qidong);
        tv_tongzhi=(TextView)findViewById(R.id.tv_tongzhi);
        tv_info=(TextView)findViewById(R.id.tv_info);
        cb_01=(CheckBox)findViewById(R.id.cb_01);
        cb_02=(CheckBox)findViewById(R.id.cb_02);
        cb_03=(CheckBox)findViewById(R.id.cb_03);
        //注册事件监听器
        cb_01.setOnClickListener(this);
        cb_02.setOnClickListener(this);
        cb_03.setOnClickListener(this);
        iv_fanhui.setOnClickListener(this);




    }



    //按钮响应事件
    @Override
    public void onClick(View v) {

        if(v == iv_fanhui){
            finish();
        }
        if(v.getId() == cb_01.getId()){

            if(cb_01.isChecked()){
                if(flag) {
                    SharedPreferences spf = getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = spf.edit();
                    editor.putString("flag", "0");
                    Toast.makeText(getApplicationContext(), tv_qidong.getText() + "   已开启", Toast.LENGTH_SHORT).show();
                    cb_01.setBackgroundResource(R.drawable.check_switch_on);
                    editor.commit();
                }

            }else {
                cb_01.setBackgroundResource(R.drawable.check_switch_off);
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage("关闭后不能开机启动哦");
                builder.setPositiveButton("继续开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        flag=false;
                        cb_01.setBackgroundResource(R.drawable.check_switch_on);
                    }
                });
                builder.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        flag=true;
                        SharedPreferences spf = getSharedPreferences("login", MODE_PRIVATE);
                        SharedPreferences.Editor editor = spf.edit();
                        editor.putString("flag", "1");
                        Toast.makeText(getApplicationContext(), tv_qidong.getText() + "   已取消", Toast.LENGTH_SHORT).show();
                        cb_01.setBackgroundResource(R.drawable.check_switch_off);
                        editor.commit();
                    }
                });
                builder.show();
            }

        }

        if(v.getId() == cb_02.getId()){

            if(cb_02.isChecked()){
                SharedPreferences spf=getSharedPreferences("tongzhi",MODE_PRIVATE);
                SharedPreferences.Editor editor=spf.edit();
                editor.putString("flag1", "0");
                Toast.makeText(getApplicationContext(),tv_tongzhi.getText()+"   已开启",Toast.LENGTH_SHORT).show();
                cb_02.setBackgroundResource(R.drawable.check_switch_on);
                editor.commit();
            }else{
                SharedPreferences spf=getSharedPreferences("tongzhi",MODE_PRIVATE);
                SharedPreferences.Editor editor=spf.edit();
                editor.putString("flag1", "1");
                Toast.makeText(getApplicationContext(),tv_tongzhi.getText()+"   已取消",Toast.LENGTH_SHORT).show();
                cb_02.setBackgroundResource(R.drawable.check_switch_off);
                editor.commit();
            }

        }

        if(v.getId() == cb_03.getId()){

            if(cb_03.isChecked()){
                SharedPreferences spf=getSharedPreferences("tuisong",MODE_PRIVATE);
                SharedPreferences.Editor editor=spf.edit();
                editor.putString("flag2", "0");
                cb_03.setBackgroundResource(R.drawable.check_switch_on);
                Toast.makeText(getApplicationContext(),tv_info.getText()+"   已开启",Toast.LENGTH_SHORT).show();
                editor.commit();
            }else {
                SharedPreferences spf=getSharedPreferences("tuisong",MODE_PRIVATE);
                SharedPreferences.Editor editor=spf.edit();
                editor.putString("flag2", "1");
                cb_03.setBackgroundResource(R.drawable.check_switch_off);
                editor.commit();
                Toast.makeText(getApplicationContext(),tv_info.getText()+"   已取消",Toast.LENGTH_SHORT).show();
            }

        }


    }


    //控件销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
        iv_fanhui=null;
        tv_qidong=null;
        cb_01=null;
        tv_tongzhi=null;
        cb_02=null;
        tv_info=null;
        cb_03=null;

    }
}
