package com.example.cocwong.test.contract;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<M extends BaseModel, V extends BaseView> {
    protected M mModel;
    protected V mView;
    private WeakReference<V> reference;
    /*存储disposable*/
    private List<Disposable> disposableList;

    protected abstract M attachModel();

    /**
     * 绑定操作
     *
     * @param view 视图
     */
    public void attach(V view) {
        this.mModel = attachModel();
        reference = new WeakReference<>(view);
        mView = reference.get();
        disposableList = new ArrayList<>();
    }

    /**
     * 解绑操作
     */
    public void detach() {
        clearDisposable();
        if (reference != null) {
            reference.clear();
            reference = null;
        }
        if (mView != null) {
            mView = null;
        }
    }

    /**
     * 根据需要添加Disposable，以便在解绑时进行取消回调结果操作
     *
     * @param disposable disposable
     */
    protected void addDisposable(Disposable disposable) {
        disposableList.add(disposable);
    }

    /**
     * 取消接收回调结果
     */
    private void clearDisposable() {
        for (Disposable d : disposableList) {
            if (!d.isDisposed()) {
                d.dispose();
            }
        }
    }
}
