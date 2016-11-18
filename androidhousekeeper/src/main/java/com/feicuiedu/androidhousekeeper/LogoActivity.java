package com.feicuiedu.androidhousekeeper;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by 张超 on 2016/11/3.
 */

public class LogoActivity extends Activity {

    private ImageView img_logo=null;

    private Animation animation=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);



        img_logo=(ImageView)findViewById(R.id.iv_logo);
        img_logo.setBackgroundResource(R.drawable.draw);
        AnimationDrawable ad=(AnimationDrawable)img_logo.getBackground();
        ad.start();


        new Handler(){
            public void handleMessage(android.os.Message msg) {
                if(msg.what==1){
                    Intent intent = new Intent(LogoActivity.this,StartActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }.sendEmptyMessageDelayed(1, 2600);



        /** 初始控件及动画 */
        img_logo=(ImageView)findViewById(R.id.iv_logo);

       animation= AnimationUtils.loadAnimation(this,R.anim.anim_logo);

       animation.setAnimationListener(animationListener);
        img_logo.startAnimation(animation);
    }

    private Animation.AnimationListener animationListener=new Animation.AnimationListener() {

        /** 动画开始 */
        @Override
        public void onAnimationStart(Animation animation) {


        }

        /** 动画结束 */
        @Override
        public void onAnimationEnd(Animation animation) {

        }

        /** 动画重复 */
        @Override
        public void onAnimationRepeat(Animation animation) {

            Intent intent=new Intent(LogoActivity.this,StartActivity.class);
            startActivity(intent);
              finish();
       }
    };



}
