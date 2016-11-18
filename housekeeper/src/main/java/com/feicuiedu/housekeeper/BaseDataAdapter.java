package com.feicuiedu.housekeeper;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张超 on 2016/11/7.
 */

public abstract class BaseDataAdapter<E>extends BaseAdapter {

    protected LayoutInflater layoutInflater;// 布局加载器
    private ArrayList<E> adapterDatas = new ArrayList<E>();
    protected Context context;
    public BaseDataAdapter(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override  public int getCount() {
        return adapterDatas.size();
    }
    @Override
    public E getItem(int position) {
        // TODO Auto-generated method stub
        return adapterDatas.get(position);
    }
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
          return position;
    }
    public ArrayList<E> getDataFromAdapter() {
        return adapterDatas;
    }
    public void setDataToAdapter(List<E> e) {
        adapterDatas.clear();
        if (e != null){
            adapterDatas.addAll(e);
        }
    }
    public void addDataToAdapter(List<E> e) {
        if (e != null) {
            adapterDatas.addAll(e);
        }
    }
    public void clearAdapter() {
        adapterDatas.clear();
    }
    public void removeDataFromAdapter(E e){
        adapterDatas.remove(e);
    }
}
