package com.feicuiedu.housekeeper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import static android.R.attr.permission;

/**
 * Created by 张超 on 2016/11/4.
 */

public class TellistActivity extends Activity  implements AdapterView.OnItemClickListener {

    private ListView listView;
    private TellistAdapter adapter;
    private int idx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tellist);
        //获取数据用来判断是显示哪一种分类的电话号码列表
        idx = getIntent().getIntExtra("idx",-1);
        // 初始控件
        listView = (ListView) findViewById(R.id.listview);
        adapter = new TellistAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String name = adapter.getItem(position).name;
        String number = adapter.getItem(position).number;
        showCallDialog(name, number);
    }
    @Override
    protected void onResume() {
        super.onResume();
        // 添加数据
        try {
            adapter.addDataToAdapter(DBReader.readTeldbTable(idx));
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
               e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
    }
    private void showCallDialog(final String name, final String number) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(" 警告 ");
        builder.setMessage(" 是否开始拨打 " + name + " 电话 ? \n\nTEL ： " + number);
        builder.setNegativeButton(" 取消 ", null);   builder.setPositiveButton(" 拨号 ", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //电话拨打
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel://" + number));
                startActivity(intent);
            }
        });
        builder.show();
    }

}
