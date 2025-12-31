package com.kcoders.real_timemarketdataapp.domain.model

data class Trade(
    val price: Double,
    val quantity: Double,
    val timestamp: Long
)

