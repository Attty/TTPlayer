package com.example.ttplayer.downloader

interface Downloader {
    fun downloadFile(url: String, mimeType: String, title: String): Long
}