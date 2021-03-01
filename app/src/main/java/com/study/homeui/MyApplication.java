package com.study.homeui;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApplication extends Application {
    private final static String TAG = "MyApplication";

    public static Context applicationContext;
    private ExecutorService executor;
    private boolean initialized = false;
    private int loginRetry = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        init();

        //Async initialization
//        executor = Executors.newSingleThreadExecutor();
//        executor.submit(() -> init());
    }

    private void initFresco() {
        DiskCacheConfig mainConfig = DiskCacheConfig.newBuilder(applicationContext).build();
        DiskCacheConfig smallConfig = DiskCacheConfig.newBuilder(applicationContext).build();

        ImagePipelineConfig.Builder configBuilder = ImagePipelineConfig.newBuilder(applicationContext)
                .setBitmapsConfig(Bitmap.Config.ARGB_8888)
                .setDownsampleEnabled(true)
                .setMainDiskCacheConfig(mainConfig)
                .setSmallImageDiskCacheConfig(smallConfig);

        ImagePipelineConfig config = configBuilder.build();
        Fresco.initialize(this, config);
    }

    private void init() {
        initMMKV();
        initFresco();
        initialized = true;
    }

    private void initMMKV() {
//        String rootDir = MMKV.initialize(this);
//        Log.d(TAG, "MMKV root: " + rootDir);
    }
}
