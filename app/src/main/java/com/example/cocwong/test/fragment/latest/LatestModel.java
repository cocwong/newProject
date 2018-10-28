package com.example.cocwong.test.fragment.latest;

import com.example.cocwong.test.bean.LatestBean;
import com.example.cocwong.test.contract.latest.LatestContract;
import com.example.cocwong.test.http.Callback;
import com.example.cocwong.test.http.HttpManager;

public class LatestModel implements LatestContract.Model {
    @Override
    public void getLatest(int page, Callback<LatestBean> callback) {
        HttpManager.getInstance().getLatest(page, callback);
    }
}
