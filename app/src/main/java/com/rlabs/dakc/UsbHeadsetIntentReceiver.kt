package com.rlabs.dakc

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.util.Log


private const val TAG = "UsbHeadsetIntentReceiver"

class UsbHeadsetIntentReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            UsbManager.ACTION_USB_DEVICE_ATTACHED -> {
                val usbDevice: UsbDevice? = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE)
                var productName = ""

                // Check if the device is non-null
                usbDevice?.let {
                    Log.d(TAG, "USB Device Detected")
                    Log.d(TAG, "Vendor ID: ${it.vendorId}")
                    Log.d(TAG, "Product ID: ${it.productId}")
                    Log.d(TAG, "Device Class: ${it.deviceClass}")
                    Log.d(TAG, "Device Subclass: ${it.deviceSubclass}")
                    Log.d(TAG, "Device Name: ${it.deviceName}")
                    Log.d(TAG, "Device Protocol: ${it.deviceProtocol}")
                    Log.d(TAG, "Interface Count: ${it.interfaceCount}")
                    Log.d(TAG, "Product name: ${it.productName}")
                    Log.d(TAG, "Manufacturer name: ${it.manufacturerName}")
                    Log.d(TAG, "Config Count: ${it.configurationCount}")
                    productName = it.productName.toString()
                }
                if (productName.contains("Headphone")) {
                    Log.i(TAG, "Usb accessory $productName detected")
                    setVolume()
                }
            }
        }
    }
}
