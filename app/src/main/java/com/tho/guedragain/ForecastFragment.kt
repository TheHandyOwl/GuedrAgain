package com.tho.guedragain

import android.app.Fragment
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.*
import android.widget.ImageView
import android.widget.TextView

class ForecastFragment: Fragment()  {

    companion object {
        val REQUEST_UNITS = 1
    }

    lateinit var root: View
    lateinit var maxTemp: TextView
    lateinit var minTemp: TextView

    var forecast: Forecast? = null
        set(value) {
            field = value
            // Accedemos a las vistas de la interfaz
            val forecastImage = root.findViewById<ImageView>(R.id.forecast_image)
            maxTemp = root.findViewById(R.id.max_temp)
            minTemp = root.findViewById(R.id.min_temp)
            val humidity = root.findViewById<TextView>(R.id.humidity)
            val forecastDescription = root.findViewById<TextView>(R.id.forecast_description)

            // Actualizamos la visa con el modelo
            value?.let {
                forecastImage.setImageResource(value.icon)
                forecastDescription.text = value.description
                updateTemperature()
                val humidityString = getString(R.string.humidity_format, value.humidity)
                humidity.text = humidityString
            }
        }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        inflater?.let {
            forecast = Forecast(25f, 10f, 35f, "Soleado con alguna nube", R.drawable.ico_01)
            // it se refiere a la variable, que no puede ser nombrada dentro del let
            root = it.inflate(R.layout.fragment_forecast, container, false)
        }

        return root
    }

    // Este método define qué opciones de menú tenemos
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
            inflater?.inflate(R.menu.settings, menu)
    }

    // Este método dice que se hace una vez que se ha pulsado una opción de menú
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menu_show_settings) {
            // Aquí sabemos que se ha pulsado la opción de menú de mostrar ajustes
            //val intent = SettingsActivity.intent(this)
            val units = if (temperatureUnits() == Forecast.TempUnit.CELSIUS) {
                R.id.celsius_rb
            } else {
                R.id.fahrenheit_rb
            }
            val intent = SettingsActivity.intent(activity, units)

            // Esto lo haríamos si la segunda pantalla no nos tiene que devolver nada
            //startActivity(intent)

            // Esto lo haríamos si la segunda pantalla nos tiene que devolver unos valores
            startActivityForResult(intent, REQUEST_UNITS)

            return true
        }
        return super.onOptionsItemSelected(item)
    }


    private fun updateTemperature() {
        var units = temperatureUnits()
        var unitsString = temperatureUnitsString(units)
        var maxTempString = getString(R.string.max_temp_format, forecast?.getMaxTemp(units), unitsString)
        var minTempString = getString(R.string.min_temp_format, forecast?.getMinTemp(units), unitsString)
        maxTemp.text = maxTempString
        minTemp.text = minTempString
    }

    private fun temperatureUnitsString(units: Forecast.TempUnit) = when (units) {
        Forecast.TempUnit.CELSIUS -> "ºC"
        else -> "F"
    }

    private fun temperatureUnits() = if (PreferenceManager
            .getDefaultSharedPreferences(activity)
            .getBoolean(PREFERENCES_SHOW_CELSIUS, true)) {
        Forecast.TempUnit.CELSIUS
    } else {
        Forecast.TempUnit.FAHRENHEIT
    }

}