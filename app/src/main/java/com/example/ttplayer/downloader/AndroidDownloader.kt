package com.example.ttplayer.downloader

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

class AndroidDownloader(
    private val context: Context
) : Downloader {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String, mimeType: String, title: String): Long {
        val request = DownloadManager.Request(url.toUri())
            .setMimeType(mimeType)
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle(title)
            .addRequestHeader(
                "Authorization",
                "BIlsvgbp6xKexuyyVD4KXSshzOSdv3bb5Fv9kSVDGEFwck2Rzb1Amkbz"
            )
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, title)
        return downloadManager.enqueue(request)
    }
}