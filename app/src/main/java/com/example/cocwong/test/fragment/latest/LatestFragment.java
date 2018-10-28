package com.example.cocwong.test.fragment.latest;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import com.example.cocwong.test.R;
import com.example.cocwong.test.adapter.LatestAdapter;
import com.example.cocwong.test.base.BaseFragment;
import com.example.cocwong.test.bean.LatestBean;
import com.example.cocwong.test.contract.latest.LatestContract;
import com.example.cocwong.test.glide.GlideApp;
import com.example.cocwong.test.view.TitleBar;

public class LatestFragment extends BaseFragment<LatestPresenter> implements LatestContract.View, TitleBar.OnTitleClickListener {
    private TitleBar titleBar;
    private RecyclerView recycler;
    private LatestAdapter adapter;

    @Override
    protected int setContentViewId() {
        return R.layout.fragment_latest;
    }

    @Override
    protected void findView() {
        super.findView();
        titleBar = findViewById(R.id.title_latest);
        titleBar.setOnTitleClickListener(this);
        recycler = findViewById(R.id.recycler_latest);
    }

    @Override
    protected LatestPresenter setPresenter() {
        return new LatestPresenter();
    }

    @Override
    protected void init() {
        super.init();
        SnapHelper helper = new PagerSnapHelper();
        helper.attachToRecyclerView(recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new LatestAdapter(GlideApp.with(this));
        recycler.setAdapter(adapter);
        presenter.getLatest(0);
    }

    @Override
    public void onLeftClick() {
        presenter.getLatest(0);
    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void updateAdapter(LatestBean bean) {
        adapter.updateData(bean.getList());
    }
}
