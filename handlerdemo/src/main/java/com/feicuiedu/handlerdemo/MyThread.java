package com.feicuiedu.handlerdemo;

import android.graphics.Color;
import android.widget.Button;

/**
 * Created by 张超 on 2016/11/3.
 */

public class MyThread extends Thread{

    private Button btn2;

    public Button getBtn2(){
        return btn2;
    }
    public void setBtn2(Button btn2){
        this.btn2 = btn2;
    }

    @Override
    public void run() {
        //把Runable对象放到列队里去
        btn2.postDelayed(new MyRunable(),2000);
    }
    private class MyRunable implements Runnable{

        @Override
        public void run() {
            btn2.setBackgroundColor(Color.GREEN);
            btn2.setText("邢污污");
        }
    }
}
