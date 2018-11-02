package com.example.cocwong.test.glide;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.cocwong.test.base.Callback;

public class ImageLoader {
    public static void load(Activity activity, String url, ImageView imageView) {
        GlideApp.with(activity)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    public static void load(Fragment fragment, String url, ImageView imageView) {
        GlideApp.with(fragment)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    public static void load(Fragment fragment, String url, ImageView imageView, final Callback callback) {
        callback.callback(false);
        GlideApp.with(fragment)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        callback.callback(true);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        callback.callback(true);
                        return false;
                    }
                })
                .into(imageView);
    }
}
