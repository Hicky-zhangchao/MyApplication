package com.feicuiedu.housekeeper;

import android.graphics.Bitmap;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Hashtable;

/**
 * Created by 张超 on 2016/11/10.
 */

public class MySoftRef extends SoftReference<Bitmap> {

    private Integer _key = 0;


    public MySoftRef(Bitmap bmp, ReferenceQueue<Bitmap> q, int key) {
        super(bmp,q);
        _key = key;
    }

}
