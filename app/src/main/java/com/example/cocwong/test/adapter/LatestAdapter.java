package com.example.cocwong.test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cocwong.test.R;
import com.example.cocwong.test.bean.LatestBean;
import com.example.cocwong.test.glide.GlideRequests;

import java.util.ArrayList;
import java.util.List;

public class LatestAdapter extends RecyclerView.Adapter<LatestAdapter.ViewHolder> {
    private List<LatestBean.ListBean> latestBeans = new ArrayList<>();
    private Context context;
    private GlideRequests requests;

    public LatestAdapter(GlideRequests requests) {
        this.requests = requests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_latest, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        requests.load(latestBeans.get(i).getImage()).into(viewHolder.img);
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.latest_img);
        }
    }
}
