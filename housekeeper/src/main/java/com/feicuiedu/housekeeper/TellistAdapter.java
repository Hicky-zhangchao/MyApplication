package com.feicuiedu.housekeeper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张超 on 2016/11/4.
 */

public class TellistAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private ArrayList<TelnumberInfo> adapterDatas = new ArrayList<TelnumberInfo>();

    public void addDataToAdapter(TelnumberInfo e) {
        if (e != null) {
            adapterDatas.add(e);
        }
    public void addDataToAdapter(List<TelnumberInfo> e) {
        if (e != null) {
            adapterDatas.addAll(e);
        }
           }


    public void replaceDataToAdapter(List<TelnumberInfo> e) {
        if (e != null) {
            adapterDatas.clear();
            adapterDatas.addAll(e);
        }

    }


    public ArrayList<TelnumberInfo> getDataFromAdapter() {
        return adapterDatas;
    }
    @Override
    public int getCount() {
        return  adapterDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.inflate_tellist_listitem, null);
        }
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_number = (TextView) convertView.findViewById(R.id.tv_number);
        tv_name.setText(getItem(position).name);   tv_number.setText(getItem(position).number + "");
        return convertView;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public TellistAdapter(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


}