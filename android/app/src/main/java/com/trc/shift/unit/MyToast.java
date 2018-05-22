package com.trc.shift.unit;

import android.view.ViewGroup;

import com.trycatch.mysnackbar.Prompt;
import com.trycatch.mysnackbar.TSnackbar;

/**
 * Created by trc on 2018/5/2.
 */

public class MyToast {
    public static void makeError(ViewGroup viewGroup,String errorText) {
        TSnackbar.make(viewGroup,
                errorText,
                TSnackbar.LENGTH_SHORT,
                TSnackbar.APPEAR_FROM_TOP_TO_DOWN).setPromptThemBackground(Prompt.ERROR).show();

    }
}
