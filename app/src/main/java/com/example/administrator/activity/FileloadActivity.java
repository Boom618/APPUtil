package com.example.administrator.activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.apputils.R;
import com.example.administrator.cacheutils.DataManager;
import com.example.administrator.fileload.FileApi;
import com.example.administrator.fileload.FileCallback;
import com.example.administrator.view.HDialogBuilder;

import java.io.File;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class FileLoadActivity extends AppCompatActivity {

    TextView txtProgress;
    HDialogBuilder hDialogBuilder;
    Button btuLoad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_load);

        btuLoad = (Button) findViewById(R.id.btu_load);


    }


    @Override
    protected void onStart() {
        super.onStart();

        btuLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUpDate(FileLoadActivity.this);
            }
        });


    }

    // ------- 版本更新
    private void checkUpDate(Context context) {
        Toast.makeText(this, "检测更新", Toast.LENGTH_SHORT).show();
        if (!isUpDate(context, 1)) {
            //更新
            String baseUrl = "http://hengdawb-app.oss-cn-hangzhou.aliyuncs.com/";
            String fileName = "app-debug.apk";
            String fileStoreDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File
                    .separator + "M_DEFAULT_DIR";
            String fileStoreName = fileName;
            showLoadingDialog();

            FileApi.getInstance(baseUrl)
                    .loadFileByName(fileName, new FileCallback(fileStoreDir, fileStoreName) {
                        @Override
                        public void onSuccess(File file) {
                            super.onSuccess(file);
                            hDialogBuilder.dismiss();
                        }

                        @Override
                        public void progress(long progress, long total) {
                            updateProgress(progress, total);
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            hDialogBuilder.dismiss();
                            call.cancel();
                        }
                    });

        } else {
            Toast.makeText(context, "当前版本为最新版本,不需要更新", Toast.LENGTH_SHORT).show();
//            final SpotsDialog dialog = new SpotsDialog(context, "当前版本为最新版本,不需要更新");
//            dialog.show();
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    dialog.dismiss();
//                }
//            }, 2000);


        }

    }

    /**
     * 更新下载进度
     *
     * @param progress
     * @param total
     */
    private void updateProgress(long progress, long total) {
        txtProgress.setText(String.format("正在下载：(%s/%s)",
                DataManager.getFormatSize(progress),
                DataManager.getFormatSize(total)));
    }

    /**
     * 显示下载对话框
     */
    private void showLoadingDialog() {
        txtProgress = (TextView) View.inflate(this, R.layout
                .layout_hd_dialog_custom_tv, null);
        hDialogBuilder = new HDialogBuilder(this);
        hDialogBuilder.setCustomView(txtProgress)
                .title("下载")
                .nBtnText("取消")
                .nBtnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hDialogBuilder.dismiss();
                        FileApi.cancelLoading();
                    }
                })
                .show();
    }

    private boolean isUpDate(Context context, int code) {
        String versionName = "";
        int versioncode = 0;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            versioncode = pi.versionCode;
            Log.e("VersionInfo", "versionName = " + versionName);
            Log.e("VersionInfo", "versioncode = " + versioncode);
            if (versioncode < code) {
                // 更新
                return true;
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
            return false;
        }

        // 其他情况 一律 不更新
        return false;
    }
}
