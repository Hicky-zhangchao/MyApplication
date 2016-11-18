package com.feicuiedu.handlerdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

/**
 * Created by 张超 on 2016/11/2.
 */

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private Button btn2;

    private View.OnClickListener ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn1:
                    btn1.setBackgroundColor(Color.GREEN);
                    btn2.setText("邢贱贱");
                    break;
                case R.id.btn2:
                    String str = "邢无双";
                    new MyAsyncTask((Button) v).execute(str,str,str);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);

        btn1.setOnClickListener(ocl);
        btn2.setOnClickListener(ocl);

        TranslateAnimation animation = new TranslateAnimation(0,0,0,800);
        animation.setDuration(3000);
        animation.setRepeatCount(1000);
        btn1.startAnimation(animation);
    }
}
