package com.feicuiedu.tmimer;

import android.app.Activity;

import android.os.Bundle;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by 张超 on 2016/10/29.
 */

public class MainActivity extends Activity {

    private Timer timer;
    private TimerTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv = (TextView)findViewById(R.id.textView);

        final int WHAT = 102;
        final Handler handler = new Handler() {
            @Override
            public void publish(LogRecord record) {
                Message msg = new Message();
                switch (msg.what){
                    case  WHAT:
                        tv.setText(msg.obj+"");
                        break;
                }
            }

            @Override
            public void flush() {

            }

            @Override
            public void close() throws SecurityException {

            }
        };
        task = new TimerTask() {
            @Override
            public void run() {
              Message message = new Message();
                message.what = WHAT;
                message.obj = System.currentTimeMillis();
            //    handler.sendMessage(message);
            }
        };
        timer = new Timer();
        timer.schedule(task,1000,2000);
    }

    protected void onStop(){
        super.onStop();
        timer.cancel();
        task.cancel();
    }
}
