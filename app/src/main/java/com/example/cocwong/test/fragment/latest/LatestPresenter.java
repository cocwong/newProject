package com.example.cocwong.test.fragment.latest;

import com.example.cocwong.test.bean.LatestBean;
import com.example.cocwong.test.contract.latest.LatestContract;
import com.example.cocwong.test.http.Callback;

import io.reactivex.disposables.Disposable;

public class LatestPresenter extends LatestContract.Presenter {
    @Override
    protected void getLatest(int page) {
        mModel.getLatest(page, new Callback<LatestBean>() {
            @Override
            public void onStart(Disposable disposable) {
                addDisposable(disposable);
            }

            @Override
            public void onSuccess(LatestBean bean) {
                mView.updateAdapter(bean);
            }

            @Override
            public void onFailed(String message) {
                mView.showMessage(message);
            }
        });
    }

    @Override
    protected LatestModel attachModel() {
        return new LatestModel();
    }
}
