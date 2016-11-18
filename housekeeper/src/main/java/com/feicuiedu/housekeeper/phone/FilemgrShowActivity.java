package com.feicuiedu.housekeeper.phone;

import android.app.Notification;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.feicuiedu.housekeeper.BaseActivity;
import com.feicuiedu.housekeeper.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张超 on 2016/11/17.
 */

public class FilemgrShowActivity extends BaseActivity {

    private int id;//确认当前页面内容
    private TextView textViewSize; // 文件大小
    private TextView textViewNumber;// 文件数量
    private Button btn_delall; // 删除所选文件
    private ListView fileListView; // 文件列表
    private FileAdapter fileAdapter; // 文件列表适配
    private ArrayList<FileInfo> fileInfos;
    private long fileSize = 0;
    private long fileNumber = 0;
    private String title = "全部文件";

    @Override
    protected void myHandleMessage(Notification.MessagingStyle.Message msg) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filemgrshow);
// #取得ID 数据 -- 从哪进入的当前文件浏览界面（全部？图像？音乐？视频?）
        id = getIntent().getIntExtra("id", -1);
        // #初始化加在所需数据
        initMainData(id);
// #初始化ActionBar @see super class ActionBarActivity
        initActionBar(title, R.drawable.btn_homeasup_default, -1, clickListener);
// #初始化当前页面主要控件 (文件总大小textview，文件总量textview，文件列表listview )
        initMainUI();
        // #初始化当前页面主要控件上的数据
        initMainUIData();
    }
    private void initMainData(int viewID) {
        fileInfos = new ArrayList<FileInfo>();
        switch (viewID) {
            case R.id.file_classlist_all:
//获取文件大小
                fileSize = FileManager.getFileManager().getAnyFileSize();
//获取文件列表
                fileInfos = FileManager.getFileManager().getAnyFileList();
//获取标题名称
                title ="全部文件";
                break;
            case R.id.file_classlist_txt:
                fileSize = FileManager.getFileManager().getTxtFileSize();
                fileInfos = FileManager.getFileManager().getTxtFileList();
                title ="文档";
                break;
            case R.id.file_classlist_video:
                fileSize = FileManager.getFileManager().getVideoFileSize();
                fileInfos = FileManager.getFileManager().getVideoFileList();
                title ="视频";
                break;
            case R.id.file_classlist_audio:
                fileSize = FileManager.getFileManager().getAudioFileSize();
                fileInfos = FileManager.getFileManager().getAudioFileList();
                title ="音频";
                break;
            case R.id.file_classlist_image:
                fileSize = FileManager.getFileManager().getImageFileSize();
                fileInfos = FileManager.getFileManager().getImageFileList();
                title ="图像";
                break;
            case R.id.file_classlist_apk:
                fileSize = FileManager.getFileManager().getApkFileSize();
                fileInfos = FileManager.getFileManager().getApkFileList();
                title ="程序包";
                break;
            case R.id.file_classlist_zip:
                fileSize = FileManager.getFileManager().getZipFileSize();
                fileInfos = FileManager.getFileManager().getZipFileList();
                title ="压缩包";
                break;
        }
        fileNumber = fileInfos.size();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        FileInfo fileInfo = fileAdapter.getItem(position);
        File file = fileInfo.getFile();
// 取出此文件的后缀名 －> MIMEType
        String type = FileTypeUtil.getMIMEType(file);
// 打开这个文件，
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), type);
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        int viewID = v.getId();
        switch (viewID) {
// 返回键
            case R.id.iv_left:
                finish();
                break;
// 删除键
            case R.id.btn_delall:
                delFile();
                break;
            default:
                break;
        }
    }
    public void delFile() {
// 用来保存所有选中的删除文件
        List<FileInfo> delFileInfos = new ArrayList<FileInfo>();
        for (int i = 0; i < fileAdapter.getDataList().size(); i++) {
            FileInfo fileInfo = fileInfos.get(i);
// 已选中的
            if (fileInfo.isSelect()) {
                delFileInfos.add(fileInfo);
            }
        }
// 删除所选中的文件
        for (int i = 0; i < delFileInfos.size(); i++) {
            FileInfo fileInfo = delFileInfos.get(i);
            File file = fileInfo.getFile();
            long size = file.length();
//为true 表示删除成功
            if (file.delete()) {
                fileAdapter.getDataList().remove(fileInfo);
                FileManager.getFileManager().getAnyFileList().remove(fileInfo);
                FileManager.getFileManager().setAnyFileSize(FileManager.getFileManager().getAnyFileSize() - size);
//对应更新界面
                switch (id) {
                    case R.id.file_classlist_txt:
//在集合中移除该文件，并修改总文件大小
                        FileManager.getFileManager().getTxtFileList().remove(fileInfo);
                        FileManager.getFileManager().setTxtFileSize(FileManager.getFileManager().getTxtFileSize() - size);
                        break;
                    case R.id.file_classlist_video:
                        FileManager.getFileManager().getVideoFileList().remove(fileInfo);
                        FileManager.getFileManager().setVideoFileSize(FileManager.getFileManager().getVideoFileSize() - size);
                        break;
                    case R.id.file_classlist_audio:
                        FileManager.getFileManager().getAudioFileList().remove(fileInfo);
                        FileManager.getFileManager().setAudioFileSize(FileManager.getFileManager().getAudioFileSize() - size);
                        break;
                    case R.id.file_classlist_image:
                        FileManager.getFileManager().getImageFileList().remove(fileInfo);
                        FileManager.getFileManager().setImageFileSize(FileManager.getFileManager().getImageFileSize() - size);
                        break;
                    case R.id.file_classlist_apk:
                        FileManager.getFileManager().getApkFileList().remove(fileInfo);
                        FileManager.getFileManager().setApkFileSize(FileManager.getFileManager().getApkFileSize()- size);
                        break;
                    case R.id.file_classlist_zip:
                        FileManager.getFileManager().getZipFileList().remove(fileInfo);
                        FileManager.getFileManager().setZipFileSize(FileManager.getFileManager().getZipFileSize() - size);
                        break;
                }
            }
        }
              // 更新列表
        fileAdapter.notifyDataSetChanged();
               // 获取文件数量
        fileNumber = fileAdapter.getDataList().size();
             // 显示
        textViewNumber.setText(fileNumber + "个");
            //调用系统回收
        System.gc();
           // 放弃线程当前执行权
        Thread.yield();
    }
}
