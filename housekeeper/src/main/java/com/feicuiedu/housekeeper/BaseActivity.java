package com.feicuiedu.housekeeper;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import static android.content.ContentValues.TAG;

/**
 * Created by 张超 on 2016/11/7.
 */

public abstract class BaseActivity extends Activity {

    protected Handler mainHandler = new Handler() {
        @Override
        public void publish(LogRecord record) {

        }

        @Override
        public void flush() {

        }
        @Override
        public void handleMessage(Notification.MessagingStyle.Message msg) {
            myHandleMessage(msg);
        }
        protected void myHandleMessage(Notification.MessagingStyle.Message msg){

        }
        @Override
        public void close() throws SecurityException {

        }
    };

    protected abstract void myHandleMessage(Notification.MessagingStyle.Message msg);

    private static ArrayList<BaseActivity> onlineActivityList = new ArrayList<BaseActivity>();
    public static void finishAll() {
        Iterator<BaseActivity> iterator = onlineActivityList.iterator();
        while (iterator.hasNext()) {
            iterator.next().finish();
        }}
    protected void startActivity(Class<?> targetClass) {
        Intent intent = new Intent(this, targetClass);
        startActivity(intent);
    }
    protected void startActivity(Class<?> targetClass, Bundle bundle) {
        Intent intent = new Intent(this, targetClass);
        intent.putExtras(bundle);   startActivity(intent);
    }
    protected void startActivity(Class<?> targetClass, int inAnimID,    int outAnimID) {
        Intent intent = new Intent(this, targetClass);
        startActivity(intent);
        overridePendingTransition(inAnimID, outAnimID);
    }
    protected void startActivity(Class<?> targetClass, int inAnimID,    int outAnimID, Bundle bundle) {
        Intent intent = new Intent(this, targetClass);
        intent.putExtras(bundle);   startActivity(intent);
        overridePendingTransition(inAnimID, outAnimID);
    }
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(TAG, getClass().getSimpleName() + " onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(TAG, getClass().getSimpleName() + " onResume()");
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        LogUtil.d(TAG, getClass().getSimpleName() + " onPause()");
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        LogUtil.d(TAG, getClass().getSimpleName() + " onStop()");
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        LogUtil.d(TAG, getClass().getSimpleName() + " onDestroy()");
        if (onlineActivityList.contains(this)) {
            onlineActivityList.remove(this);
        }
    }
}