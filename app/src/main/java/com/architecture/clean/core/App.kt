package com.architecture.clean.core


import android.content.Context
import android.support.multidex.MultiDex
import com.architecture.clean.R
import com.architecture.clean.di.component.DaggerCoreComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerCoreComponent
                .builder()
                .application(this)
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Robboto.TTF")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}