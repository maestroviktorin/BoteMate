package com.example.boatmatea

import android.app.Application
import com.example.boatmatea.data.AppContainer
import com.example.boatmatea.data.AppDataContainer

class BoatMateApplication : Application() {
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}