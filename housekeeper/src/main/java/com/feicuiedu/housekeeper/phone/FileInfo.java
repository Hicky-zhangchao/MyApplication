package com.feicuiedu.housekeeper.phone;

import java.io.File;

/**
 * Created by 张超 on 2016/11/16.
 */

public class FileInfo {

    private File file;
    private boolean isSelect;
    //图标图像文件名
    private String iconName;
    //文件分类
    private String fileType;

    public FileInfo(File file,String iconName,String fileType){
        super();
        this.file = file;
        this.iconName = iconName;
        this.fileType = fileType;
        isSelect = false;
    }
    public String getIconName(){
        return iconName;
    }
    public void setIconName(){
        this.iconName = iconName;
    }
    public String getFileType(){
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }
    public boolean isSelect() {
        return isSelect;
    }
    public void setSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }
}
