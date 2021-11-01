package com.transactional.domain.request

import com.transactional.domain.enum.TransactionType
import java.math.BigDecimal

data class TransactionRequest(

    val value: BigDecimal,
    val cardNumber: String,
    val type: TransactionType,
    val cnpj: String? = null,
    val cpf: String? = null
)
