package com.tho.guedragain

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var stoneButton : Button? = null
    var donkeyButton : Button? = null

    val TAG = MainActivity::class.java.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stoneButton = findViewById<Button>(R.id.stone_button)
        donkeyButton = findViewById<Button>(R.id.donkey_button)

        stoneButton?.setOnClickListener(this)
        donkeyButton?.setOnClickListener(this)

        Log.v(TAG, "Pasando por onCreate")

        if (savedInstanceState != null) {
            Log.v(TAG, "savedInstanceState no es null y clave vale: ${savedInstanceState.getString("clave")}")
        } else {
            Log.v(TAG, "savedInstanceState ES null")
        }

    }

    override fun onClick(v: View?) {
        Log.v(TAG, "Pasando por onClick")

        // Solución 1
        if (v == stoneButton) {
            Log.v(TAG, "Se ha pulsado stoneButton")
        } else if (v == donkeyButton) {
            Log.v(TAG, "Se ha pulsado donkeyButton")
        } else {
            Log.v(TAG, "Se ha pulsado algún botón")
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.v(TAG, "Pasando por onSaveInstanceState")
        outState?.putString("clave","valor")
    }



}
