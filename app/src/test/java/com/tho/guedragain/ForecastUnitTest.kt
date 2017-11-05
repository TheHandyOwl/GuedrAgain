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

    // Tests básicos
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
        assertEquals(50f, forecast.getMinTemp(Forecast.TempUnit.FAHRENHEIT))
    }

    // Tests con excepciones
    @Test (expected = IllegalArgumentException::class)
    fun humidityOverRange_throwsArgumentException() {
        Forecast(
                25f,
                10f,
                100.01f,
                "Soleado con alguna nube",
                R.drawable.ico_01)
    }

    @Test (expected = IllegalArgumentException::class)
    fun humidityUnderRange_throwsArgumentException() {
        Forecast(
                25f,
                10f,
                -1f,
                "Soleado con alguna nube",
                R.drawable.ico_01)
    }

}