package com.rlabs.dakc

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    lateinit var dev1: TextView
    lateinit var dev2: TextView
    lateinit var dev3: TextView
    lateinit var refreshButton: Button
    lateinit var dakcButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        dev1 = findViewById(R.id.dev1)
        dev2 = findViewById(R.id.dev2)
        dev3 = findViewById(R.id.dev3)

        fun readFields() {
            dev1.text = tinymixOut(device = "0")
            dev2.text = tinymixOut(device = "1")
            dev3.text = tinymixOut(device = "2")
        }
        readFields()

        refreshButton = findViewById(R.id.button1)
        refreshButton.setOnClickListener { readFields() }

        dakcButton = findViewById(R.id.dakcIt)
        dakcButton.setOnClickListener {
            Log.d("Dakc", "DAKC button in MainActivity tapped")
            setVolume()
        }
    }
}