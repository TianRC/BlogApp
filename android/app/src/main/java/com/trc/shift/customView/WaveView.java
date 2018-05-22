package com.trc.shift.customView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by trc on 2018/5/2.
 */

public class WaveView extends View {
    private int  width = 0;
    private int height = 0;
    private int baseLine = 90;// 基线，用于控制水位上涨的，这里是写死了没动，你可以不断的设置改变。
    private Paint firstPaint,secondPaint;
    private int waveHeight = 100;// 波浪的最高度
    private int waveWidth  ;//波长
    private float offset =0f;//偏移量
    private float offset2 =200f;//偏移量
    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    /**
     * 不断的更新偏移量，并且循环。
     */
    private void updateXControl(){
        //设置一个波长的偏移
        ValueAnimator mAnimator = ValueAnimator.ofFloat(0,waveWidth);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(animation -> {
            float animatorValue = (float)animation.getAnimatedValue() ;
            offset = animatorValue;//不断的设置偏移量，并重画
//            postInvalidate();
        });
        mAnimator.setDuration(3681);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.start();

        ValueAnimator mAnimator1 = ValueAnimator.ofFloat(0,waveWidth);
        mAnimator1.setInterpolator(new LinearInterpolator());
        mAnimator1.addUpdateListener(animation -> {
            float animatorValue = (float)animation.getAnimatedValue() ;
            offset2 = animatorValue+200f;//不断的设置偏移量，并重画
            postInvalidate();
        });
        mAnimator1.setDuration(2379);
        mAnimator1.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator1.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(getPath(offset), firstPaint);
        canvas.drawPath(getPath(offset2), secondPaint);
    }
    //初始化paint，没什么可说的。
    private void initView(){
        firstPaint = new Paint();
        firstPaint.setColor(0x90ab47bc);
        firstPaint.setStyle(Paint.Style.FILL);
        secondPaint = new Paint();
        secondPaint.setColor(0x90ab47bc);
        secondPaint.setStyle(Paint.Style.FILL);


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getMeasuredWidth();//获取屏幕宽度
        height = getMeasuredHeight();//获取屏幕高度
        waveWidth = width;
        baseLine = height/2;
        updateXControl();
    }

    /**
     * 核心代码，计算path
     * @return
     */


    private Path getPath(float offset){
        int itemWidth = waveWidth/2;//半个波长
        Path mPath = new Path();
        mPath.moveTo(-itemWidth * 3, baseLine);//起始坐标
        //核心的代码就是这里
        for (int i = -3; i < 2; i++) {
            int startX = i * itemWidth;
            mPath.quadTo(
                    startX + itemWidth/2 + offset,//控制点的X,（起始点X + itemWidth/2 + offset)
                    getWaveHeigh( i ),//控制点的Y
                    startX + itemWidth + offset,//结束点的X
                    baseLine//结束点的Y
            );//只需要处理完半个波长，剩下的有for循环自已就添加了。
        }
        //下面这三句话很重要，它是形成了一封闭区间，让曲线以下的面积填充一种颜色，大家可以把这3句话注释了看看效果。
        mPath.lineTo(width,height);
        mPath.lineTo(0,height);
        mPath.close();
        return  mPath;
    }
    //奇数峰值是正的，偶数峰值是负数
    private int getWaveHeigh(int num){
        if(num % 2 == 0){
            return baseLine + waveHeight;
        }
        return baseLine - waveHeight;
    }

}