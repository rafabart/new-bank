package com.packageservice.repository

import com.packageservice.domain.entity.Card
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CardRepository : JpaRepository<Card, Long> {

    fun findByCardNumber(cardNumber: String): Optional<Card>
}