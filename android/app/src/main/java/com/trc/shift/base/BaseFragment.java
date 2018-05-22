package com.trc.shift.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trc.shift.R;

/**
 * Created by trc on 2018/5/3.
 */

public abstract class BaseFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayout(), null);
        initView();
        initData();
        return view;
    }

    public abstract int getLayout();

    public abstract void initView();

    public abstract void initData();

    public View getRootView() {
        return view;
    }
}
