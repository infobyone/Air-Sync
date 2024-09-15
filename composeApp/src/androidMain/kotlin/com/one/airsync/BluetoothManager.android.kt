package com.one.airsync

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileInputStream
import java.util.UUID

actual class BluetoothManager {
    private val bluetoothAdapter: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()


    @SuppressLint("MissingPermission")
    actual fun startBluetoothDiscovery() {
        if (bluetoothAdapter.isEnabled) {

            bluetoothAdapter.startDiscovery()
        }
    }

    @SuppressLint("MissingPermission")
    actual fun stopBluetoothDiscovery() {

        bluetoothAdapter.cancelDiscovery()
    }

    @SuppressLint("MissingPermission")
    actual fun sendFileOverBluetooth(filePath: String, destinationAddress: String) {
        val device = bluetoothAdapter.getRemoteDevice(destinationAddress)
        // Logic For Sending file

        val uuid = UUID.fromString("YOUR_UUID")

        val bluetoothSocket = device.createRfcommSocketToServiceRecord(uuid)
        bluetoothSocket.connect()

        val outputStream = bluetoothSocket.outputStream
        val inputStream =  FileInputStream(File(filePath))

        val buffer = ByteArray(1024)
        var bytesRead: Int
        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            outputStream.write(buffer, 0, bytesRead)
        }

        inputStream.close()
        outputStream.close()
        bluetoothSocket.close()

    }
}