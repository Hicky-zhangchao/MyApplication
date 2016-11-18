package com.feicuiedu.housekeeper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.lang.ref.ReferenceQueue;
import java.util.Hashtable;

/**
 * Created by 张超 on 2016/11/10.
 */

public class BitmapCache  {
    private BitmapCache(){
        hashRefs = new Hashtable<Integer,MySoftRef>();
        q = new ReferenceQueue<Bitmap>();
    }
    public static BitmapCache getInstance(){
        if (cache==null){
            cache = new BitmapCache();
        }
        return cache;
    }
    public void addCacheBitmap(Bitmap bmp,Integer key){
        cleanCache();   //清除垃圾引用
        MySoftRef ref = new MySoftRef(bmp,q,key);
        hashRefs.put(key,ref);
    }
    public Bitmap getBitmap(int resId, Context context){
        Bitmap bmp = null;
        //缓存中是否有该Bitmap实例的软引用，如果有，从软引用中取得。
        if (hashRefs.containKey(resId)){
            MySoftRef ref = (MySoftRef)hashRefs.get(resId);
            bmp = (Bitmap)ref.get();
        }
        //如果没有软引用，或者从软引用中得到实例是null，重新构造一个实例并保存对这个新建实例的软引用
        if (bmp == null){
            bmp = BitmapFactory.decodeStream(context.getResources().openRawResource(resId));
            this.addCacheBitmap(bmp,resId);
        }
        return bmp;
    }
    private void cleanCache(){
        MySoftRef ref = null;
        while ((ref = (MySoftRef)q.poll())!=null){
            hashRefs.remove(ref._key);
        }
    }
    //清楚Cache内的全部内容
    public void clearCache(){
        cleanCache();
        hashRefs.clear();
        System.gc();
        System.runFinalization();
    }
}
