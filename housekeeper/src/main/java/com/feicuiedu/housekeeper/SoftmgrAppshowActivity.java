package com.feicuiedu.housekeeper;

import android.os.Bundle;
import android.view.View;

import static android.R.attr.id;

/**
 * Created by 张超 on 2016/11/9.
 */

public class SoftmgrAppshowActivity extends BaseActivity {

    private void asynloadApp(){
        progressBarArc.setVisibility(View.VISIBLE);
        appListView.setVisibility(View.INVISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                switch (id){
                    case R.id.rl_soft_all:
                        appInfos = AppInfoManager.getAppInfoManager(getApplicationContext()).getAllPackageInfo(true);
                        break;
                    case R.id.rl_soft_sys:
                        appInfos = AppInfoManager.getAppInfoManager(getApplicationContext()).getSystemPackageInfo(true);
                        break;
                    case R.id.rl_soft_use:
                        appInfos = AppInfoManager.getAppInfoManager(getApplicationContext()).getUserPackageInfo(true);
                        break;
                }
            }
        }).start();
    }
    private void asynLoadApp(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBarArc.setVisibility(View.INVISIBLE);
                appListView.setVisibility(View.VISIBLE);
                appAdapter.setDataToAdapter(appInfos);
                appAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonemgrshowapp);
        asynLoadApp();
    }
}
