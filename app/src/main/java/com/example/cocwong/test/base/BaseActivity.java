package com.example.cocwong.test.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentViewId());
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
}
