package com.ua.dnipro.remotecontrolesp8266.mvp.main_activity

import com.ua.dnipro.remotecontrolesp8266.data.DataListOne
import com.ua.dnipro.remotecontrolesp8266.mvp.BaseMvpPresenter
import com.ua.dnipro.remotecontrolesp8266.view.BaseView

interface MainScreenContract {
    interface Presenter: BaseMvpPresenter<View>{
        fun led_pin7() : String
        fun getDataFromTheServer(status : String)
    }
    interface View: BaseView{
        fun updateUI(data : DataListOne)
        fun generateConsoleLog(str : String, code : Int)
    }
}