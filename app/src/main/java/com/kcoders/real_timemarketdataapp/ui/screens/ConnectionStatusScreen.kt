package com.kcoders.real_timemarketdataapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kcoders.real_timemarketdataapp.data.websocket.ConnectionState
import com.kcoders.real_timemarketdataapp.ui.viewmodel.MarketViewModel
import io.ktor.websocket.Frame
import org.koin.compose.viewmodel.koinViewModel

/*@Composable
fun ConnectionStatusScreen() {
    val vm = koinViewModel<MarketViewModel>()
    val status by vm.connection.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.padding(24.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("System Status", style = MaterialTheme.typography.titleMedium)

                Spacer(Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                    ) {


                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(
                                color = when (status) {
                                    ConnectionState.CONNECTED -> Color.Green
                                    ConnectionState.CONNECTING -> Color.Yellow
                                    else -> Color.Red
                                },
                                shape = CircleShape
                            )
                    )

                    Spacer(Modifier.width(12.dp))

                    Text(
                        text = "WebSocket: $status",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(Modifier.height(8.dp))
                Text("Data Source: Binance Stream", fontSize = 14.sp, color = Color.Gray)
            }
        }

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = { *//* Call vm.reconnect() *//* },
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text("Reconnect")
        }
    }
}*/

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay

@Composable
fun ConnectionStatusScreen() {
   // val ctx = LocalContext.current
    val vm = koinViewModel<MarketViewModel>()
    val status by vm.connection.collectAsState()
    val lastUpdateEpoch by vm.lastUpdate.collectAsState()

    var currentTime by remember { mutableLongStateOf(System.currentTimeMillis()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            currentTime = System.currentTimeMillis()
        }
    }

    val secondsAgo = if (lastUpdateEpoch == 0L) {
        "No data received"
    } else {
        "${(currentTime - lastUpdateEpoch) / 1000}s ago"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 10.dp
            ),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "CONNECTION STATUS",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Gray
                )

                Spacer(Modifier.height(24.dp))


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(14.dp)
                            .background(
                                color = when (status) {
                                    ConnectionState.CONNECTED -> Color.Green
                                    ConnectionState.CONNECTING -> Color.Yellow
                                    else -> Color.Red
                                },
                                shape = CircleShape
                            )
                    )

                    Spacer(Modifier.width(12.dp))

                    Text(
                        text = status.toString(),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.sp
                    )
                }

                Spacer(Modifier.height(16.dp))


                Text(
                    text = "Last update: $secondsAgo",
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = "Source: stream.binance.com",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(Modifier.height(40.dp))


//        Button(
//            onClick = { Toast.makeText(ctx,"Reconnecting",
//                Toast.LENGTH_SHORT).show() },
//            modifier = Modifier.fillMaxWidth(0.8f),
//            shape = MaterialTheme.shapes.medium
//        ) {
//            Text("Reconnect Socket", modifier = Modifier.padding(vertical = 4.dp))
//        }
    }
}