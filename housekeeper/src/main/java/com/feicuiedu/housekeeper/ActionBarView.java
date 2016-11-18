package com.feicuiedu.housekeeper;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 张超 on 2016/11/8.
 */

public class ActionBarView extends LinearLayout {

    private ImageView iv_actionbar_left;// 左边按钮
    private ImageView iv_actionbar_right;// 右边按钮
    private TextView tv_actionbar_title;// 中间文本

    public ActionBarView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        inflate(context, R.layout.layout_actionbar, this);
        tv_actionbar_title = (TextView) findViewById(R.id.tv_title);
        iv_actionbar_left = (ImageView) findViewById(R.id.iv_left);
        iv_actionbar_right = (ImageView) findViewById(R.id.iv_right);
    }
    public void initActionBar(String title, int leftResID, int rightResID, OnClickListener listener){
        tv_actionbar_title.setText(title);
        if (leftResID == -1){
            iv_actionbar_left.setVisibility(View.INVISIBLE);
        }
        else {
            iv_actionbar_left.setImageResource(leftResID);
            iv_actionbar_left.setOnClickListener(listener);
        }
        if (rightResID == -1){
            iv_actionbar_right.setVisibility(View.INVISIBLE);
        }
        else {
            iv_actionbar_right.setImageResource(rightResID);
            iv_actionbar_right.setOnClickListener(listener);
        }
    }
}
