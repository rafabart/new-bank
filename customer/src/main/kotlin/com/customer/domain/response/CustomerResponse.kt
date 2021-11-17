package com.customer.domain.response

import java.time.LocalDateTime
import java.util.*

data class CustomerResponse(

    var id: UUID,

    val email: String,

    var name: String,

    val cnpj: String?,

    val cpf: String?,

    var createAt: LocalDateTime,

    var updatedAt: LocalDateTime
)
