package com.ua.dnipro.remotecontrolesp8266

import android.app.Application
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.ua.dnipro.remotecontrolesp8266.di.AppDiComponent
import com.ua.dnipro.remotecontrolesp8266.di.DaggerAppDiComponent
import com.ua.dnipro.remotecontrolesp8266.di.MainScreenModule
import com.ua.dnipro.remotecontrolesp8266.retrofit.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class App : Application() {
    private val BASE_URL = "http://192.168.4.1"

    lateinit var injector: AppDiComponent
        private set

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this
        injector = DaggerAppDiComponent.builder()
            .mainScreenModule(MainScreenModule())
            .build()
    }

    companion object {
        private lateinit var INSTANCE: App
        @JvmStatic
         fun get(): App = INSTANCE
    }

    @Throws(IOException::class)
    fun connectServer() : Api? {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        return retrofit.create(Api::class.java)

    }

}