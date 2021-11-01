package com.transactional.domain.document

import com.transactional.domain.enum.TransactionStatus
import com.transactional.domain.enum.TransactionType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Document
data class Transaction(

    @Id
    val id: String = UUID.randomUUID().toString(),

    val value: BigDecimal,

    @Indexed
    val cardNumber: String,

    val cnpj: String? = null,

    val cpf: String? = null,

    val fee: Boolean? = false,

    val description: String? = null,

    var status: TransactionStatus = TransactionStatus.NEW,

    val type: TransactionType,

    val createAt: LocalDateTime = LocalDateTime.now()
)
