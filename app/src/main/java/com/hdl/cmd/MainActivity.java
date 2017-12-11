package com.hdl.cmd;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hdl.elog.ELog;
import com.hdl.ffmpeg.Ffmpeg;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartRun(View view) {
        try {
            Ffmpeg ffmpegCmd = new Ffmpeg();
            /**
             * 将sdcard中的test.ts文件转换成为output_ts_to_1001.mp4并输出
             */
            final String dir = Environment.getExternalStorageDirectory().getPath() + File.separator;
            String cmd = ("ffmpeg -i " + dir + "test.ts -acodec copy -vcodec copy -absf aac_adtstoasc " + dir + "output_ts_to_1001.mp4");
            ELog.e(cmd);
            int execute = ffmpegCmd.execute(cmd.split(" "));
            ELog.e("执行完毕了" + execute);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
