package com.example.cocwong.test.fragment.latest;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cocwong.test.R;
import com.example.cocwong.test.adapter.LatestAdapter;
import com.example.cocwong.test.base.BaseFragment;
import com.example.cocwong.test.base.Callback;
import com.example.cocwong.test.bean.LatestBean;
import com.example.cocwong.test.contract.latest.LatestContract;
import com.example.cocwong.test.util.AppHelper;
import com.example.cocwong.test.util.DensityHelper;
import com.example.cocwong.test.view.LatestRecyclerView;
import com.example.cocwong.test.view.TitleBar;

public class LatestFragment extends BaseFragment<LatestPresenter> implements LatestContract.View, TitleBar.OnTitleClickListener, Callback {
    private TitleBar titleBar;
    private LatestRecyclerView recycler;
    private TextView tvTag, tvTitle, tvContent;
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
        tvTag = findViewById(R.id.latest_tag);
        tvTitle = findViewById(R.id.latest_desc_title);
        tvContent = findViewById(R.id.latest_desc_content);
    }

    @Override
    protected LatestPresenter setPresenter() {
        return new LatestPresenter();
    }

    @Override
    protected void init() {
        super.init();
        adapter = new LatestAdapter(this);
        recycler.setAdapter(adapter);
        recycler.setCallback(this);
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
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) tvTag.getLayoutParams();
        params.topMargin = titleBar.getHeight() + (int) DensityHelper.dp2Px(getContext(), 10) + (int) (AppHelper.getScreenHeight() * LatestRecyclerView.itemScale) - tvTag.getHeight() / 2;
        params.leftMargin = AppHelper.getScreenWidth() / 2 + (int) (AppHelper.getScreenWidth() * LatestRecyclerView.itemScale) / 2 - tvTag.getWidth() / 2;
    }

    @Override
    public void showNext(LatestBean.ListBean bean) {
        tvTag.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.VISIBLE);
        tvContent.setVisibility(View.VISIBLE);
        tvTag.setText(bean.getType());
        tvTitle.setText(bean.getTitle());
        tvContent.setText(bean.getSummary());
    }

    @Override
    public void callback(Object obj) {
        int position = (int) obj;
        System.out.println("position:" + position);
        showNext(adapter.getItemByPosition(position));
    }
}
