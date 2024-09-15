package com.one.airsync

expect class FileManager {
    fun sendFile(filePath: String, destination: String)
    fun receiveFile(filePath: String): ByteArray

}