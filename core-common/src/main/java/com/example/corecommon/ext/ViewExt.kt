package com.example.corecommon.ext

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.DrawableRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

fun RecyclerView.doNotLeak(lifecycleOwner: LifecycleOwner) {
    lifecycleOwner.lifecycle.addObserver(RecyclerviewEventObserver(this))
}

internal class RecyclerviewEventObserver(private val recyclerView: RecyclerView) :
    LifecycleEventObserver {
    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            recyclerView.adapter = null
        }
    }

}

fun RecyclerView.asVerticalLayout(reverse: Boolean = false) {
    this.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, reverse)
}

fun ImageView.setImage(
    progress: ProgressBar,
    url: String,
    @DrawableRes errorImage: Int,
    cornerRadius: Int = 0
) {
    val requestOption: RequestOptions = if (cornerRadius > 0) {
        RequestOptions().transform(CenterCrop(), RoundedCorners(cornerRadius))
    } else {
        RequestOptions()
    }

    Glide
        .with(this.context)
        .load(url)
        .centerCrop()
        .listener(
            object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progress.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progress.visibility = View.GONE
                    return false
                }
            }
        ).apply(requestOption)
        .error(errorImage)
        .into(this)
}
