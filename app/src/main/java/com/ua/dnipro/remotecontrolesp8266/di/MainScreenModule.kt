package com.ua.dnipro.remotecontrolesp8266.di

import com.ua.dnipro.remotecontrolesp8266.mvp.main_activity.MainScreenPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainScreenModule {

    @Provides
    @Singleton
    fun providesPresenter(): MainScreenPresenter = MainScreenPresenter()
}