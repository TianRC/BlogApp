package com.trc.shift.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.trc.shift.R;

/**
 * Created by trc on 2018/5/4.
 */

public class Bar extends LinearLayout {

    private ImageView back;

    public Bar(Context context) {
        super(context);
//        init(context);
    }

    public Bar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleBar);
        if (attributes != null) {
            boolean leftButtonVisible = attributes.getBoolean(R.styleable.CustomTitleBar_showLeft, true);
            if (!leftButtonVisible) back.setVisibility(INVISIBLE);
        }
    }

    public Bar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        init(context);
    }


    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.custom_title_bar, this, true);
        back = findViewById(R.id.bar_back);
    }

}
