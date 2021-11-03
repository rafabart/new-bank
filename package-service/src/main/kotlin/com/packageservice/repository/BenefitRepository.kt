package com.packageservice.repository

import com.packageservice.domain.entity.Benefit
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BenefitRepository : JpaRepository<Benefit, Long> {

    fun findByCardNumber(cardNumber: String): Optional<Benefit>
}