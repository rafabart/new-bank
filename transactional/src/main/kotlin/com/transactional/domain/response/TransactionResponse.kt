package com.transactional.domain.response

import com.transactional.domain.enum.TransactionStatus
import java.time.LocalDateTime

data class TransactionResponse(

    val id: String,
    val status: TransactionStatus,
    val createAt: LocalDateTime
)
