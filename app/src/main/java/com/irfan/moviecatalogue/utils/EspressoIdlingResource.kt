package com.irfan.moviecatalogue.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource : IdlingResource {
    private const val RESOURCE = "GLOBAL"
    val idlingResource = CountingIdlingResource(RESOURCE)

    override fun increment() {
        idlingResource.increment()
    }

    override fun decrement() {
        idlingResource.decrement()
    }
}

interface IdlingResource {

    fun increment()

    fun decrement()
}
