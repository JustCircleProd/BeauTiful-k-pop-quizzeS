package ru.justcircleprod.onlybtsfuns

import android.app.Application
import com.yandex.mobile.ads.common.MobileAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OnlyBTSFans : Application() {
    override fun onCreate() {
        super.onCreate()

        MobileAds.initialize(this) { }
    }
}