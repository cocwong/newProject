package com.example.cocwong.test.contract;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<M extends BaseModel, V extends BaseView> {
    protected M mModel;
    protected V mView;
    private WeakReference<V> reference;
    private List<Disposable> disposableList;

    protected abstract M attachModel();

    public void attach(V view) {
        this.mModel = attachModel();
        reference = new WeakReference<>(view);
        mView = reference.get();
        disposableList = new ArrayList<>();
    }

    protected void detach() {
        if (reference != null) {
            reference.clear();
            reference = null;
        }
        clearDisposable();
    }

    protected void addDisposable(Disposable disposable) {
        disposableList.add(disposable);
    }

    private void clearDisposable() {
        for (Disposable d : disposableList) {
            if (!d.isDisposed()) {
                d.dispose();
            }
        }
    }
}
