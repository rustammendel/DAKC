package com.rlabs.dakc

import android.service.quicksettings.TileService
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader


class DakcTileService : TileService() {


    // Called when the user adds your tile.
    override fun onTileAdded() {
        super.onTileAdded()
    }

    // Called when your app can update your tile.
    override fun onStartListening() {
        super.onStartListening()
    }

    // Called when your app can no longer update your tile.
    override fun onStopListening() {
        super.onStopListening()
    }

    // Called when the user taps on your tile in an active or inactive state.
    override fun onClick() {
        super.onClick()
        Log.d("QS", "Tile tapped")
        runAsRoot(arrayOf("tinymix -D 2 3 120"))
    }

    // Called when the user removes your tile.
    override fun onTileRemoved() {
        super.onTileRemoved()
    }
}

fun runAsRoot(commands: Array<String>) {

    try {
        for (command in commands) {
            val processBuilder = ProcessBuilder("su", "-c", command)
            val process = processBuilder.start()

            // Read the output
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val errorReader = BufferedReader(InputStreamReader(process.errorStream))
            val output = StringBuilder()
            val errorOutput = StringBuilder()

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

}