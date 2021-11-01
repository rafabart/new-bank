package com.card.domain.request

import com.card.domain.enums.CardStatus
import java.time.LocalDateTime

data class CardStatusRequest(

    val cardNumber: String,

    var status: CardStatus,

    var updatedAt: LocalDateTime = LocalDateTime.now()
)
