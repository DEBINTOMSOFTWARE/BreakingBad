package com.debin.challengegan.framework.utils

sealed class Resource<out T> {
    data class Loading(val mgs : String?=null) : Resource<Nothing>()
    data class Success<out R> (val result: R) : Resource<R>()
    data class Error(val mgs: String?) : Resource<Nothing>()
}