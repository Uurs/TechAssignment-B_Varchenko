package b.varchenko.data.network

import android.util.Log
import java.net.InetAddress
import javax.inject.Inject

private const val TAG = "NetworkConnection"

interface NetworkAvailability {
    suspend fun isNetworkAvailable(): Boolean
}

internal class NetworkAvailabilityImpl @Inject constructor() : NetworkAvailability {
    override suspend fun isNetworkAvailable(): Boolean {
        return try {
            InetAddress.getByName("google.com").isReachable(2000)
        } catch (e: Exception) {
            Log.e(TAG, "checkIsNetworkAccessible: failed with exception", e)
            false
        }
    }
}