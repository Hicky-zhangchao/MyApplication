package com.feicuiedu.housekeeper;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 张超 on 2016/11/4.
 */

public class AssetsDBManager {

    public static void copyAssetsFileToFile(Context context, String path, File toFile) throws IOException {
        LogUtil.d("AssetsDBManager", "copyAssetsFileToFile start");
        LogUtil.d("AssetsDBManager", "file path:" + path);
        LogUtil.d("AssetsDBManager", "toFile path:" + toFile.getAbsolutePath());
        InputStream inStream = null;
        // 输入流(用来读取当前项目的 Assets 内的 db 文本)
        BufferedInputStream buffInputStream = null;
        //输出流(用来将读取到的 db 信息写到指定目录文件 toFile 中去)
        BufferedOutputStream buffOutputStream = null;
        try {    inStream = context.getAssets().open(path);
            buffInputStream = new BufferedInputStream(inStream);
            buffOutputStream = new BufferedOutputStream(      new FileOutputStream(toFile, false));
            // IO 操作
            int len = 0;
            byte[] buff = new byte[2 * 1024];
            while ((len = buffInputStream.read(buff)) != -1) {
                buffOutputStream.write(buff, 0, len);
            }
            buffOutputStream.flush();
        }
        catch (IOException e) {
            // TODO: handle exception
             throw e;
        }
        finally{
            // IO 关闭
            buffOutputStream.close();
            buffInputStream.close();
            inStream.close();
            LogUtil.d("AssetsDBManager", "copyAssetsFileToFile end");
        }
    }

}
