package com.warrantysafe.app.domain.utils

sealed class Results<out T> {
    data class Success<out T>(val data: T) : Results<T>()
    data class Failure(val exception: Throwable) : Results<Nothing>()
    object Loading : Results<Nothing>()
}
