package com.ua.dnipro.remotecontrolesp8266.di

import com.ua.dnipro.remotecontrolesp8266.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        MainScreenModule::class
    ]
)
@Singleton
interface AppDiComponent {
    fun inject(mainScreenActivity: MainActivity)
}