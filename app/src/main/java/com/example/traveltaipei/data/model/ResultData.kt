package com.example.traveltaipei.data.model

sealed class ResultData<out T> {
    data class Success<out T>(val data: T) : ResultData<T>()
    data class Error(val code: Int, val message: String?) : ResultData<Nothing>()
}
