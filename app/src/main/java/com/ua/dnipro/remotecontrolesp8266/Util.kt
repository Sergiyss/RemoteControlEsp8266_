package com.ua.dnipro.remotecontrolesp8266

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.jesusm.holocircleseekbar.lib.HoloCircleSeekBar
import kotlinx.coroutines.*


fun isOnline(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = cm.activeNetworkInfo
    return if (netInfo != null && netInfo.isConnectedOrConnecting) {
        true
    } else false
}


inline fun <reified T : Activity> Activity.startActivityArg(name : String, value : String){
    startActivity(Intent(this, T::class.java).putExtra(name, value))
}

val out : String.() -> Unit = {
    println(this)
}

val sum = {x : Int, y  : Int -> x +y}

object Coroutines {
    fun io(work: suspend (() -> Unit)): Job =
            CoroutineScope(Dispatchers.IO).launch {
                work()
            }
    fun <T: Any> ioThenMain(work: suspend (() -> T?), callback: ((T?) -> Unit)): Job =
            CoroutineScope(Dispatchers.Main).launch {
                val data = CoroutineScope(Dispatchers.IO).async rt@ {
                    return@rt work()
                }.await()
                callback(data)
            }
}

fun HoloCircleSeekBar.mySetOnClickListener() {
    println("YESS")
}