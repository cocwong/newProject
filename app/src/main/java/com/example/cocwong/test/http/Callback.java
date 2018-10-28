package com.example.cocwong.test.http;

import io.reactivex.disposables.Disposable;

public interface Callback<T> {
    void onStart(Disposable disposable);

    void onSuccess(T t);

    void onFailed(String message);
}
