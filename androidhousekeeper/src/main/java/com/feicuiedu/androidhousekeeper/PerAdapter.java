package com.feicuiedu.androidhousekeeper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 张超 on 2016/11/3.
 */

public class PerAdapter extends BaseAdapter {

    private List<Person> list=null;

    LayoutInflater inflater=null;

    private Context context = null;

    public PerAdapter(Context context, List<Person> list){
        this.list=list;
        this.context = context;
        inflater=LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        if(view != null){
            view=convertView;
        }else {
            view=inflater.inflate(R.layout.list_item,null);
        }

        ImageView iv_img=(ImageView)view.findViewById(R.id.iv_img);
        TextView tv_name=(TextView)view.findViewById(R.id.tv_name);
        ImageView iv_logo=(ImageView)view.findViewById(R.id.iv_logo);

        iv_img.setBackgroundResource(list.get(position).getPer_ic());
        tv_name.setText(list.get(position).getPer_name());
        final String number=list.get(position).getPer_number();
        iv_logo.setBackgroundResource(list.get(position).getPer_lic());
        iv_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri uri = Uri.parse("tel:" + number);
                intent.setData(uri);
                context.startActivity(intent);

            }
        });

        return view;
    }


//    public void addListener(final View view){
//
//        view.findViewById(R.id.iv_logo).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            Log.d("demo","mdsfsnf========");
//
//            }
//        });
//
//    }




}