package com.tho.guedragain

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button

class SettingsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        findViewById<View>(R.id.ok_btn).setOnClickListener { acceptSettings() }
        findViewById<View>(R.id.cancel_btn).setOnClickListener { cancelSettings() }

    }

    private fun cancelSettings() {
        // Finalizamos esta actividad, regresando a la anterior
        finish()
    }

    private fun acceptSettings() {
        // Finalizamos esta actividad, regresando a la anterior
        finish()
    }

}