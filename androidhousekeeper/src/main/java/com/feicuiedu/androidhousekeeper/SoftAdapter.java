package com.feicuiedu.androidhousekeeper;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 张超 on 2016/11/3.
 */

public class SoftAdapter extends BaseAdapter {

    private List<SoftInformation> list=null;

    private LayoutInflater inflater=null;

    private PackageManager packageManager=null;

    private Context context=null;


    public SoftAdapter(Context context, PackageManager packageManager, List<SoftInformation> list){
        this.list=list;
        this.context=context;
        this.packageManager=packageManager;
        this.inflater=LayoutInflater.from(context);

    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view=null;

        if(convertView !=null){
            view=convertView;
        }else {
            view=inflater.inflate(R.layout.softinfo_item,null);
        }

        SoftInformation softInformation=list.get(position);

        Bitmap bmp=((BitmapDrawable)(softInformation.getPackageInfo().applicationInfo.loadIcon(packageManager))).getBitmap();


        final CheckBox cb_checkbox=(CheckBox)view.findViewById(R.id.cb_checkbox);
        ImageView iv_applog=(ImageView)view.findViewById(R.id.iv_applog);
        TextView tv_appname=(TextView)view.findViewById(R.id.tv_appname);
        TextView tv_appdao=(TextView)view.findViewById(R.id.tv_appdao);
        TextView tv_appversion=(TextView)view.findViewById(R.id.tv_appversions);

        if(softInformation.isDel()){
            cb_checkbox.setChecked(true);
        }else {
            cb_checkbox.setChecked(false);
        }


        cb_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断选择的app，改变list集合中的CheckBox状态
                if(cb_checkbox.isChecked()){
                    cb_checkbox.setChecked(true);
                    list.get(position).setIsDel(true);
                } else {
                    cb_checkbox.setChecked(false);
                    list.get(position).setIsDel(false);
                }
            }
        });


        iv_applog.setImageBitmap(bmp);
        tv_appname.setText(softInformation.getPackageInfo().applicationInfo.loadLabel(packageManager).toString());
        tv_appdao.setText(softInformation.getPackageInfo().packageName);
        tv_appversion.setText(softInformation.getPackageInfo().versionName);

        return view;
    }

}
