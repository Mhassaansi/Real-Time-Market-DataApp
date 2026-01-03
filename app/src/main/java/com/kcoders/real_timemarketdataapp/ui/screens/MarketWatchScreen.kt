package com.kcoders.real_timemarketdataapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
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
import com.kcoders.real_timemarketdataapp.ui.viewmodel.MarketViewModel
import org.koin.compose.viewmodel.koinViewModel



@Composable
fun MarketWatchScreen(nav: NavController) {
    val vm = koinViewModel<MarketViewModel>()
    val price by vm.price.collectAsState()
    val dir by vm.direction.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "BTC / USDT",
                fontSize = 20.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Medium
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "$price",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = when (dir) {
                        1 -> Color.Green
                        -1 -> Color.Red
                        else -> Color.Gray
                    }
                )
                Text(
                    text = if (dir == 1) " ↑" else if (dir == -1) " ↓" else "",
                    fontSize = 32.sp,
                    color = if (dir == 1) Color.Green else Color.Red
                )
            }

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = { nav.navigate("detail") },
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Text("View Details")
            }
        }


        OutlinedButton(
            onClick = { nav.navigate("status") },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            Text("Connection Status")
        }
    }
}
