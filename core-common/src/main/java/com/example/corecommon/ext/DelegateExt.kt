package com.example.corecommon.ext

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

operator fun <T> AdapterDelegatesManager<T>.plusAssign(adapterDelegate: AdapterDelegate<T>?) {
    if (adapterDelegate != null) {
        addDelegate(adapterDelegate)
    }
}
