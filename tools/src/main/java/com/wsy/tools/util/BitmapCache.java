/*
 * Copyright (c) 2017-present 3000.com All Rights Reserved.
 */
package com.wsy.tools.util;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 图片缓存。
 *
 * @author wsy
 */
public final class BitmapCache {
    private LruCache<String, Bitmap> mLruCache;
    private static final int MAX_LRU_CACHE_SIZE = (int) (Runtime.getRuntime().maxMemory() / 8);

    public BitmapCache() {
        //初始化LruCache
        initLruCache(MAX_LRU_CACHE_SIZE);
    }

    public BitmapCache(int size) {
        //初始化LruCache
        initLruCache(size);
    }

    private void initLruCache(int size) {
        mLruCache = new LruCache<String, Bitmap>(size) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }
    public Bitmap get(String url) {
        return mLruCache.get(url);
    }

    public void remove(String url){
        mLruCache.remove(url);
    }

    public void clear(){
        mLruCache.evictAll();
    }

    public void put(String url, Bitmap bitmap) {
        if(!EmptyUtil.isEmpty(url) && bitmap != null){
            mLruCache.put(url, bitmap);
        }
    }
}
