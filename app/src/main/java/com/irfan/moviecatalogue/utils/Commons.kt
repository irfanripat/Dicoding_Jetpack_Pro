package com.irfan.moviecatalogue.utils

import android.view.View

object Commons {

    fun View.hide() {
        visibility = View.GONE
    }

    fun View.show() {
        visibility = View.VISIBLE
    }
}