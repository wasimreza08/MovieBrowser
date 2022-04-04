package com.example.corecommon.ext

import java.io.IOException
import java.net.UnknownHostException

fun Throwable.isNetworkException() =
    this is IOException || this is UnknownHostException
