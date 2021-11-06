package com.packageservice.domain.response

import com.packageservice.domain.entity.Benefit
import java.time.LocalDateTime

data class CardResponse(

    var id: Long?,

    val cardNumber: String,

    var benefits: MutableList<Benefit>?,

    var createAt: LocalDateTime?,

    var updatedAt: LocalDateTime?

)
