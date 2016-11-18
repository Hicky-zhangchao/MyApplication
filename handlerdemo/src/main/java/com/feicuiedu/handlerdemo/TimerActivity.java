package com.feicuiedu.handlerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 张超 on 2016/11/3.
 */

public class TimerActivity extends AppCompatActivity {

    private Button btnStart;
    private TextView tvShow;
    private int time;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg){
            tvShow.setText(msg.arg1+"");
        }
    };
    private boolean isRun = true;
    private View.OnClickListener ocl = new View.OnClickListener(){

        @Override
        public void onClick(View v){
            if(isRun){
                isRun = false;
                time = 0;
            }
            else {
                isRun = true;
            }
            Timer timer = new Timer();
            timer.schedule(new TimerTask(){

                @Override
                public void run(){
                    Message message = new Message();
                    time ++;
                    message.arg1 = time;
                    handler.sendMessage(message);
                }

            },0,10);
        }
    };
        @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_layout);

        tvShow = (TextView)findViewById(R.id.tv_show);
        btnStart=(Button)findViewById(R.id.btn_start);

        btnStart.setOnClickListener(ocl);
    }

}
