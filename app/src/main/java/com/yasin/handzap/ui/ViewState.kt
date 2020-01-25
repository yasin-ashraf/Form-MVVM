package com.yasin.handzap.ui

/**
 * Created by Yasin on 25/01/20.
 */
sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    object EmptyList : ViewState<Nothing>()
    data class Success<T>(val data: T) : ViewState<T>()
}