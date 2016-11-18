package com.feicuiedu.housekeeper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by 张超 on 2016/11/4.
 */

public class TelmsgActivity extends Activity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private TelclassAdapter adapter;

    private void initAppDBFile(){
        if (!DBReader.isExistsTeldbFile()){
            try {
                AssetsDBManager.copyAssetsFileToFile(getApplicationContext(),"db/commonnum.db", DBReader.telFile);
            }
            catch (IOException e){
                ToastUtil.show(this, " 初始通讯大全数据库文件异常 ", Toast.LENGTH_SHORT);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telmsg);
        //初始控件
        listView = (ListView) findViewById(R.id.listview);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telmsg);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initAppDBFile();
        adapter.clearDataTOAdapter();
        adapter.addDataToAdapter(new TelclassInfo(" 本地电话 ", 0));
        try {
            adapter.addDataToAdapter(DBRead.readTeldbClasslist());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_DIAL);
            startActivity(intent);
            return;
        }
        TelclassInfo classInfo = adapter.getItem(position);
        Intent intent = new Intent(this, TellistActivity.class);
        intent.putExtra("idx", classInfo.idx);
        startActivity(intent);
    }
}
