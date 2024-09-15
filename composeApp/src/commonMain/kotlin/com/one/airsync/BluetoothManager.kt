package com.one.airsync


expect class BluetoothManager {
    fun startBluetoothDiscovery()
    fun stopBluetoothDiscovery()
    fun sendFileOverBluetooth(filePath: String, destinationAddress: String)
}