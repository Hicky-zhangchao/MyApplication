package com.feicuiedu.dialogmodule;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by 张超 on 2016/11/1.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    @Override
   protected void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button)findViewById(R.id.main_btn1);
        btn1.setOnClickListener(this);
        btn2 = (Button)findViewById(R.id.main_btn2);
        btn2.setOnClickListener(this);
        btn3 = (Button)findViewById(R.id.main_btn3);
        btn3.setOnClickListener(this);
        btn4 = (Button)findViewById(R.id.main_btn4);
        btn4.setOnClickListener(this);
        btn5 = (Button)findViewById(R.id.main_btn5);
        btn5.setOnClickListener(this);


    }
    private void dialog1(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//先得到构造器
        builder.setTitle("提示");//设置标题
        builder.setMessage("是否确认退出？");//设置内容
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){//设置确定按钮

            @Override
            public void onClick(DialogInterface dialog,int which) {
                dialog.dismiss();//关闭dialog
                Toast.makeText(MainActivity.this,"确认"+which,Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "取消" + which, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {//设置忽略按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "忽略" + which, Toast.LENGTH_SHORT).show();
            }
        });
        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }
    private void dialog1_1(){
        //先new出一个监听器，设置好监听
        DialogInterface.OnClickListener dialogOnclicListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case Dialog.BUTTON_POSITIVE:
                        Toast.makeText(MainActivity.this, "确认" + which, Toast.LENGTH_SHORT).show();
                        break;
                    case Dialog.BUTTON_NEGATIVE:
                        Toast.makeText(MainActivity.this, "取消" + which, Toast.LENGTH_SHORT).show();
                        break;
                    case Dialog.BUTTON_NEUTRAL:
                        Toast.makeText(MainActivity.this, "忽略" + which, Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        };
        //dialog参数设置
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//先得到构造器
        builder.setTitle("提示");//设置标题
        builder.setMessage("是否确认退出");//设置内容
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setPositiveButton("确认",dialogOnclicListener);
        builder.setNegativeButton("取消",dialogOnclicListener);
        builder.setNeutralButton("忽略",dialogOnclicListener);
        builder.create().show();

    }
    private void dialog2(){
        final String items[] = {"张三","李四","王五"};
        //dialog参数设置
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//先得到构造器
        builder.setTitle("提示");//设置标题
        //builder.setMessage("是否确认退出?"); //设置内容
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        //设置列表显示，注意设置了列表显示就不要设置builder.setMessage()l ,否则列表起不了作用
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this,items[which],Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this,"确定",Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }
    private void dialog3(){
        final String items[] = {"男","女"};
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setSingleChoiceItems(items,1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              //dialog.dismiss();
                Toast.makeText(MainActivity.this,items[which],Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }
    private void dialog4(){
        final String items[] = {"篮球","足球","排球"};
        final boolean selected[] = {true,false,true};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setMultiChoiceItems(items,selected, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
               // dialog.dismiss();
                Toast.makeText(MainActivity.this, items[which] + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this,"确定",Toast.LENGTH_SHORT).show();
                //android会自动根据你选择的改变selected数组的值
                for (int i = 0; i<selected.length;i++){
                    Log.e("hongliang",""+selected[i]);
                }
            }
        });
        builder.create().show();
    }
    private void dialog5(){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("提示"); //设置标题
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可

        LayoutInflater lif = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = lif.inflate(R.layout.login_layout,null);
        builder.setView(view);
        builder.create().show();
    }
    @Override
        public void onClick(View v)  {
        switch (v.getId()){
            case R.id.main_btn1:
               // dialog1();
                dialog1_1();//等同与上面的dialog1(),比之更简洁，推荐
                break;
            case R.id.main_btn2:
                dialog2();
                break;
            case R.id.main_btn3:
                dialog3();
                break;
            case R.id.main_btn4:
                dialog4();
                break;
            case R.id.main_btn5:
                dialog5();
                break;
        }
    }
}
