package com.trc.shift.unit.clickAnim;

import android.animation.TimeInterpolator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;


/**
 * 点击时缩小动画
 *
 * @author SamWang(199004)
 *         2016/1/15 14:26
 */
public class DefaultClickEffectScaleAnimate implements ViewClickEffect {
    /**
     * 先快后慢的动画效果,可自行替换
     * 其它效果
     */
    private TimeInterpolator interpolator = new LinearInterpolator();
    /**
     * 点击时缩小的比例
     */
    private static final float scale = 0.9f;
    private static final float alpha = 0.7f;
    /**
     * 点击动画持续时间
     */
    private static final int duration = 150;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onPressedEffect(View view) {
        view.animate()
                .scaleX(scale)
                .scaleY(scale)
                .alpha(alpha)
                .withEndAction(() -> {
                    view.animate().scaleX(1.08f).scaleY(1.08f).alpha(alpha).setInterpolator(interpolator);
                })
                .setDuration(duration)
                .setInterpolator(interpolator);
    }

    @Override
    public void onUnPressedEffect(View view) {
        view.animate().scaleX(1).scaleY(1).alpha(1).setInterpolator(interpolator);
    }
}
