package com.tho.guedragain

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ForecastUnitTest {

    lateinit var forecast: Forecast

    @Before
    fun setUp() {
        forecast = Forecast(
                25f,
                10f,
                35f,
                "Soleado con alguna nube",
                R.drawable.ico_01)
    }

    @Test
    fun maxTempUnitsCelsius_isCorrect() {
        assertEquals(25f, forecast.getMaxTemp(Forecast.TempUnit.CELSIUS))
    }

    @Test
    fun minTempUnitsCelsius_isCorrect() {
        assertEquals(10f, forecast.getMinTemp(Forecast.TempUnit.CELSIUS))
    }

    @Test
    fun maxTempUnitsConversionFahrenheit_isCorrect() {
        assertEquals(77f, forecast.getMaxTemp(Forecast.TempUnit.FAHRENHEIT))
    }

    @Test
    fun minTempUnitsConversionFahrenheit_isCorrect() {
        assertEquals(50f, forecast.getMaxTemp(Forecast.TempUnit.FAHRENHEIT))
    }

}