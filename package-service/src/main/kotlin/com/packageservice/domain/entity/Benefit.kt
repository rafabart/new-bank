package com.packageservice.domain.entity

import com.packageservice.domain.enums.TaxCycleEnum
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Benefit(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null,

    val title: String,

    val description: String,

    var price: BigDecimal? = BigDecimal.ZERO,

    var taxCycle: TaxCycleEnum? = TaxCycleEnum.NEVER,

    @CreationTimestamp
    var createAt: LocalDateTime? = null,

    @UpdateTimestamp
    var updatedAt: LocalDateTime? = null,

    var active: Boolean? = false

)
