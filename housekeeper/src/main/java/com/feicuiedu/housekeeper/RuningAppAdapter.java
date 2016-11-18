package com.feicuiedu.housekeeper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicuiedu.housekeeper.phone.SpeedupActivity;

import static android.R.attr.bitmap;

/**
 * Created by 张超 on 2016/11/12.
 */

public class RuningAppAdapter extends BaseBaseAdapter<SpeedupActivity.RuningAppInfo> {

    private boolean isFling;                    //是否在快速滑动
    private LayoutInflater layoutInflater;
    private int state = 0;                      //显示用户进程
    public static final int STATE_SHOW_USER = 0;//显示用户进程
    public static final int STATE_SHOW_ALL = 1; //显示全部进程
    public static final int STATE_SHOW_SYS = 2; //显示系统进程
    public boolean isFling(){
        return isFling;
    }
    public void setFling(boolean isFling){
        this.isFling = isFling;
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public View getItemView(int position, View convertView, ViewGroup parent){
        Bitmap icon = getIconBitmap(position);
        holdView.iv_icon.setImageBitmap(icon);
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.layout_speedup_listitem, null);
        }
        TextView tv_lable = (TextView)convertView.findViewById(R.id.tv_app_lable);
        TextView tv_size = (TextView) convertView.findViewById(R.id.tv_app_packagename);
        ImageView iv_icon = (ImageView) convertView.findViewById(R.id.iv_app_icon);
        CheckBox cb_clear = (CheckBox) convertView.findViewById(R.id.cb_clear);
        TextView tv_message = (TextView) convertView.findViewById(R.id.tv_app_version);
        cb_clear.setTag(position);
        cb_clear.setOnCheckedChangeListener(checkedChangeListener); // 监听CheckBox
        tv_lable.setText(getItem(position).getLableName());
        tv_size.setText("内存：" +  CommonUtil.getFileSize(getItem(position).getSize()));
        iv_icon.setImageDrawable(getItem(position).getIcon());
        cb_clear.setChecked(getItem(position).isClear());
        tv_message.setText(getItem(position).isSystem() ? "系统进程" : "");
        return convertView;
    }
    private CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int position = (Integer) buttonView.getTag();
            getItem(position).setClear(isChecked); // 更新当前CheckBox是否选中的实体数据
        }
    };
    public RuningAppAdapter(Context context){
        super(context);
        bitmapCache = BitmapCache.getInstance();
        //图片大小信息
        BitmapUtil.SizeMessage sizeMessage = new BitmapUtil.SizeMessage(context, false, 60, 60);
        //加载默认图片
        defIconBitmap = BitmapUtil.loadBitmap(R.drawable.ic_launcher,
                sizeMessage);
    }
    public Bitmap getIconBitmap(int position){
        if (isFling){
            return defIconBitmap;
        }
        Bitmap bitmap = ((BitmapDrawable)getItem(position).getIcon()).getBitmap();
        bitmapCache.addCacheBitmap(bitmap,position);
        bitmap = bitmapCache.getBitmap(position,context);
        return bitmap;
    }
}

