package com.tho.guedragain.model

import java.lang.IllegalArgumentException

class Forecast (val maxTemp: Float, val minTemp: Float, val humidity: Float, val description: String, val icon: Int) {
    enum class TempUnit {
        CELSIUS,
        FAHRENHEIT
    }

    init {
        if (humidity !in 0f..100f) {
            throw IllegalArgumentException("Humidity should be between 0 and 100")
        }
    }

    protected fun toFahrenheit(celsius: Float) = celsius * 1.8f + 32

    fun getMaxTemp(units: TempUnit) = when (units) {
        TempUnit.CELSIUS -> maxTemp
        TempUnit.FAHRENHEIT -> toFahrenheit(maxTemp)
    }

    fun getMinTemp(units: TempUnit) = when (units) {
        TempUnit.CELSIUS -> minTemp
        TempUnit.FAHRENHEIT -> toFahrenheit(minTemp)
    }

}