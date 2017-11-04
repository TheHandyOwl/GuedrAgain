package com.tho.guedragain

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView

class ForecastActivity : AppCompatActivity() {

    companion object {
        val REQUEST_UNITS = 1
    }

    val TAG = ForecastActivity::class.java.canonicalName

    var forecast: Forecast? = null
        set(value) {
            // Accedemos a las vistas de la interfaz
            val forecastImage = findViewById<ImageView>(R.id.forecast_image)
            val maxTemp = findViewById<TextView>(R.id.max_temp)
            val minTemp = findViewById<TextView>(R.id.min_temp)
            val humidity = findViewById<TextView>(R.id.humidity)
            val forecastDescription = findViewById<TextView>(R.id.forecast_description)

            // Actualizamos la visa con el modelo
            if(value != null) {
                forecastImage.setImageResource(value.icon)
                forecastDescription.text = value.description
                val maxTempString = getString(R.string.max_temp_format, value.maxTemp)
                val minTempString = getString(R.string.min_temp_format, value.minTemp)
                val humidityString = getString(R.string.humidity_format, value.humidity)
                maxTemp.text = maxTempString
                minTemp.text = minTempString
                humidity.text = humidityString
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        forecast = Forecast(25f, 10f, 35f, "Soleado con alguna nube", R.drawable.ico_01)
    }

    // Este método define qué opciones de menú tenemos
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.settings, menu)

        // Indicamos si debe mostrar menú o no
        return true
    }

    // Este método dice que se hace una vez que se ha pulsado una opción de menú
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menu_show_settings) {
            // Aquí sabemos que se ha pulsado la opción de menú de mostrar ajustes
            val intent = SettingsActivity.intent(this)

            // Esto lo haríamos si la segunda pantalla no nos tiene que devolver nada
            //startActivity(intent)

            // Esto lo haríamos si la segunda pantalla nos tiene que devolver unos valores
            startActivityForResult(intent, REQUEST_UNITS)

            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_UNITS) {
            if (resultCode == Activity.RESULT_OK) {
                val unitSelected = data?.getIntExtra(SettingsActivity.EXTRA_UNITS, R.id.celsius_rb)
                when (unitSelected) {
                    R.id.celsius_rb -> {
                        Log.v(TAG, "Soy ForecastActivity, han pulsado OK y las unidades son Celsius")

                    }
                    R.id.fahrenheit_rb -> Log.v(TAG, "Soy ForecastActivity, han pulsado OK y las unidades son Fahrenheit")
                }
                PreferenceManager.getDefaultSharedPreferences(this)
                        .edit()
                        //.putBoolean(PREFERENCES_SHOW_CELSIUS, true)
                        .putBoolean(PREFERENCES_SHOW_CELSIUS, unitSelected == R.id.celsius_rb)
                        .apply()
            } else {
                Log.v(TAG,"Soy ForecastActivity y han pulsado CANCEL")
            }
        }

    }

}
