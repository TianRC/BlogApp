package com.trc.shift.unit.clickAnim;

import android.view.View;

/**
 * Created by trc on 2018/5/4.
 */

public interface ViewClickEffect {
    /**
     * 按下去的效果
     */
    void onPressedEffect(View view);

    /**
     * 释放的效果     * @param view
     */
    void onUnPressedEffect(View view);
}
