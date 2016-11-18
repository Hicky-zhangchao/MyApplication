package com.feicuiedu.housekeeper;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicuiedu.housekeeper.LogUtil;
import com.feicuiedu.housekeeper.R;

/**
 * Created by 张超 on 2016/11/7.
 */

public abstract class BaseActionbarActivity extends BaseAdapter{

    public static final int NULL_ID = -1;
    public void setActionBar(int resIdTitle, int resIdLeft, int resIdRight) {
        try{
            TextView tv_action_title = (TextView)findViewById(R.id.tv_actionbar_title);
            ImageView iv_action_left = (ImageView) findViewById(R.id.iv_action_left);
            ImageView iv_action_right = (ImageView) findViewById(R.id.iv_action_right);
            if (resIdLeft != NULL_ID){
                iv_action_left.setImageResource(resIdLeft);
            }
            else{
                iv_action_left.setVisibility(View.INVISIBLE);
            }
            if (resIdRight != NULL_ID){
                iv_action_right.setImageResource(resIdRight);
            }
            else {
                iv_action_right.setVisibility(View.INVISIBLE);
            }
            if (resIdTitle != NULL_ID){
                tv_action_title.setText(resIdTitle);
            }
        }
        catch (Exception e){
            LogUtil.w("BaseActionbarActivity", "ActionBar 有异常 , 是否当前页 面并没有 includle==> include_actionbar.xml 布局 ?");

        }
    }
}
