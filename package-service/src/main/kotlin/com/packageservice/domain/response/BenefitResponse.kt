package com.packageservice.domain.response

import com.packageservice.domain.enums.TaxCycleEnum
import java.math.BigDecimal
import java.time.LocalDateTime

data class BenefitResponse(

    val id: Long?,

    val cardNumber: String,

    val title: String,

    val description: String,

    val price: BigDecimal?,

    val taxCycle: TaxCycleEnum?,

    val createAt: LocalDateTime?,

    val updatedAt: LocalDateTime?,

    var active: Boolean?
)
