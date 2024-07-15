package com.rlabs.dakc

import android.service.quicksettings.TileService
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader


class DakcTileService : TileService() {


    // Called when the user taps on your tile in an active or inactive state.
    override fun onClick() {
        super.onClick()
        Log.d("QS", "Tile tapped")
        runAsRoot(arrayOf("tinymix -D 2 3 120"))
    }

}

fun runAsRoot(commands: Array<String>): Pair<StringBuilder, StringBuilder> {
    val output = StringBuilder()
    val errorOutput = StringBuilder()

    try {
        for (command in commands) {
            val processBuilder = ProcessBuilder("su", "-c", command)
            val process = processBuilder.start()

            // Read the output
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val errorReader = BufferedReader(InputStreamReader(process.errorStream))

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                output.append(line).append("\n")
            }
            while (errorReader.readLine().also { line = it } != null) {
                errorOutput.append(line).append("\n")
            }

            process.waitFor()

            // Log the output
            if (output.isNotEmpty()) {
                Log.d("Command Output", output.toString())
            }
            if (errorOutput.isNotEmpty()) {
                Log.e("Command Error", errorOutput.toString())
            }


        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return Pair(output, errorOutput)
}