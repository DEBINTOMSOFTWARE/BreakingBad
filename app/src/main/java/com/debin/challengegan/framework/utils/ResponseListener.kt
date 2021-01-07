package com.debin.challengegan.framework.utils


interface ResponseListener {
    fun onLoading()
    fun onSuccess()
    fun onFailure(errorMessage : String)
}