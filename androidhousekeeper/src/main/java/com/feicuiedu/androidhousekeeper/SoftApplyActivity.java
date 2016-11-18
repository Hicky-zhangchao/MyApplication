package com.feicuiedu.androidhousekeeper;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张超 on 2016/11/2.
 */


public class SoftApplyActivity extends Activity implements View.OnClickListener {

    //返回监听
    private ImageView iv_fanhui=null;

    private SoftAdapter madapter=null;

    private ListView listView=null;

    private PackageManager packageManager=null;

    private List<SoftInformation> list=null;

    private CheckBox cb_cball=null;

    private Button btn_del=null;

    private AppDelRecevice appDelRecevice=null;

    private List<SoftInformation> list_del=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.softapply_activity);
        //注册广播接收器
        appDelRecevice = new AppDelRecevice();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addDataScheme("package");
        filter.addAction(AppDelRecevice.ACTION_APPDEL);
        registerReceiver(appDelRecevice, filter);



        init();

        packageManager=this.getPackageManager();

//        List<PackageInfo> infos=packageManager.getInstalledPackages(PackageManager.GET_ACTIVITIES|PackageManager.GET_UNINSTALLED_PACKAGES);

        List<PackageInfo> infos=packageManager.getInstalledPackages(0);


        list=new ArrayList<>();
        for(int i=0;i<infos.size();i++){
            PackageInfo pi=infos.get(i);
            if((pi.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)!=0) {

            }else {
                SoftInformation softInformation = new SoftInformation(pi, false);
                list.add(softInformation);
            }
        }
        madapter=new SoftAdapter(this,packageManager,list);
        listView.setAdapter(madapter);




    }

    private void init(){

        iv_fanhui=(ImageView)findViewById(R.id.iv_fanhui);
        listView=(ListView)findViewById(R.id.listView);
        cb_cball=(CheckBox)findViewById(R.id.cb_cball);
        btn_del=(Button)findViewById(R.id.btn_del);


        //注册监听器
        btn_del.setOnClickListener(this);
        cb_cball.setOnClickListener(this);
        iv_fanhui.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {

        if(v == iv_fanhui){

            this.finish();
        }

        if(v == cb_cball){
            if(cb_cball.isChecked()){

                for(int i=0;i<list.size();i++){
                    list.get(i).setIsDel(true);
                }
                madapter=new SoftAdapter(this,packageManager,list);
                listView.setAdapter(madapter);
                madapter.notifyDataSetChanged();

            }else{
                for(int i=0;i<list.size();i++){
                    list.get(i).setIsDel(false);
                }
                madapter=new SoftAdapter(this,packageManager,list);
                listView.setAdapter(madapter);
                madapter.notifyDataSetChanged();
            }

        }
        if(v == btn_del){
            int s=0;
            for(int i=0;i<list.size();i++){
                if(list.get(i).isDel()){
                    s++;
                }
            }


            if(s !=0){

                final String[] appname=new String[s];
                int k=0;

                for(int i=0;i<list.size();i++){
                    if(list.get(i).isDel()){
                        String name = list.get(i).getPackageInfo().applicationInfo.loadLabel(packageManager).toString();
                        String packageName = list.get(i).getPackageInfo().packageName;
                        appname[k]=name;
                        k++;
//                    Log.d("demo","soft: " + i + "---" + name + "---" + packageName);
                    }
                }


                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("是否卸载以下选中的软件？");

                builder.setItems(appname, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                builder.setPositiveButton("确认删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        list_del = new ArrayList<SoftInformation>();
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).isDel()) {
                                list_del.add(list.get(i));
                            }
                        }

                        uninstApp();


                    }
                });
                builder.setNegativeButton("取消卸载", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "卸载已取消", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();


            }else {
                Toast.makeText(getApplicationContext(),"你没有选择任何要卸载的软件！",Toast.LENGTH_SHORT).show();
            }


        }


    }

    int n=0;
    /** 删除app方法 */
    public void uninstApp(){

        if(n < list_del.size()){
            String packageName=list_del.get(n).getPackageInfo().packageName;
            Uri packageURI=Uri.parse("package:"+packageName);
            Intent uninstallIntent=new Intent(Intent.ACTION_DELETE,packageURI);
            startActivity(uninstallIntent);
            n++;

        }else {
            Toast.makeText(getApplicationContext(), "成功卸载", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),"选择的应用已卸载",Toast.LENGTH_SHORT).show();
        }

    }


    /**
     * 应用删除广播接收器
     * Created by lyb on 2016/8/29.
     */
    public class AppDelRecevice extends BroadcastReceiver {

        public static final String ACTION_APPDEL="com.androidy.app.phone.del";

        @Override
        public void onReceive(Context context, Intent intent) {

            String action=intent.getAction();

            if(action.equals(Intent.ACTION_PACKAGE_REMOVED)||action.equals(ACTION_APPDEL)){

                Log.d("demo", "卸载完成");

                uninstApp();
            }

        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        iv_fanhui=null;
        unregisterReceiver(appDelRecevice);
    }
}
