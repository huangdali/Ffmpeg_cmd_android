package com.hdl.ffmpeg;

import android.text.TextUtils;

/**
 * Created by HDL on 2017/12/8.
 *
 * @author HDL
 */

public class Ffmpeg {
    static {
        System.loadLibrary("ffmpegrun");
        System.loadLibrary("avcodec-57");
        System.loadLibrary("avformat-57");
        System.loadLibrary("avutil-55");
        System.loadLibrary("swscale-4");
        System.loadLibrary("avfilter-6");
    }

    /**
     * 执行ffmpeg命令行
     *
     * @param cmd
     * @return
     */
    private native int execute(String[] cmd);

    /**
     * 视频格式转换【内部已经使用子线程，不要过多同时调用次方法】
     *
     * @param sourceFile 源文件（需要转换的文件），不能包含空格
     * @param targetFile 目标文件（转换之后的文件），不能包含空格
     * @param listener
     * @throws Exception
     */
    public void transform(String sourceFile, String targetFile, final OnExecuteListener listener) throws Exception {
        if (listener != null) {
            listener.onStart();
        }
        if (TextUtils.isEmpty(sourceFile) || TextUtils.isEmpty(targetFile)) {
            if (listener != null) {
                listener.onFinished(-1);
            }
            return;
        }
        final String cmd = ("ffmpeg -i " + sourceFile.trim() + " -acodec copy -vcodec copy -absf aac_adtstoasc " + targetFile.trim());

        new Thread() {
            @Override
            public void run() {
                int execute = execute(cmd.split(" "));
                if (listener != null) {
                    listener.onFinished(execute);
                }
            }
        }.start();

    }

    /**
     * 添加水印
     *
     * @param sourceFile    源文件（需要转换的文件），不能包含空格
     * @param targetFile    目标文件（转换之后的文件），不能包含空格
     * @param watermarkFile 水印地址，不能包含有空格
     * @param listener
     */
    public void addWatermark(String sourceFile, String watermarkFile, String targetFile, final OnExecuteListener listener) {
        if (listener != null) {
            listener.onStart();
        }
        if (TextUtils.isEmpty(sourceFile) || TextUtils.isEmpty(targetFile)) {
            if (listener != null) {
                listener.onFinished(-1);
            }
            return;
        }
//        final String cmd = ("ffmpeg -y -i " + sourceFile.trim() + " -i "+watermarkFile+" -filter_complex [0:v][1:v]overlay=main_w-overlay_w-10:main_h-overlay_h-10[out] -map [out] -map 0:a -codec:a copy " + targetFile.trim());
        final String cmd = ("ffmpeg -i " + sourceFile.trim() + " -vf \"movie="+watermarkFile.trim()+"[watermark];[in][watermark] overlay=10:10[out]\" " + targetFile.trim());

        new Thread() {
            @Override
            public void run() {
                int execute = execute(cmd.split(" "));
                if (listener != null) {
                    listener.onFinished(execute);
                }
            }
        }.start();

    }

    /**
     * 执行回调
     */
    public interface OnExecuteListener {
        void onStart();

        void onFinished(int result);
    }
}
