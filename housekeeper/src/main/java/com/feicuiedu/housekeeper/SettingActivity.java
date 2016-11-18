package com.feicuiedu.housekeeper;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

/**
 * Created by 张超 on 2016/11/8.
 */

public class SettingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        // 初始化 ActionBar @see super class ActionBarActivity
        String title = getResources().getString(R.string.setting);
        initActionBar(title, R.drawable.btn_homeasup_default, -1, clickListener);
        // 初始化主按钮(开机启动，通知图标，消息推送,关于我们.....)
        initMainButton();
    }
    private void initMainButton(){
        tb_notif = (ToggleButton)findViewById(R.id.tb_toggle_notification);
        tb_notif.setChecked(NotificationUtil.isOpenNotification(getApplicationContext()));
        tb_notif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //选出弹出通知栏
                    NotificationUtil.cancelAppIconNotification(getApplicationContext());
                }
                else {
                    //取消通知栏
                    NotificationUtil.cancelAppIconNotification(getApplicationContext());
                }
            }
        });
    }
    public void hitSettingitem(View view){
        int viewID = view.getId();
        switch (viewID){
            case R.id.rl_setting_about:
                // 用来判断是从哪里进入的关于我们界面
                Bundle bundle = new Bundle();
                bundle.putString("className", SettingActivity.this.getClass().getSimpleName());
                startActivity(AboutActivity.class, bundle);break;
            case R.id.rl_setting_help:
                SharedPreferences preferences = getSharedPreferences("lead_config",      Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isFirstRun", true);
                editor.commit();
                // 用来判断是从哪里进入的关于我们界面
                Bundle bundle2 = new Bundle();
                bundle2.putString("className",SettingActivity.this.getClass().getSimpleName());
                startActivity(LeadActivity.class, bundle2);
                break;
            default:
                break;
        }
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int viewID = v.getId();
            switch (viewID){
                case R.id.iv_left:
                    startActivity(HomeActivity.class);
                    finish();
                    break;
            }
        }
    };
}
