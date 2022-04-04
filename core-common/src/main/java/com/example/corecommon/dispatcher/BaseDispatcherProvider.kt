package com.example.corecommon.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface BaseDispatcherProvider {
    fun main(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
    fun compute(): CoroutineDispatcher
}