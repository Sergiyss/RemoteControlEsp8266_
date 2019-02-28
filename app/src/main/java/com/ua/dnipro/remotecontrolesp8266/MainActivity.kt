package com.ua.dnipro.remotecontrolesp8266

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.app.progresviews.ProgressWheel
import com.jesusm.holocircleseekbar.lib.HoloCircleSeekBar
import com.ua.dnipro.remotecontrolesp8266.data.DataListOne
import com.ua.dnipro.remotecontrolesp8266.mvp.main_activity.MainScreenContract
import com.ua.dnipro.remotecontrolesp8266.mvp.main_activity.MainScreenPresenter
import com.ua.dnipro.remotecontrolesp8266.view.BaseCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import dmax.dialog.SpotsDialog
import java.util.*
import javax.inject.Inject


class MainActivity : BaseCompatActivity(), MainScreenContract.View {

    @Inject
    lateinit var presenter: MainScreenPresenter

    private var saveLog : String = ""

    private lateinit var temperature : ProgressWheel
    private lateinit var humidity : ProgressWheel
    private lateinit var log : TextView
    private lateinit var picker : HoloCircleSeekBar

    lateinit var dialog : SpotsDialog


    override fun init(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)

        App.get().injector.inject(this)

        presenter.attach(this)

        temperature = findViewById(R.id.t_progress)
        humidity = findViewById(R.id.h_progress)
        log = findViewById(R.id.log_esp8266)
        picker = findViewById(R.id.picker)

        dialog = SpotsDialog(this@MainActivity)


        picker.setOnSeekBarChangeListener(object : HoloCircleSeekBar.OnCircleSeekBarChangeListener{
            override fun onStartTrackingTouch(p0: HoloCircleSeekBar?) {}

            override fun onStopTrackingTouch(p0: HoloCircleSeekBar?) {}

            override fun onProgressChanged(p0: HoloCircleSeekBar?, p1: Int, p2: Boolean) {
                generateConsoleLog("Servo $p1", 200)
            }

        })

        downloadDatas() //Чтобы обновить UI при первом запуски.

        //Переключатель свитодиотов
        pin_d7.setOnClickListener {
            downloadDatas(presenter.led_pin7())
        }

        update_dht22.setOnClickListener {
            downloadDatas()
        }
    }


    private fun downloadDatas(state : String = "off"){
        dialog.show()
        presenter.getDataFromTheServer(state)
    } //Загрузить базу данных.

    override fun updateUI(data : DataListOne) {
        dialog.dismiss()


        temperature.setStepCountText(data.t)
        temperature.setPercentage(match(data.t, 50))

        humidity.setStepCountText(data.h)
        humidity.setPercentage(match(data.h, 100))

        generateConsoleLog("led - ${data.state_pin_d7}", 200)

    }

    private fun match(caunt : String, maxInt : Int) : Int{
        val operationOne = caunt.toFloat() * 100;
        val operationTwo = operationOne / maxInt

        return (360 * (operationTwo / 100)).toInt()
    }


    override fun generateConsoleLog(str: String, code: Int) {
        saveLog += "\"${getTime().format(Date())} -> ${if (code == 200) "OK :" else "ERROR :"} $str\""
        saveLog += "\n"
        log.text = saveLog
    }

    private fun getTime() = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
}

