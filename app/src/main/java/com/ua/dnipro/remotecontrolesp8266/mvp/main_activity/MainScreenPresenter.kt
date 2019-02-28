package com.ua.dnipro.remotecontrolesp8266.mvp.main_activity

import com.ua.dnipro.remotecontrolesp8266.App
import com.ua.dnipro.remotecontrolesp8266.Coroutines
import com.ua.dnipro.remotecontrolesp8266.data.DataListOne
import com.ua.dnipro.remotecontrolesp8266.mvp.BasePresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainScreenPresenter:
    BasePresenter<MainScreenContract.View>(),
    MainScreenContract.Presenter {

    private var statusLedpin7 = false

    override fun led_pin7(): String {

        statusLedpin7 = !statusLedpin7

        if(statusLedpin7){
            return "on"
        }else{
            return "off"
        }

    }

    override fun getDataFromTheServer(status: String) {
        val app = App()

        Coroutines.ioThenMain({
            app.connectServer()!!.loadtListNews(status)
        }){
            it!!.enqueue(object : Callback<List<DataListOne>> {

                override fun onFailure(call: Call<List<DataListOne>>, t: Throwable){
                    view!!.updateUI(DataListOne("0.0", "0.0", "off"))
                    view!!.generateConsoleLog(t.localizedMessage, 404)
                }

                override fun onResponse(call: Call<List<DataListOne>>, response: Response<List<DataListOne>>) {
                    view!!.updateUI(response.body()!![0])
                }

            })
        }
    }
}