package com.architecture.clean.di.builder

import com.architecture.clean.ui.detail.DetailActivity
import com.architecture.clean.ui.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityProviders::class])
    abstract fun bindMainActivity(): MainActivity
    @ContributesAndroidInjector()
    abstract fun bindDetailActivity(): DetailActivity
}