## Ffmpeg_cmd_android

android端ffmpeg命令行工具
```java
    Ffmpeg ffmpegCmd = new Ffmpeg();
    /**
     * 将sdcard中的test.ts文件转换成为output_ts_to_1001.mp4并输出
     */
    final String dir = Environment.getExternalStorageDirectory().getPath() + File.separator;
    String cmd = ("ffmpeg -i " + dir + "test.ts -acodec copy -vcodec copy -absf aac_adtstoasc " + dir + "output_ts_to_1001.mp4");
    ELog.e(cmd);
    int execute = ffmpegCmd.execute(cmd.split(" "));
    ELog.e("执行完毕了" + execute);
```