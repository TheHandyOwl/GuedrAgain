package com.tho.guedragain

import android.app.Activity
import android.content.Intent
import android.media.audiofx.Equalizer
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class ForecastActivity : AppCompatActivity() {

    val TAG = ForecastActivity::class.java.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_UNITS) {
            if (resultCode == Activity.RESULT_OK) {
                val unitSelected = data?.getIntExtra(SettingsActivity.EXTRA_UNITS, R.id.celsius_rb)
                when (unitSelected) {
                    R.id.celsius_rb -> {
                        Log.v(TAG, "Soy ForecastActivity, han pulsado OK y las unidades son Celsius")
                        //Toast.makeText(this,"Celsius seleccionado", Toast.LENGTH_LONG).show()
                    }
                    R.id.fahrenheit_rb -> {
                        Log.v(TAG, "Soy ForecastActivity, han pulsado OK y las unidades son Fahrenheit")
                        //Toast.makeText(this,"Fahrenheit seleccionado", Toast.LENGTH_LONG).show()
                    }
                }

                val oldShowCelsius = temperatureUnits()

                PreferenceManager.getDefaultSharedPreferences(this)
                        .edit()
                        //.putBoolean(PREFERENCES_SHOW_CELSIUS, true)
                        .putBoolean(PREFERENCES_SHOW_CELSIUS, unitSelected == R.id.celsius_rb)
                        .apply()
                updateTemperature()

                Snackbar.make(
                        findViewById<View>(android.R.id.content),
                        "Han cambiado las preferencias",
                        Snackbar.LENGTH_LONG)
                        .setAction("Deshacer", {
                            PreferenceManager.getDefaultSharedPreferences(this)
                                    .edit()
                                    //.putBoolean(PREFERENCES_SHOW_CELSIUS, true)
                                    .putBoolean(PREFERENCES_SHOW_CELSIUS, oldShowCelsius == Forecast.TempUnit.CELSIUS)
                                    .apply()
                            updateTemperature()
                        })
                        .show()
            } else {
                Log.v(TAG,"Soy ForecastActivity y han pulsado CANCEL")
            }
        }

    }

}
