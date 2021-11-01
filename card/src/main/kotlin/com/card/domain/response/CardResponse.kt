package com.card.domain.response

import com.card.domain.enums.CardStatus
import java.time.LocalDateTime

data class CardResponse(

    val id: String,

    val customerId: String,

    val cardNumber: String,

    var status: CardStatus,

    val createAt: LocalDateTime,

    var updatedAt: LocalDateTime
)
