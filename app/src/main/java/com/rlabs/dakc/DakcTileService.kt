package com.rlabs.dakc

import android.service.quicksettings.TileService
import android.util.Log


class DakcTileService : TileService() {


    // Called when the user taps on your tile in an active or inactive state.
    override fun onClick() {
        super.onClick()
        Log.d("Dakc", "Tile tapped")
        setVolume()
    }

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

    // Called when the user removes your tile.
    override fun onTileRemoved() {
        super.onTileRemoved()
    }

}