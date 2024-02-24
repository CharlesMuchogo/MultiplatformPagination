package data.network

import kotlinx.coroutines.flow.Flow

expect class ConnectivityObserver {
    fun observe(): Flow<NetworkStatus>

}

enum class NetworkStatus{
    Available, Unavailable, Losing, Lost
}