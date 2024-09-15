package com.one.airsync

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.wifi.p2p.WifiP2pConfig
import android.net.wifi.p2p.WifiP2pManager
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileInputStream
import java.net.ServerSocket
import android.app.Activity

actual class WifiDirectManager(private val  context: Context) {


    private val wifiP2pManager:
            WifiP2pManager = context.getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager
    private val channel: WifiP2pManager.Channel = wifiP2pManager.initialize(context, context.mainLooper, null)



    @SuppressLint("MissingPermission")
    actual fun startWifiDirectDiscovery() {

        wifiP2pManager.discoverPeers(channel, object :
        WifiP2pManager.ActionListener {
            override fun onSuccess() {
                TODO("Not yet implemented")
                // Discovery Started
            }

            override fun onFailure(p0: Int) {
                TODO("Not yet implemented")
                //Handle Failure
            }
        })
    }

    actual fun stopWifiDirectDiscovery() {

        wifiP2pManager.stopPeerDiscovery(channel, null)
    }

    @SuppressLint("MissingPermission")
    actual fun connectToPeer(deviceAddress: String) {

        var config = WifiP2pConfig()
        config.deviceAddress = deviceAddress


        wifiP2pManager.connect(channel, config, object : WifiP2pManager.ActionListener {
            override fun onSuccess() {
                TODO("Not yet implemented")
                //connect to peer
            }

            override fun onFailure(p0: Int) {
                TODO("Not yet implemented")
                //Handle Failure
            }
        })
    }

    actual fun sendFileOverWifiDirect(filePath: String, destinationAddress: String) {

        val serverSocket = ServerSocket(8888)
        val clientSocket = serverSocket.accept()

        val outputStream = clientSocket.outputStream
        val inputStream = FileInputStream(File(filePath))

        val buffer = ByteArray(1024)
        var bytesRead: Int
        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            outputStream.write(buffer, 0, bytesRead)
        }
        inputStream.close()
        outputStream.close()
        clientSocket.close()
        serverSocket.close()
    }
}