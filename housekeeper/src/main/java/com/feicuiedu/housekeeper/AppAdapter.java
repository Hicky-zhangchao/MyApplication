package com.feicuiedu.housekeeper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.renderscript.Element;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 张超 on 2016/11/9.
 */

public class AppAdapter extends BaseDataAdapter<Element.DataType> {

    private LayoutInflater layoutInflater;
    @SuppressLint("NewApi")

    public AppAdapter(Context context) {
        super(context);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        BitmapUtil.SizeMessage sizeMessage = new BitmapUtil.SizeMessage(context, false, 60, 60);
    }

    @Override
    public View  getItemView(int position, View convertView, ViewGroup parent) {
        ImageView iv_icon;
        TextView tv_title;
        TextView tv_text;
        TextView tv_version;
        CheckBox cb_del;
        // 将 dataList 里的所有数据对应适配 layout_software_listitem.xml 布局文件上
        AppInfo appInfo = getItem(position);
        iv_icon = (ImageView) convertView.findViewById(R.id.iv_app_icon);
        tv_title = (TextView) convertView.findViewById(R.id.tv_app_lable);
        tv_text = (TextView)convertView.findViewById(R.id.tv_app_packagename);
        tv_version = (TextView) convertView.findViewById(R.id.tv_app_version);
        cb_del = (CheckBox) convertView.findViewById(R.id.cb_del);
        String title = appInfo.getPackageInfo().applicationInfo.loadLabel(context.getPackageManager()).toString();
        //包名
        String text = appInfo.getPackageInfo().packageName;
        //版本
        String version = appInfo.getPackageInfo().versionName;
        boolean isDel = appInfo.isDel();
        iv_icon.setImageResource(position);
        tv_title.setText(title);
        tv_text.setText(text);
        tv_version.setText(version);
        cb_del.setOnCheckedChangeListener(checkedChangeListener);
        return convertView;
    }
    private CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener(){

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int position = (Integer) buttonView.getTag();
            getDataList().get(position).setDel(isChecked);
        }
    };
}
