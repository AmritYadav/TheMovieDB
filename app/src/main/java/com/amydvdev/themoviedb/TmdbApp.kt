package com.amydvdev.themoviedb

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TmdbApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}