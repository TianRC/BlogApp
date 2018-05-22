package com.trc.shift.unit;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import com.trc.shift.R;

/**
 * Created by trc on 2018/5/4.
 */

public class SoundPoolUtil {
    private static SoundPoolUtil soundPoolUtil;
    private SoundPool soundPool;

    //单例模式
    public static SoundPoolUtil getInstance(Context context) {
        if (soundPoolUtil == null)
            soundPoolUtil = new SoundPoolUtil(context);
        return soundPoolUtil;
    }

    private SoundPoolUtil(Context context) {
        soundPool = new SoundPool(3, AudioManager.STREAM_SYSTEM, 0);
        //加载音频文件
        soundPool.load(context, R.raw.voice1, 1);
        soundPool.load(context, R.raw.voice1, 1);
        soundPool.load(context, R.raw.voice1, 1);


    }

    public void play(int number) {
        Log.d("tag", "number " + number);
        //播放音频
        soundPool.play(number, 1, 1, 0, 0, 1);
    }

    /**
     * 定义枚举来限定按钮音乐类型
     */
    public enum MusicType {
        FIRST, SECOND, THIRD
    }
}
