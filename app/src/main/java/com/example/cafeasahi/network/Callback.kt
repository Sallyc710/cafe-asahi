package com.example.cafeasahi.network

interface Callback<T> {
    fun onSuccess(result: T?)
    fun onfailed(exception:Exception)
}