package com.ua.dnipro.remotecontrolesp8266.mvp

import com.ua.dnipro.remotecontrolesp8266.view.BaseView

interface BaseMvpPresenter<V: BaseView> {
    var isAttached: Boolean
    fun attach(view: V)
    fun detach()
}