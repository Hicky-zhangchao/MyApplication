package com.feicuiedu.androidhousekeeper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张超 on 2016/11/2.
 */

public class CommuniCationActivity extends Activity implements View.OnClickListener {

    private List<Person> mlist=null;

    private ListView listView=null;

    private ImageView ll_return=null;

    private ImageView iv_select=null;

    private EditText et_pname=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.communication_activity);

        ll_return=(ImageView)findViewById(R.id.ll_return);
        et_pname=(EditText)findViewById(R.id.et_pname);
        iv_select=(ImageView)findViewById(R.id.iv_select);
        listView=(ListView)findViewById(R.id.listView);



        //注册监听器
        iv_select.setOnClickListener(this);
        ll_return.setOnClickListener(this);


        mlist=new ArrayList<>();
        //查询手机的电话簿
        Cursor cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null,null);
        //获取电话簿信息
        while(cursor.moveToNext()) {
            //备注姓名
            String c_name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            //号码
            String c_number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            Person person=new Person(R.drawable.lianxiren,c_name,c_number,R.drawable.bohao);

            mlist.add(person);
        }


        final PerAdapter adapter=new PerAdapter(this,mlist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Person per = (Person) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), per.getPer_name() + "\t" + per.getPer_number(), Toast.LENGTH_SHORT).show();


            }
        });



    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        listView=null;
        ll_return=null;
    }

    @Override
    public void onClick(View v) {

        if(v == ll_return){
            this.finish();
        }
        if(v == iv_select){

            int k=0;

            for(int i=0;i<mlist.size();i++){
                Person per=mlist.get(i);
                if((et_pname.getText().toString()).equals(per.getPer_name())){

                    AlertDialog.Builder builder=new AlertDialog.Builder(this);
                    final String strnum="tel:"+per.getPer_number();
                    builder.setTitle("查询到信息如下");
                    builder.setMessage("姓名：" + per.getPer_name() + "\n" + "号码：" + per.getPer_number());
                    builder.setPositiveButton("拨打电话", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(Intent.ACTION_CALL);
                            Uri uri = Uri.parse(strnum);
                            intent.setData(uri);
                            startActivity(intent);

                        }
                    });

                    builder.setNegativeButton("转到拨号页面", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent=new Intent(Intent.ACTION_DIAL);
                            Uri uri=Uri.parse(strnum);
                            intent.setData(uri);
                            startActivity(intent);

                        }
                    });
                    builder.show();

                }else {
                    k++;
                    if(k >= mlist.size()){
                        Toast.makeText(this,"查询信息不存在！",Toast.LENGTH_SHORT).show();
                    }
                }


            }

        }


    }
}
