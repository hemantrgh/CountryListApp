package com.exozet.demoapp.network

import com.exozet.demoapp.exception.AppException

class NetworkResponse<T> private constructor(val status: Status, val data: T?, val exception: AppException?) {
    enum class Status {
        SUCCESS, ERROR
    }
    companion object {
        fun <T> success(data: T?): NetworkResponse<T> {
            return NetworkResponse(Status.SUCCESS, data, null)
        }
        fun <T> error(exception: AppException?): NetworkResponse<T> {
            return NetworkResponse(Status.ERROR, null, exception)
        }
    }
}