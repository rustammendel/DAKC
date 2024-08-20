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

    }

}

fun tinymixOut(device: String, lineNum: Int = 0): String {
    val outPair = runAsRoot(arrayOf("tinymix -D $device | head -20"))

    val out = outPair.first.toString()
    val errout = outPair.second.toString()

    val outArr = out.split("\n")

    val firstLine = if (errout.isNotEmpty()) errout.split("\n")[0] else outArr[lineNum]
    val iVolLine = outArr.indexOfFirst { s -> s.contains("Headset Playback Volume") }

    val volInfo = if (errout.isEmpty() && iVolLine != -1) outArr[iVolLine] else ""

    Log.d("tinymix", out)

    return "tinymix -D $device out: \n$firstLine \n$volInfo"

}