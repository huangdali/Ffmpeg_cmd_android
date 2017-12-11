#include "android_log.h"
#include "ffmpeg.h"
#include <jni.h>

JNIEXPORT jint JNICALL Java_com_hdl_ffmpeg_Ffmpeg_execute(JNIEnv *env, jobject type,jobjectArray commands){
      int argc = (*env)->GetArrayLength(env,commands);
      char *argv[argc];
      int i;
      for (i = 0; i < argc; i++) {
          jstring js = (jstring) (*env)->GetObjectArrayElement(env,commands, i);
          argv[i] = (char *) (*env)->GetStringUTFChars(env,js, 0);
      }
      LOGD("----------begin---------");
      return main(argc,argv);
}