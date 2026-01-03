package com.kcoders.real_timemarketdataapp.data.websocket

import com.kcoders.real_timemarketdataapp.domain.model.Trade
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.plugins.websocket.wss
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject

enum class ConnectionState { CONNECTING, CONNECTED, DISCONNECTED, ERROR }

class BinanceWebSocketService {
    private val client = HttpClient(OkHttp) { install(WebSockets) }


    private val _state = MutableStateFlow(ConnectionState.DISCONNECTED)
    val state = _state.asStateFlow()

    fun connect(): Flow<Trade> = flow {
        try {
            _state.value = ConnectionState.CONNECTING
            client.wss(
                method = HttpMethod.Get,
                host = "stream.binance.com",
                port = 9443,
                path = "/ws/btcusdt@trade"
            ) {
                _state.value = ConnectionState.CONNECTED
                for (frame in incoming) {
                    if (frame is Frame.Text) {
                        val json = JSONObject(frame.readText())
                        emit(Trade(
                            price = json.getString("p").toDouble(),
                            quantity = json.getString("q").toDouble(),
                            timestamp = json.getLong("T")
                        ))
                    }
                }
            }
        } catch (e: Exception) {
            _state.value = ConnectionState.ERROR
        } finally {
            _state.value = ConnectionState.DISCONNECTED
        }
    }
}
