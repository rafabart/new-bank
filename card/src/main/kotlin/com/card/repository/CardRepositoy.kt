package com.card.repository

import com.card.domain.document.Card
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface CardRepositoy : MongoRepository<Card, String> {

    fun findByCardNumber(cardNumber: String): Optional<Card>

    fun findByCustomerId(cardNumber: String): List<Card>
}