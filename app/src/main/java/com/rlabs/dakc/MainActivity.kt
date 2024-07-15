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
    lateinit var button1: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        dev1 = findViewById(R.id.dev1)
        dev1.text = TinymixOut(device = "1")

        dev2 = findViewById(R.id.dev2)
        dev2.text = TinymixOut(device = "2")

        dev3 = findViewById(R.id.dev3)
        dev3.text = TinymixOut(device = "2", 7)


        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            dev1.text = TinymixOut(device = "1")
            dev2.text = TinymixOut(device = "2")
            dev3.text = TinymixOut(device = "2", 7)
        }

    }


}

fun TinymixOut(device: String, lineNum: Int = 0): String {
    val outPair = runAsRoot(arrayOf("tinymix -D $device"))

    val out = outPair.first.toString()
    val errout = outPair.second.toString()

    val firstLine = if (errout.isNotEmpty()) errout.split("\n")[0] else out.split("\n")[lineNum]

    Log.d("tinymix", out)

    return "tinymix -D $device out: \n$firstLine"

}