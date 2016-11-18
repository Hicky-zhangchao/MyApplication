package com.feicuiedu.housekeeper;

/**
 * Created by 张超 on 2016/11/4.
 */

public class TelclassInfo {

    //电话名称
    public String name;
    //唯一 ID
    public int idx;

    //重写构造方法
    public TelclassInfo(String name, int idx) {
        super();
        this.name = name;
        this.idx = idx;
    }

    //系统默认构造方法
    public TelclassInfo() {
        super();
    }

}
