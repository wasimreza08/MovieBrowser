package com.example.featuremovie.presentation.view.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.corecommon.ext.plusAssign

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.example.featuremovie.presentation.uimodel.Movie

class MovieListAdapter(
    restorationPolicy: StateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
) : AsyncListDifferDelegationAdapter<Movie>(ItemDifferCallback()) {
    init {
        stateRestorationPolicy = restorationPolicy
        with(delegatesManager) {
            this += itemUIDelegate()
        }
    }
}

class ItemDifferCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}
