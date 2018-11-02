package com.example.cocwong.test.glide;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

@GlideModule
public class MyGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);
        long memorySize = Runtime.getRuntime().maxMemory() / 10;
        int diskSize = 1024 * 1024 * 100;
        builder.setMemoryCache(new LruResourceCache(memorySize));
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, diskSize));
    }
}