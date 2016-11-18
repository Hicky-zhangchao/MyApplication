package com.feicuiedu.androidhousekeeper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 张超 on 2016/11/2.
 */

public class FileManagementActivity extends Activity implements View.OnClickListener {

    //监听对象
    private RelativeLayout rl_all=null;
    private RelativeLayout rl_file=null;
    private RelativeLayout rl_mv=null;
    private RelativeLayout rl_radio=null;
    private RelativeLayout rl_img=null;
    private RelativeLayout rl_yasuo=null;
    private RelativeLayout rl_soft=null;




    //Textview控件
    private TextView tv_all=null;
    private TextView tv_allnum=null;
    private TextView tv_file=null;
    private TextView tv_filenum=null;
    private TextView tv_mv=null;
    private TextView tv_mvnum=null;
    private TextView tv_radio=null;
    private TextView tv_radionum=null;
    private TextView tv_img=null;
    private TextView tv_imgnum=null;
    private TextView tv_yasuo=null;
    private TextView tv_yasuonum=null;
    private TextView tv_soft=null;
    private TextView tv_softnum=null;
    private TextView tv_left=null;
    private TextView tv_sum=null;

    private Button btn_del=null;
    private ImageView iv_fanhui=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filemanagement_activity);

        init();

        tv_left.setText("已发现");
        tv_sum.setText("878.01 M");

        tv_all.setText("全部");
        tv_allnum.setText("878.01M");

        tv_file.setText("文档");
        tv_filenum.setText("5.11M");

        tv_mv.setText("视频");
        tv_mvnum.setText("101.10M");

        tv_radio.setText("音频");
        tv_radionum.setText("230.41M");

        tv_img.setText("图像");
        tv_imgnum.setText("170.93M");

        tv_yasuo.setText("压缩包");
        tv_yasuonum.setText("63.93M");

        tv_soft.setText("程序包");
        tv_softnum.setText("40.14M");

        btn_del.setText("清理全部");


    }

    //初始化控件
    protected void init(){

        rl_all=(RelativeLayout)findViewById(R.id.rl_all);
        tv_all=(TextView)findViewById(R.id.tv_all);
        tv_allnum=(TextView)findViewById(R.id.tv_allnum);

        rl_file=(RelativeLayout)findViewById(R.id.rl_file);
        tv_file=(TextView)findViewById(R.id.tv_file);
        tv_filenum=(TextView)findViewById(R.id.tv_filenum);

        rl_mv=(RelativeLayout)findViewById(R.id.rl_mv);
        tv_mv=(TextView)findViewById(R.id.tv_mv);
        tv_mvnum=(TextView)findViewById(R.id.tv_mvnum);

        rl_radio=(RelativeLayout)findViewById(R.id.rl_radio);
        tv_radio=(TextView)findViewById(R.id.tv_radio);
        tv_radionum=(TextView)findViewById(R.id.tv_radionum);

        rl_img=(RelativeLayout)findViewById(R.id.rl_img);
        tv_img=(TextView)findViewById(R.id.tv_img);
        tv_imgnum=(TextView)findViewById(R.id.tv_imgnum);

        rl_yasuo=(RelativeLayout)findViewById(R.id.rl_yasuo);
        tv_yasuo=(TextView)findViewById(R.id.tv_yasuo);
        tv_yasuonum=(TextView)findViewById(R.id.tv_yasuonum);

        rl_soft=(RelativeLayout)findViewById(R.id.rl_soft);
        tv_soft=(TextView)findViewById(R.id.tv_soft);
        tv_softnum=(TextView)findViewById(R.id.tv_softnum);

        btn_del=(Button)findViewById(R.id.btn_del);
        iv_fanhui=(ImageView)findViewById(R.id.iv_fanhui);

        tv_left=(TextView)findViewById(R.id.tv_left);
        tv_sum=(TextView)findViewById(R.id.tv_sum);



        //注册监听器
        rl_all.setOnClickListener(this);
        rl_file.setOnClickListener(this);
        rl_mv.setOnClickListener(this);
        rl_radio.setOnClickListener(this);
        rl_img.setOnClickListener(this);
        rl_yasuo.setOnClickListener(this);
        rl_soft.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        iv_fanhui.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        if(v == iv_fanhui){

            FileManagementActivity.this.finish();

        }

        if(v == rl_all){

            Intent intent=new Intent(FileManagementActivity.this,FileAllActivity.class);
            startActivity(intent);
        }

        if(v == rl_file){

            Toast.makeText(FileManagementActivity.this,"文档",Toast.LENGTH_SHORT).show();

        }

        if(v == rl_mv){

            Toast.makeText(FileManagementActivity.this,"视频",Toast.LENGTH_SHORT).show();

        }

        if(v == rl_radio){

            Toast.makeText(FileManagementActivity.this,"音频",Toast.LENGTH_SHORT).show();

        }

        if(v == rl_img){

            Toast.makeText(FileManagementActivity.this,"图像",Toast.LENGTH_SHORT).show();

        }

        if(v == rl_yasuo){

            Toast.makeText(FileManagementActivity.this,"压缩包",Toast.LENGTH_SHORT).show();

        }

        if(v == rl_soft){

            Toast.makeText(FileManagementActivity.this,"程序包",Toast.LENGTH_SHORT).show();

        }

        if(v == btn_del){

            Toast.makeText(FileManagementActivity.this,"清理全部",Toast.LENGTH_SHORT).show();

        }

    }
}
