package com.ua.dnipro.remotecontrolesp8266.retrofit

import com.ua.dnipro.remotecontrolesp8266.data.DataListOne
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/led")
    fun loadtListNews(@Query("state") state : String ): Call<List<DataListOne>>

}