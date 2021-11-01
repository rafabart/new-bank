package com.transactional.repository

import com.transactional.domain.document.Transaction
import org.springframework.data.mongodb.repository.MongoRepository

interface TransactionRepository : MongoRepository<Transaction, String> {

    fun findByCardNumber(cardNumber: String): List<Transaction>
}