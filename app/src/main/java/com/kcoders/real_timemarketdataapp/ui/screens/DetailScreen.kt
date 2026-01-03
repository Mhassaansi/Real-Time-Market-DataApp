package com.kcoders.real_timemarketdataapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kcoders.real_timemarketdataapp.ui.viewmodel.MarketViewModel
import org.koin.compose.viewmodel.koinViewModel
import kotlin.collections.emptyList
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight



@Composable
fun DetailScreen() {
    val vm = koinViewModel<MarketViewModel>()

    // Simplified state observation
    val trades by vm.trades.collectAsState(initial = emptyList())
    val price by vm.price.collectAsState()
    val dir by vm.direction.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("BTC / USDT", fontSize = 16.sp, color = Color.Gray)
        Text(
            text = "$price",
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            color = if (dir == 1) Color.Green else if (dir == -1) Color.Red else Color.Gray
        )

        Spacer(Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Price (USDT)", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
            Text("Amount (BTC)", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 1.dp)


        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(trades) { trade ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = trade.price.toString(),
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = trade.quantity.toString(),
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}
