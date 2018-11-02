package com.example.cocwong.test.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

/**
 * activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    private ExitBroadcastReceiver exitReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentViewId());
        registerReceiver(exitReceiver = new ExitBroadcastReceiver(), new IntentFilter(Constant.ACTION_ACTIVITY_FINISH));
        findView();
        init();
    }

    /**
     * @return layoutId
     */
    protected abstract int setContentViewId();

    /**
     * 初始化View操作放这里
     */
    protected void findView() {
    }

    /**
     * 初始化操作放这里
     */
    protected void init() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(exitReceiver);
    }

    class ExitBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (TextUtils.equals(action, Constant.ACTION_ACTIVITY_FINISH)) {
                System.out.println(getComponentName()+":finish");
                if (!isDestroyed() && !isFinishing()) {
                    finish();
                }
            }
        }
    }
}
