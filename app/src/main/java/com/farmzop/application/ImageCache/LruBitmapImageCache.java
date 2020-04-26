package com.farmzop.application.ImageCache;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Gaurav on 11-03-2016.
 */
public class LruBitmapImageCache extends LruCache<String, Bitmap> implements
        ImageLoader.ImageCache {
    public static int getDefaultLruCacheSize() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;

        return cacheSize;
    }

    public LruBitmapImageCache() {
        this(getDefaultLruCacheSize());
    }

    public LruBitmapImageCache(int sizeInKiloBytes) {
        super(sizeInKiloBytes);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight() / 1024;
    }

    @Override
    public Bitmap getBitmap(String tag) {
        return get(tag);
    }

    @Override
    public void putBitmap(String tag, Bitmap bitmap) {
        put(tag, bitmap);
    }
}
