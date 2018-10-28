package com.example.cocwong.test.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.widget.RadioGroup;

import com.example.cocwong.test.R;
import com.example.cocwong.test.base.BaseActivity;
import com.example.cocwong.test.fragment.FindFragment;
import com.example.cocwong.test.fragment.ImageFragment;
import com.example.cocwong.test.fragment.latest.LatestFragment;
import com.example.cocwong.test.fragment.MineFragment;
import com.example.cocwong.test.fragment.RaiseFragment;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    private FragmentManager fragmentManager;
    private String lastFragmentName;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findView() {
        radioGroup = findViewById(R.id.main_tab_group);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    protected void init() {
        super.init();
        fragmentManager = getSupportFragmentManager();
        radioGroup.check(R.id.main_tab_btn_latest);
    }

    /**
     * 切换fragment
     *
     * @param clazz class for fragment
     */
    private void switchFragment(Class<? extends Fragment> clazz) {
        String fragmentName = clazz.getSimpleName();
        if (TextUtils.equals(fragmentName, lastFragmentName)) return;
        Fragment fragment = fragmentManager.findFragmentByTag(fragmentName);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragment == null) {
            try {
                fragment = clazz.newInstance();
                transaction.add(R.id.main_container, fragment, fragmentName);
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        } else {
            transaction.show(fragment);
        }
        if (lastFragmentName != null) {
            Fragment lastFragment = fragmentManager.findFragmentByTag(lastFragmentName);
            if (lastFragment != null) {
                transaction.hide(lastFragment);
            }
        }
        transaction.commit();
        lastFragmentName = fragmentName;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.main_tab_btn_latest://最新
                switchFragment(LatestFragment.class);
                break;
            case R.id.main_tab_btn_image://图说
                switchFragment(ImageFragment.class);
                break;
            case R.id.main_tab_btn_raise://养鹅
                switchFragment(RaiseFragment.class);
                break;
            case R.id.main_tab_btn_find://发现
                switchFragment(FindFragment.class);
                break;
            case R.id.main_tab_btn_mine://我的
                switchFragment(MineFragment.class);
                break;
        }
    }
}
