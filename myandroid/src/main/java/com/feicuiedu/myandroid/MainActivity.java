package com.feicuiedu.myandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by 张超 on 2016/10/27.
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Context context;

    // 图片
    private ImageView iv_weather;

    // 透明渐变按钮
    private Button btn_alpha;

    // 旋转按钮
    private Button btn_rotate;

    // 缩放按钮
    private Button btn_scale;

    // 位移按钮
    private Button btn_translate;

    // 动画组合按钮
    private Button btn_set;

    private Button btn_jump;

    private ImageView iv_yy1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        btn_alpha = (Button) findViewById(R.id.btn_alpha);
        btn_rotate = (Button) findViewById(R.id.btn_rotate);
        btn_scale = (Button) findViewById(R.id.btn_scale);
        btn_translate = (Button) findViewById(R.id.btn_translate);
        btn_set = (Button) findViewById(R.id.btn_set);
        btn_jump = (Button) findViewById(R.id.btn_jump);

        iv_yy1 = (ImageView) findViewById(R.id.iv_yy1);


        iv_weather = (ImageView) findViewById(R.id.iv_weather);


        btn_alpha.setOnClickListener(ocl);
        btn_rotate.setOnClickListener(ocl);
        btn_scale.setOnClickListener(ocl);
        btn_translate.setOnClickListener(ocl);
        btn_set.setOnClickListener(ocl);
        btn_jump.setOnClickListener(ocl);
        // 让帧动画运行起来
       // AnimationDrawable animationDrawable = (AnimationDrawable) iv_weather.getBackground();
      //  animationDrawable.start();

    }

    private View.OnClickListener ocl = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            Animation anim = null;
            switch (view.getId()){
                case R.id.btn_alpha:

                    // 透明动画执行过程
                    anim = AnimationUtils.loadAnimation(context,R.anim.alpha);
                    break;
                case R.id.btn_rotate:

                    // 旋转动画
                    anim = AnimationUtils.loadAnimation(context,R.anim.rotate);
                    break;
                case R.id.btn_scale:

                    // 缩放动画
                    anim = AnimationUtils.loadAnimation(context,R.anim.scale);
                    break;
                case R.id.btn_translate:

                    // 位移动画
                    anim = AnimationUtils.loadAnimation(context,R.anim.translate);
                    break;
                case R.id.btn_set:

                    anim = AnimationUtils.loadAnimation(context,R.anim.anim_set);

                    break;
                default:
                    Intent intent = new Intent();
                    intent.setClass(context,SecondActivity.class);
                    startActivity(intent);

                    overridePendingTransition(R.anim.scale,R.anim.translate);
                    return;
            }

            anim.setAnimationListener(al);
            iv_yy1.startAnimation(anim);
        }
    };

    Animation.AnimationListener al = new Animation.AnimationListener() {

        // 动画开始时被调用的方法
        @Override
        public void onAnimationStart(Animation animation) {

            Log.d(TAG, "onAnimationStart()called with: animation = [" + animation + "]");


        }

        // 动画结束
        @Override
        public void onAnimationEnd(Animation animation) {

            Log.d(TAG, "onAnimationEnd() called with: animation = [" + animation + "]");
        }

        // 动画重复
        @Override
        public void onAnimationRepeat(Animation animation) {
            Log.d(TAG, "onAnimationRepeat() called with: animation = [" + animation + "]");
        }
    };
}
