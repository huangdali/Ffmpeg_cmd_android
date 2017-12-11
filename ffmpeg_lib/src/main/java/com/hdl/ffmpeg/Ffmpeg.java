package com.hdl.ffmpeg;

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
    }

    /**
     * 执行ffmpeg命令行
     *
     * @param cmd
     * @return
     */
    public native int execute(String[] cmd);
}
