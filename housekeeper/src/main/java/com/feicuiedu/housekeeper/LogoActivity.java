package com.feicuiedu.housekeeper;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


/**
 * Created by 张超 on 2016/11/4.
 */

public class LogoActivity extends Activity {

    private ImageView img_logo = null;
    private Animation animation = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        //初始控件级动画
        img_logo = (ImageView)findViewById(R.id.iv_logo);
        animation = AnimationUtils.loadAnimation(this,R.anim.anim_logo);
    }

    private Animation.AnimationListener animationListener = new Animation.AnimationListener(){
        //动画开始
        @Override
        public void onAnimationStart(Animation animation) {

        }
        //动画重复
        @Override
        public void onAnimationRepeat(Animation animation){

        }
        //动画结束
        @Override
        public void onAnimationEnd(Animation animation){
            Intent intent = new Intent(LogoActivity.this,TelmsgActivity.class);
            startActivity(intent);
            finish();

        }

    };
}
