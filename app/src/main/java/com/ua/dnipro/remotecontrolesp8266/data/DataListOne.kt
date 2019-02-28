package com.ua.dnipro.remotecontrolesp8266.data

import com.google.gson.annotations.SerializedName

data class DataListOne(@SerializedName("temperature") var t: String,
                       @SerializedName("humidity") var h: String,
                       @SerializedName("pin_d7") var state_pin_d7: String) : java.io.Serializable