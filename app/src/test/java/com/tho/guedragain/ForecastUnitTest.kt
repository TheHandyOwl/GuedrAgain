package com.tho.guedragain

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

}