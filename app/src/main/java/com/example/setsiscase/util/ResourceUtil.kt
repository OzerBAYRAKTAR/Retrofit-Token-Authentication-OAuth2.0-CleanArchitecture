package com.example.setsiscase.util


sealed class ResourceUtil<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : ResourceUtil<T>(data)
    class Error<T>(message: String, data: T? = null) : ResourceUtil<T>(data, message)
    class Loading<T>(data: T? = null) : ResourceUtil<T>(data)
}