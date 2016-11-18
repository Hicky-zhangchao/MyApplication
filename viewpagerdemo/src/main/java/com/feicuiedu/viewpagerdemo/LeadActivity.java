package com.feicuiedu.viewpagerdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 张超 on 2016/10/31.
 */

public class LeadActivity extends AppCompatActivity {

    private ImageView[] icons = new ImageView[3];
    private TextView tv_skip;
    private ViewPager viewPager;
    private BasePagerAdapter leadPagerAdapter;
   private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
       @Override
       public void onPageSelected(int arg0) {
           //到达最后一个page时，显示出skip文本
           tv_skip.setVisibility(View.INVISIBLE);
           if (arg0 >= 2){
               tv_skip.setVisibility(View.VISIBLE);
           }
           //更新下标图标
           for (int i = 0; i<icons.length;i++){
               icons[i].setImageResource(R.drawable.adware_style_default);
           }
           icons[arg0].setImageResource(R.drawable.adware_style_selected);
       }

       @Override
       public void onPageScrolled(int aeg0,float arg1,int arg2) {

       }

       @Override
       public void onPageScrollStateChanged(int arg0) {

       }
   };

    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lead_main);
        initLeadIcon();
        initViewPager();
        initPagerDate();
    }
    private void initLeadIcon(){
        icons[0] = (ImageView)findViewById(R.id.icon1);
        icons[1] = (ImageView)findViewById(R.id.icon2);
        icons[2] = (ImageView)findViewById(R.id.icon3);
        icons[0].setImageResource(R.drawable.adware_style_selected);
        tv_skip = (TextView)findViewById(R.id.tv_skip);
        tv_skip.setVisibility(View.INVISIBLE);
        //tv_skip.setOnClickListener(this);
    }
    /**
     * 初始化ViewPager
     */
    private void initViewPager(){
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        leadPagerAdapter = new BasePagerAdapter(this);
        viewPager.setAdapter(leadPagerAdapter);
        //viewPager.setOnPageChangeListener(pageChangeListener);
    }
    /**
     * 初始化页面信息
     */
    private void initPagerDate(){
        ImageView imageView = null;
        imageView = (ImageView)getLayoutInflater().inflate(R.layout.activity_lead_item,null);
        imageView.setImageResource(R.drawable.adware_style_applist);
        leadPagerAdapter.addViewToAdapter(imageView);
        imageView = (ImageView)getLayoutInflater().inflate(R.layout.activity_lead_item,null);
        imageView.setImageResource(R.drawable.adware_style_banner);
        leadPagerAdapter.addViewToAdapter(imageView);
        imageView = (ImageView)getLayoutInflater().inflate(R.layout.activity_lead_item,null);
        imageView.setImageResource(R.drawable.adware_style_creditswall);
        leadPagerAdapter.addViewToAdapter(imageView);
        leadPagerAdapter.notifyDataSetChanged();
    }
}
