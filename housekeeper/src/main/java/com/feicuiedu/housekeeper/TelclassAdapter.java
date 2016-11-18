package com.feicuiedu.housekeeper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 张超 on 2016/11/4.
 */

public class TelclassAdapter  extends BaseAdapter {

    private LayoutInflater layoutInflater;
    // 当前适配器内的数据集合 (当前适配器适配工作只认此集合)
    private ArrayList<TelclassInfo> adapterDatas = new ArrayList<TelclassInfo>();
    public TelclassAdapter(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return  adapterDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return  adapterDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.inflate_telmgr_listitem, null);
        }
        TextView tv_text = (TextView) convertView.findViewById(R.id.textview);
        tv_text.setText(getItem(position).name);
        return convertView;
    }
    // 添加数据到当前适配器集合
    public void addDataToAdapter(TelclassInfo e) {

        if (e != null) {
            adapterDatas.add(e);
        }
    }

    public ArrayList<TelclassInfo> getDataFromAdapter() {
        return adapterDatas;
    }

    public void clearDataTOAdapter() {
        adapterDatas.clear();
    }


}