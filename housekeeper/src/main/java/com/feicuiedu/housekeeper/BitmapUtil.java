package com.feicuiedu.housekeeper;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Size;

/**
 * Created by 张超 on 2016/11/10.
 */

public class BitmapUtil {
    public static Bitmap loadBitmap(String pathName,SizeMessage sizeMessage){
        //加载的图像目标大小
        int targetw = sizeMessage.getW();
        int targeth = sizeMessage.getH();
        Context context = sizeMessage.getContext();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//打开边界处理

        BitmapFactory.decodeFile(pathName,options);
        int resw = options.outWidth;
        int resh = options.outHeight;
        if (resw<=targetw && resh <= targeth){
            options.inSampleSize = 1;//设置加载时的资源比例
        }
        else {
            int scalew = resw / targetw;
            int scaleh = resh / targeth;
            int scale = scalew > scaleh ? scalew : scaleh;
            options.inSampleSize = scale;//设置加载时的资源比例
        }
        options.inJustDecodeBounds = false;//关闭边界处理
        Bitmap bitmap = BitmapFactory.decodeFile(pathName,options);
        return bitmap;
    }
    public static Bitmap loadBitmap(int resID,SizeMessage sizeMessage) {
        //加载的图像目标大小
        int targetw = sizeMessage.getW();
        int targeth = sizeMessage.getH();
        Context context = sizeMessage.getContext();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//打开边界处理

        BitmapFactory.decodeResource(context.getResources(),resID,options);
        int resw = options.outWidth;
        int resh = options.outHeight;
        if (resw <= targetw && resh <= targeth) {
            options.inSampleSize = 1;//设置加载时的资源比例
        } else {
            int scalew = resw / targetw;
            int scaleh = resh / targeth;
            int scale = scalew > scaleh ? scalew : scaleh;
            options.inSampleSize = scale;//设置加载时的资源比例
        }
        options.inJustDecodeBounds = false;//关闭边界处理
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),resID,options);
        return bitmap;
    }
      public static class SizeMessage{
          private int w;
          private int h ;
          private Context context;
          public SizeMessage(Context context,boolean isPx,int w,int h){
              this.context = context;
              if (!isPx){
                  w = DeviceUtil.dp2px(context,w);
                  h = DeviceUtil.dp2px(context,h);
              }
              this.w = w;
              this.h = h;
          }
          public Context getContext(){
              return context;
          }
          public int getW(){
              return w;
          }
          public void setW(int w){
              this.w = w;
          }
          public int getH(){
              return h;
          }
          public void setH(int h){
              this.h = h;
          }
      }
}
