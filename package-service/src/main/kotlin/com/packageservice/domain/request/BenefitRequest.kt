package com.packageservice.domain.request

import com.packageservice.domain.enums.TaxCycleEnum
import java.math.BigDecimal

data class BenefitRequest(

    val title: String,

    val description: String,

    val price: BigDecimal,

    val taxCycle: TaxCycleEnum,

    var active: Boolean
)
