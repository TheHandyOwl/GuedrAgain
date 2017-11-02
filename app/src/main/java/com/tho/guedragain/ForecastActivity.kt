package com.tho.guedragain

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class ForecastActivity : AppCompatActivity() {

    val TAG = ForecastActivity::class.java.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        val forecast = Forecast(25f, 10f, 35f, "Soleado con alguna nube", R.drawable.ico_01)
        
    }

}
