package com.exozet.demoapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.mindbody.countrylistapp.App

/**
 * Utility class to consolidate the utility functions
 */

object Util {

    /**
     * Function will return true in case of connectivity. False otherwise.
     */
    fun isNetworkAvailable(): Boolean {
        val connectivityManager = App.applicationContext().getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

}