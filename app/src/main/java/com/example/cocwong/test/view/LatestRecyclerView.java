package com.example.cocwong.test.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.AttributeSet;
import android.view.View;

import com.example.cocwong.test.base.Callback;
import com.example.cocwong.test.util.AppHelper;

public class LatestRecyclerView extends RecyclerView {
    public static int itemMargin;
    public static float itemScale = 0.5f;
    private float STAY_SCALE = 0.9f;
    private PagerSnapHelper helper;
    private Callback callback;

    public LatestRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        helper = new PagerSnapHelper();
        helper.attachToRecyclerView(this);
        setLayoutManager(new MyLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        init();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    private void init() {
        itemMargin = (int) ((AppHelper.getScreenWidth() - AppHelper.getScreenWidth() * itemScale) / 2);
    }

    /**
     * @param view
     * @return 缩放比例
     */
    private float calculateOffset(View view) {
        if (view == null) return -1;
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        boolean isVertical = layoutManager.canScrollVertically();
        int viewStart = isVertical ? view.getTop() : view.getLeft();
        int viewEnd = isVertical ? view.getBottom() : view.getRight();
        int centerX = isVertical ? getHeight() / 2 : getWidth() / 2;
        int childCenter = (viewStart + viewEnd) / 2;
        int distance = Math.abs(childCenter - centerX);

        if (distance > centerX)
            return STAY_SCALE;

        float offset = 1.f - (distance / (float) centerX);
        return (1.f - STAY_SCALE) * offset + STAY_SCALE;
    }

    class MyLayoutManager extends LinearLayoutManager {
        MyLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
            addOnScrollListener(new OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    System.out.println("state:"+newState);
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        View snapView = helper.findSnapView(MyLayoutManager.this);
                        if (snapView == null) return;
                        if (callback != null) {
                            callback.callback(getChildAdapterPosition(snapView));
                        }
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    View snapView = helper.findSnapView(MyLayoutManager.this);
                    int snapPosition = getChildAdapterPosition(snapView);
                    int leftPosition = snapPosition - 1;
                    int rightPosition = snapPosition + 1;
                    View leftView = findViewByPosition(leftPosition);
                    View rightView = findViewByPosition(rightPosition);
                    if (snapView != null) {
                        float offset = calculateOffset(snapView);
                        snapView.setScaleX(offset);
                        snapView.setScaleY(offset);
                    }
                    if (leftView != null) {
                        float offset = calculateOffset(leftView);
                        leftView.setScaleX(offset);
                        leftView.setScaleY(offset);
                    }
                    if (rightView != null) {
                        float offset = calculateOffset(rightView);
                        rightView.setScaleX(offset);
                        rightView.setScaleY(offset);
                    }
                }
            });
        }
    }
}
