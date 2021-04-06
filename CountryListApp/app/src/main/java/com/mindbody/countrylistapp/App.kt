package com.mindbody.countrylistapp

import android.app.Application
import com.exozet.demoapp.di.AppComponent

/**
 * Singleton class for application level resources
 */

class App: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun applicationContext() : App {
            return instance as App
        }

        @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = AppComponent.create(this, getString(R.string.base_url));
    }

}