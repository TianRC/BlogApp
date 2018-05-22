package com.trc.shift.unit.clickAnim;

import android.view.View;

import com.trc.shift.unit.OnClickEffectTouchListener;

/**
 * Created by trc on 2018/5/4.
 */

public class OnClickAnimUtil {
    public static void clickAnim(View view) {
        OnClickEffectTouchListener onClickEffectTouchListener = new OnClickEffectTouchListener();
        onClickEffectTouchListener.setViewClickEffect(new DefaultClickEffectScaleAnimate());
        view.setOnTouchListener(onClickEffectTouchListener);
    }
}
