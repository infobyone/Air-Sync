package com.one.airsync


expect class WifiDirectManager {
    fun startWifiDirectDiscovery()
    fun stopWifiDirectDiscovery()
    fun connectToPeer(deviceAddress: String)
    fun sendFileOverWifiDirect(filePath: String, destinationAddress: String)
}