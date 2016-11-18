package com.feicuiedu.housekeeper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 张超 on 2016/11/7.
 */

public class LeadActivity extends BaseAdapter implements DialogInterface.OnClickListener {
    private boolean isFromSetting;
    protected Context context;
    private ArrayList<View> viewList = new ArrayList<View>();
    private ArrayList<String> tabtitleList = new ArrayList<String>();

    public BasePagerAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<View> getViewList() {
        return viewList;
    }

    public void addViewToAdapter(View view) {
        viewList.add(view);
    }

    public void addTabToAdapter(String title) {
        tabtitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // TODO Auto-generated method stub
        return tabtitleList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = viewList.get(position);
        container.removeView(view);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = viewList.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    private void initLeadIcon() {

        icons[0] = (ImageView) findViewById(R.id.icon1);
        icons[1] = (ImageView) findViewById(R.id.icon2);
        icons[2] = (ImageView) findViewById(R.id.icon3);
        icons[0].setImageResource(R.drawable.adware_style_selected);
        tv_skip = (TextView) findViewById(R.id.tv_skip);
        tv_skip.setVisibility(View.INVISIBLE);
        tv_skip.setOnClickListener(this);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        leadPagerAdapter = new BasePagerAdapter(this);
        viewPager.setAdapter(leadPagerAdapter);
        viewPager.setOnPageChangeListener(pageChangeListener);
    }

    private void initPagerData() {
        ImageView imageView = null;
        imageView = (ImageView) getLayoutInflater().inflate(R.layout.layout_lead_item, null);
        imageView.setImageResource(R.drawable.adware_style_applist);
        leadPagerAdapter.addViewToAdapter(imageView);
        imageView = (ImageView) getLayoutInflater().inflate(R.layout.layout_lead_item, null);
        imageView.setImageResource(R.drawable.adware_style_banner);
        leadPagerAdapter.addViewToAdapter(imageView);
        imageView = (ImageView) getLayoutInflater().inflate(R.layout.layout_lead_item, null);
        imageView.setImageResource(R.drawable.adware_style_creditswall);
        leadPagerAdapter.addViewToAdapter(imageView);
        leadPagerAdapter.notifyDataSetChanged();
    }



    private void savePreferences() {
        SharedPreferences preferences = getSharedPreferences("lead_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isFirstRun", false);
        editor.commit();
    }
    public void onClick(View v){
        savePreferences();
        if (isFromSetting){
            startActivity(SettingActivity.class);
        }
        else {
            startActivity(LogoActivity.class);
        }
        finish();
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {

    }



    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }  @Override
    public void onPageSelected(int arg0) {
        tv_skip.setVisibility(View.INVISIBLE);
        if (arg0 >= 2) {
            tv_skip.setVisibility(View.VISIBLE);
        }
        for (int i = 0; i < icons.length; i++) {
            icons[i].setImageResource(R.drawable.adware_style_default);
        }
        icons[arg0].setImageResource(R.drawable.adware_style_selected);
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String fromClassName = intent.getStringExtra("className");
        if (fromClassName != null && fromClassName.equals("SettingActivity")) {
            isFromSetting = true;
        }
        SharedPreferences preferences = getSharedPreferences("lead_config", Context.MODE_PRIVATE);
        boolean isFirstRun = preferences.getBoolean("isFirstRun", true);
        if (!isFirstRun) {
            startActivity(LogoActivity.class);
            finish();
        } else {
            setContentView(R.layout.activity_lead);
            initLeadIcon();
            initViewPager();
            initPagerData();

        }
        finish();}


}
