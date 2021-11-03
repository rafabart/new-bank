package com.transactional.domain.response

import com.transactional.domain.enum.TransactionStatus
import java.math.BigDecimal
import java.time.LocalDateTime

data class TransactionResponse(

    val id: String,
    val value: BigDecimal,
    val status: TransactionStatus,
    val createAt: LocalDateTime
)
