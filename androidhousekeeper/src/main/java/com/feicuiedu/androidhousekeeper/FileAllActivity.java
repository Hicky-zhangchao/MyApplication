package com.feicuiedu.androidhousekeeper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by 张超 on 2016/11/2.
 */

public class FileAllActivity extends Activity implements View.OnClickListener {

    private ImageView iv_fanhui=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fileall_activity);

        init();



    }

    //初始化控件
    private void init(){

        iv_fanhui=(ImageView)findViewById(R.id.iv_fanhui);


        //注册监听器
        iv_fanhui.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        if(v == iv_fanhui){

            FileAllActivity.this.finish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iv_fanhui=null;

    }
}
