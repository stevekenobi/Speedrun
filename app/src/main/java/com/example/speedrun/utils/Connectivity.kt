package com.example.speedrun.utils

import android.net.*
import android.net.ConnectivityManager.NetworkCallback
import android.os.Handler
import android.telephony.TelephonyManager
import com.example.speedrun.events.NetworkAvailableEvent
import org.greenrobot.eventbus.EventBus
import timber.log.Timber
import javax.inject.Inject

class Connectivity @Inject constructor(
    val connectivityManager: ConnectivityManager,
    val eventBus: EventBus
) {

    private var isNetworkMonitoringActive = false
    private var isConnected = false
    private val handler = Handler()

    private fun getNetworkInfo(): NetworkInfo? {
        return connectivityManager.activeNetworkInfo
    }

    private fun getNetwork(): Network? {
        val activeNetworkInfo = getNetworkInfo() ?: return null

        val networks = connectivityManager.allNetworks
        networks.forEach {
            val networkInfo = connectivityManager.getNetworkInfo(it)

            if (networkInfo != null && networkInfo.toString() == activeNetworkInfo.toString())
                return it
        }

        return null
    }

    private fun getNetworkCapabilities(): NetworkCapabilities? {
        val network = getNetwork() ?: return null

        return connectivityManager.getNetworkCapabilities(network)
    }

    fun isConnecting(): Boolean {
        val info = getNetworkInfo()

        return (info != null && info.isConnectedOrConnecting)
    }

    fun isConnected(): Boolean {
        val capabilities = getNetworkCapabilities()
        val info = getNetworkInfo()

        return (info != null &&
                info.isConnected &&
                capabilities != null &&
                capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_CAPTIVE_PORTAL).not())
    }

    fun isConnectedWifi(): Boolean {
        val info = getNetworkInfo()
        return (isConnected() && info?.type == ConnectivityManager.TYPE_WIFI)
    }

    fun isConnectedMobile(): Boolean {
        val info = getNetworkInfo()
        return (isConnected() && info?.type == ConnectivityManager.TYPE_MOBILE)
    }

    fun isConnectedFast(): Boolean {
        val info = getNetworkInfo() ?: return false
        return (isConnected() && isConnectionFast(info.type, info.subtype))
    }

    fun isConnectionFast(type: Int, subType: Int): Boolean {
        return if (type == ConnectivityManager.TYPE_WIFI) {
            true
        } else if (type == ConnectivityManager.TYPE_MOBILE) {
            when (subType) {
                TelephonyManager.NETWORK_TYPE_1xRTT -> false // ~ 50-100 kbps
                TelephonyManager.NETWORK_TYPE_CDMA -> false // ~ 14-64 kbps
                TelephonyManager.NETWORK_TYPE_EDGE -> false // ~ 50-100 kbps
                TelephonyManager.NETWORK_TYPE_EVDO_0 -> true // ~ 400-1000 kbps
                TelephonyManager.NETWORK_TYPE_EVDO_A -> true // ~ 600-1400 kbps
                TelephonyManager.NETWORK_TYPE_GPRS -> false // ~ 100 kbps
                TelephonyManager.NETWORK_TYPE_HSDPA -> true // ~ 2-14 Mbps
                TelephonyManager.NETWORK_TYPE_HSPA -> true // ~ 700-1700 kbps
                TelephonyManager.NETWORK_TYPE_HSUPA -> true // ~ 1-23 Mbps
                TelephonyManager.NETWORK_TYPE_UMTS -> true // ~ 400-7000 kbps
                TelephonyManager.NETWORK_TYPE_EHRPD -> true // ~ 1-2 Mbps
                TelephonyManager.NETWORK_TYPE_EVDO_B -> true // ~ 5 Mbps
                TelephonyManager.NETWORK_TYPE_HSPAP -> true // ~ 10-20 Mbps
                TelephonyManager.NETWORK_TYPE_IDEN -> false // ~25 kbps
                TelephonyManager.NETWORK_TYPE_LTE -> true // ~ 10+ Mbps
                TelephonyManager.NETWORK_TYPE_UNKNOWN -> false
                else -> false
            }
        } else {
            false
        }
    }

    private val networkCallback: NetworkCallback = object : NetworkCallback() {
        override fun onAvailable(network: Network) {
            Timber.d("Connectivity.onAvailable")
            triggerNetworkEvents()
        }

        override fun onLost(network: Network) {
            Timber.d("Connectivity.onLost")
            triggerNetworkEvents()
        }
    }

    fun startNetworkMonitoring() {
        if (isNetworkMonitoringActive) {
            initConnected(isConnected())
            return
        }

        initConnected(isConnected())

        val networkRequestBuilder = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)

        val networkRequest = networkRequestBuilder.build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)

        isNetworkMonitoringActive = true
    }

    fun stopNetworkMonitoring() {
        if (!isNetworkMonitoringActive)
            return

        connectivityManager.unregisterNetworkCallback(networkCallback)
        isNetworkMonitoringActive = false
    }


    private fun initConnected(isConnected: Boolean) {
        this.isConnected = isConnected
    }

    private fun triggerNetworkEvents() {
        val isTrulyConnected = isConnected()

        if (isTrulyConnected && isConnected.not()) {
            isConnected = true
            handler.removeCallbacksAndMessages(null)
            handler.postDelayed( {
                val networkAvailableEvent = NetworkAvailableEvent(isConnected(), isConnectedFast())
                eventBus.post(networkAvailableEvent)
            }, 2000)
        } else if (!isTrulyConnected && isConnected){
            isConnected = false
            handler.removeCallbacksAndMessages(null)
            handler.postDelayed({
                eventBus.post(NetworkAvailableEvent(isConnected = false, isConnectedFast = false))
            }, 2000)
        }
    }


}