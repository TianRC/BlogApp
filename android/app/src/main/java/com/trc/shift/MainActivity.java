package com.trc.shift;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.trc.shift.activity.home.ui.HomeActivity;
import com.trc.shift.activity.user.ui.LoginActivity;
import com.trc.shift.config.Constant;
import com.trc.shift.unit.SPUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(() -> {
            String token = (String) SPUtil.get(this, Constant.TOKEN, "");
            if (TextUtils.isEmpty(token)) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            } else {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
            finish();
        }, 2000);

    }
}
