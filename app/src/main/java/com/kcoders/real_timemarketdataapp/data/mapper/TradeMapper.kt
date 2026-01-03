package com.kcoders.real_timemarketdataapp.data.mapper

import com.kcoders.real_timemarketdataapp.data.local.TradeEntity
import com.kcoders.real_timemarketdataapp.domain.model.Trade

fun TradeEntity.toDomain() =
    Trade(price, quantity, timestamp)

fun Trade.toEntity() =
    TradeEntity(
        price = price,
        quantity = quantity,
        timestamp = timestamp
    )
