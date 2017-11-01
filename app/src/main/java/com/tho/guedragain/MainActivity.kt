package com.tho.guedragain

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val TAG = MainActivity::class.java.canonicalName
    var offlineWeatherImage : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.stone_button).setOnClickListener(this)
        findViewById<Button>(R.id.donkey_button).setOnClickListener(this)

        offlineWeatherImage = findViewById<ImageView>(R.id.offline_weather_image)

        Log.v(TAG, "Pasando por onCreate")

        if (savedInstanceState != null) {
            Log.v(TAG, "savedInstanceState no es null y clave vale: ${savedInstanceState.getString("clave")}")
        } else {
            Log.v(TAG, "savedInstanceState ES null")
        }

    }

    override fun onClick(v: View?) {
        Log.v(TAG, "Pasando por onClick")

        // Solución final sin atributos. Pasamos la vista al crear el listener
        Log.v(TAG, when (v?.id) {
            R.id.stone_button -> "Se ha pulsado stoneButton"
            R.id.donkey_button -> "Se ha pulsado donkeyButton"
            else -> "Se ha pulsado algún botón"
        })

        // Solución 4: preparabamos el when
        when (v?.id) {
            R.id.stone_button -> offlineWeatherImage?.setImageResource(R.drawable.offline_weather)
            R.id.donkey_button -> offlineWeatherImage?.setImageResource(R.drawable.offline_weather2)
        }

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.v(TAG, "Pasando por onSaveInstanceState")
        outState?.putString("clave","valor")
    }



}
