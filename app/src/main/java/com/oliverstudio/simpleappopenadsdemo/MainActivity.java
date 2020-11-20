package com.oliverstudio.simpleappopenadsdemo;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.appopen.AppOpenAd;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAd();
    }

    private void initAd() {
        MobileAds.initialize(this, initializationStatus -> {
        });
        //uncomment the code to add test devices
//        List<String> testDeviceIds = Arrays.asList("put your device ids here");
//        RequestConfiguration configuration = new RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build();
//        MobileAds.setRequestConfiguration(configuration);

        initAppOpenAds();

    }

    //!!!Be ware, keep loadCallback object as FIELD, don't local variable.
    // It's because under the hood the loadCallback is wrapped in a WeakReference.
    // The reference must be strong, otherwise, after launching a Garbage Collector, the object will be removed.
    private AppOpenAd.AppOpenAdLoadCallback loadCallback;

    private void initAppOpenAds() {

        loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {

            @Override
            public void onAppOpenAdLoaded(AppOpenAd ad) {
                Log.d("ptg", "onAppOpenAdLoaded: ");
                ad.show(MainActivity.this, new FullScreenContentCallback(){
                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();

                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();

                    }
                });

            }

            @Override
            public void onAppOpenAdFailedToLoad(LoadAdError loadAdError) {
                Log.d("ptg", "onAppOpenAdFailedToLoad: ");
            }
        };

        AppOpenAd.load(
                this,
                getString(R.string.app_open_ads1),
                new AdRequest.Builder().build(),
                AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
                loadCallback);

    }
}