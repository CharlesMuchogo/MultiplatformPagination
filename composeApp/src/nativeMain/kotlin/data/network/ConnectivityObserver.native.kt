package data.network

import kotlinx.coroutines.flow.Flow

actual class ConnectivityObserver {
    actual fun observe(): Flow<NetworkStatus> {
        TODO("Not yet implemented")
    }

}