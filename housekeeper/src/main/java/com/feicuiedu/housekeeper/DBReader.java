package com.feicuiedu.housekeeper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by 张超 on 2016/11/4.
 */

public class DBReader {

    public static File telFile;

    static {
        // 默认位置
        String dbFileDir = "data/data/edu.feicui.contacts/";
        File fileDir = new File(dbFileDir);
        fileDir.mkdirs();
        // 文件目录的创建
        telFile = new File(dbFileDir, "commonnum.db");
        LogUtil.d("DBRead", "telFile dir path: " + dbFileDir);
    }

    public static boolean isExistsTeldbFile() {
        // 没有通讯大全 File
        File toFile = DBReader.telFile;
        if (!toFile.exists() || toFile.length() <= 0) {
            return false;
        }
        return true;
    }

    public static ArrayList<TelclassInfo> readTeldbClasslist() throws Exception {
        ArrayList<TelclassInfo> classListInfos = new ArrayList<TelclassInfo>();
        // 打开 DB 文件
        SQLiteDatabase db = null;
        // 执行查询的 SQL 语句select * from classlist
        Cursor cursor = null;
        try {
            db = SQLiteDatabase.openOrCreateDatabase(telFile, null);
            cursor = db.rawQuery("select * from classlist", null);
            LogUtil.d("DBRead", "read teldb classlist size: " + cursor.getCount());
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                //idx 为 classlist 表中电话的 ID，根据 idx 值进行指定页面的跳转
                int idx = cursor.getInt(cursor.getColumnIndex("idx"));
                TelclassInfo classListInfo = new TelclassInfo(name, idx);
                classListInfos.add(classListInfo);
            }
            while (cursor.moveToNext());
        }
        catch (Exception e){
            // TODO: handle exception
            throw e;
        }
        finally{
            try{
                cursor.close();
                db.close();
            }
            catch (Exception e2){
                // TODO: handle exception
                   throw e2;
            }
            LogUtil.d("DBRead", "read teldb classlist end [list size]: " + classListInfos.size());
        }
        return classListInfos;
    }
    public static ArrayList<TelnumberInfo> readTeldbTable(int idx) throws Exception{
        ArrayList<TelnumberInfo> numberInfos = new ArrayList<TelnumberInfo>();
        //idx 为 classlist 表中电话的 ID，根据 idx 值进行指定页面的跳转
        String sql = "select * from table" + idx;
        SQLiteDatabase db = null;
        Cursor cursor  = null;
        try{
            // 打开 DB 文件
            db = SQLiteDatabase.openOrCreateDatabase(telFile, null);
            // 执行查询的 SQL 语句 select * from table +idx
            cursor = db.rawQuery(sql, null);
            LogUtil.d("DBRead", "read teldb number table size: "+ cursor.getCount());
            if (cursor.moveToFirst()){
                do{
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String number = cursor.getString(cursor.getColumnIndex("number"));
                    TelnumberInfo numberInfo = new TelnumberInfo(name,number);
                    numberInfos.add(numberInfo);
                }
                while (cursor.moveToNext());
            }
        }
        catch (Exception e){
            // TODO: handle exception
               throw e;
        }
        finally {
            try {
                cursor.close();
                db.close();
            }
            catch (Exception e2){
                // TODO: handle exception
                throw e2;
            }
            LogUtil.d("DBRead","read teldb number table end [list size]: "+ numberInfos.size());
        }
        return numberInfos;
    }
}