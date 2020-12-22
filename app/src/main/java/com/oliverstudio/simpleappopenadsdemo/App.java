package com.oliverstudio.simpleappopenadsdemo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

public class App extends Application implements LifecycleObserver, Application.ActivityLifecycleCallbacks {

    private AppOpenAd mAppOpenAd;
    private boolean mIsMinimized = false;

    @Override
    public void onCreate() {
        super.onCreate();

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        registerActivityLifecycleCallbacks(this);

        initAppOpenAds2();

    }

    private AppOpenAd.AppOpenAdLoadCallback loadCallback2;

    private void initAppOpenAds2() {
        loadCallback2 = new AppOpenAd.AppOpenAdLoadCallback() {

            @Override
            public void onAppOpenAdLoaded(AppOpenAd ad) {
                mAppOpenAd = ad;
            }

            @Override
            public void onAppOpenAdFailedToLoad(LoadAdError loadAdError) {

            }
        };

        AppOpenAd.load(
                this,
                getString(R.string.app_open_ads2),
                new AdRequest.Builder().build(),
                AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
                loadCallback2);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onAppBackgrounded() {
        mIsMinimized = true;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onAppForegrounded() {

    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        if (mIsMinimized) {
            showAppOpenAd2(activity);
            mIsMinimized = false;
        }
    }

    private void showAppOpenAd2(Activity activity) {
        if (mAppOpenAd != null) {
            mAppOpenAd.show(activity, new FullScreenContentCallback() {
                @Override
                public void onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent();
                    initAppOpenAds2();
                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();

                }
            });
        }
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }

}
