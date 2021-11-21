package com.customer.domain.response

import java.time.LocalDateTime

data class CardClientResponse(

    val id: String,

    val customerId: String,

    val cardNumber: String,

    var status: String,

    val createAt: LocalDateTime,

    var updatedAt: LocalDateTime
)
