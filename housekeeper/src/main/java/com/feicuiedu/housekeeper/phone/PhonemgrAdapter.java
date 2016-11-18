package com.feicuiedu.housekeeper.phone;



import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicuiedu.housekeeper.BaseBaseAdapter;
import com.feicuiedu.housekeeper.R;

/**
 * Created by 张超 on 2016/11/15.
 */

public class PhonemgrAdapter extends BaseBaseAdapter<PhoneInfo> {


    @Override
    public View getItemView(int position, View convertView, ViewGroup parent){
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.layout_phonemgr_listitem, null);
        }
        PhoneInfo phoneInfo = getItem(position);
        ImageView icon = (ImageView) convertView.findViewById(R.id.iv_phonemgr_icon);
        TextView title = (TextView)convertView.findViewById(R.id.tv_phonemgr_title);
        TextView text = (TextView) convertView.findViewById(R.id.tv_phonemgr_text);
        icon.setImageDrawable(phoneInfo.getIcon());
        title.setText(phoneInfo.getTitle());
        text.setText(phoneInfo.getText());
        // 给每个图加不同背景(无实际作用)
        switch (position % 3){
            case 0:
                icon.setBackgroundResource(R.drawable.notification_information_progress_green);
                break;
            case 1:
                icon.setBackgroundResource(R.drawable.notification_information_progress_red);
                break;
            case 2:
            default:
                icon.setBackgroundResource(R.drawable.notification_information_progress_yellow);
                break;
        }
        return convertView;
    }
}
