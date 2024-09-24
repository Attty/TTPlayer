package com.example.ttplayer.common

fun <T> List<T>.findOrFirst(predicate: (T) -> Boolean): T {
    return this.find(predicate) ?: this.first()
}