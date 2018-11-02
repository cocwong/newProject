package com.example.cocwong.test.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.cocwong.test.R;
import com.example.cocwong.test.base.Callback;
import com.example.cocwong.test.bean.LatestBean;
import com.example.cocwong.test.glide.ImageLoader;
import com.example.cocwong.test.util.AppHelper;
import com.example.cocwong.test.view.LatestRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LatestAdapter extends RecyclerView.Adapter<LatestAdapter.ViewHolder> {
    private List<LatestBean.ListBean> latestBeans = new ArrayList<>();
    private Fragment context;

    public LatestAdapter(Fragment context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context.getContext()).inflate(R.layout.item_latest, viewGroup, false);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        params.width = (int) (AppHelper.getScreenWidth() * LatestRecyclerView.itemScale);
        params.height = (int) (AppHelper.getScreenHeight() * LatestRecyclerView.itemScale);
        params.rightMargin = LatestRecyclerView.itemMargin / 4;
        params.leftMargin = LatestRecyclerView.itemMargin / 4;
        params.topMargin = LatestRecyclerView.itemMargin / 8;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) viewHolder.itemView.getLayoutParams();
        params.leftMargin = i == 0 ? LatestRecyclerView.itemMargin : LatestRecyclerView.itemMargin / 4;
        params.rightMargin = i == latestBeans.size() - 1 ? LatestRecyclerView.itemMargin : LatestRecyclerView.itemMargin / 4;
        ImageLoader.load(context, latestBeans.get(i).getImage(), viewHolder.img, new Callback() {
            @Override
            public void callback(Object obj) {
                boolean isLoaded = (boolean) obj;
                viewHolder.progressBar.setVisibility(isLoaded ? View.GONE : View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return latestBeans.size();
    }

    public void updateData(List<LatestBean.ListBean> latestBeans) {
        this.latestBeans = latestBeans;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        ProgressBar progressBar;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.latest_img);
            progressBar = itemView.findViewById(R.id.latest_progress);
        }
    }

    public LatestBean.ListBean getItemByPosition(int position) {
        return latestBeans.get(position);
    }
}
