package com.example.cocwong.test.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cocwong.test.contract.BasePresenter;
import com.example.cocwong.test.contract.BaseView;

import java.lang.reflect.ParameterizedType;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {
    protected T presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setContentViewId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initMvp();
        init();
    }

    @SuppressWarnings("unchecked")
    private void initMvp() {
        presenter = setPresenter();
        if (presenter != null) {
            presenter.attach(this);
        }
    }

    protected T setPresenter() {
        return null;
    }

    /**
     * @return layoutId
     */
    protected abstract int setContentViewId();

    /**
     * 初始化View
     */
    protected void findView() {
    }

    /**
     * 初始化
     */
    protected void init() {
    }

    /**
     * findViewById
     *
     * @param id  viewId
     * @param <V> view extends View
     * @return View
     */
    @SuppressWarnings("unchecked")
    protected <V extends View> V findViewById(int id) {
        View view = getView();
        if (view == null) return null;
        View viewById = view.findViewById(id);
        return (V) viewById;
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        if (presenter != null) {
            presenter.detach();
        }
        super.onDestroy();
    }
}
