package com.hdl.cmd;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hdl.elog.ELog;
import com.hdl.ffmpeg.Ffmpeg;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Ffmpeg ffmpegCmd;
    private static final String dir = Environment.getExternalStorageDirectory().getPath() + File.separator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ffmpegCmd = new Ffmpeg();
    }

    private long curTime;

    public void onStartRun(View view) {
        try {

            /**
             * 将sdcard中的test.ts文件转换成为output_ts_to_1001.mp4并输出
             */
            ffmpegCmd.transform(dir + "11/11.ts", dir + "11/output_ts_to_1105.mp4", new Ffmpeg.OnExecuteListener() {
                @Override
                public void onStart() {
                    ELog.e("开始了");
                    curTime = System.currentTimeMillis();
                }

                @Override
                public void onFinished(int result) {
                    ELog.e("耗时：" + (System.currentTimeMillis() - curTime) / 1000f + " s");
                    ELog.e("结束了" + result);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAddWaterMark(View view) {
        ffmpegCmd.addWatermark(dir + "11/output_ts_to_1105.mp4", dir + "11/t.png", dir + "11/output_watermark_004.mp4", new Ffmpeg.OnExecuteListener() {
            @Override
            public void onStart() {
                ELog.e("开始了");
            }

            @Override
            public void onFinished(int result) {
                ELog.e("结束了" + result);
            }
        });
    }
}
