package com.irfan.moviecatalogue.utils

import android.view.View
import android.widget.ImageView
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher


object DrawableMatcher {

    fun hasDrawable() = object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("ImageView have drawable")
        }

        override fun matchesSafely(view: View): Boolean {
            return view is ImageView && view.drawable != null
        }
    }

}