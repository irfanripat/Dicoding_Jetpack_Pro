package com.irfan.moviecatalogue.utils

import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.irfan.moviecatalogue.utils.EspressoIdlingResource.idlingResource

class IdlingResourceTarget(view: ImageView) : DrawableImageViewTarget(view) {

    private var isLoading = false
        set(value) {
            if (field != value) {
                field = value
                if (value) idlingResource.increment() else idlingResource.decrement()
            }
        }

    private val checkSizeTimeOutRunnable = Runnable {
        isLoading = false
    }

    override fun onLoadStarted(placeholder: Drawable?) {
        isLoading = true
        val handler = Handler(Looper.getMainLooper())
        // If we cannot get a valid size during the delay (1000ms) then set isLoading to false
        handler.postDelayed(checkSizeTimeOutRunnable, 1_000)
        getSize { _, _ ->
            // This callback will only be called if the size is valid
            handler.removeCallbacks(checkSizeTimeOutRunnable)
        }
        super.onLoadStarted(placeholder)
    }
    override fun onLoadFailed(errorDrawable: Drawable?) {
        isLoading = false
        super.onLoadFailed(errorDrawable)
    }
    override fun onLoadCleared(placeholder: Drawable?) {
        isLoading = false
        super.onLoadCleared(placeholder)
    }
    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
        isLoading = false
        super.onResourceReady(resource, transition)
    }
}