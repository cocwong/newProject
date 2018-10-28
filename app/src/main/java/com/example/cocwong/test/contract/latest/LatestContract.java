package com.example.cocwong.test.contract.latest;

import com.example.cocwong.test.bean.LatestBean;
import com.example.cocwong.test.contract.BaseModel;
import com.example.cocwong.test.contract.BasePresenter;
import com.example.cocwong.test.contract.BaseView;
import com.example.cocwong.test.fragment.latest.LatestFragment;
import com.example.cocwong.test.fragment.latest.LatestModel;
import com.example.cocwong.test.http.Callback;

public interface LatestContract {
    interface Model extends BaseModel {
        void getLatest(int page, Callback<LatestBean> callback);
    }

    interface View extends BaseView {
        void updateAdapter(LatestBean bean);
    }

    abstract class Presenter extends BasePresenter<LatestModel, LatestFragment> {
        protected abstract void getLatest(int page);
    }
}
