package com.transactional.service

import com.transactional.domain.document.Transaction
import com.transactional.domain.request.TransactionRequest
import com.transactional.exception.TransactionNotFound
import com.transactional.mapper.TransactionMapper
import com.transactional.repository.TransactionRepository
import org.springframework.stereotype.Service

@Service
class TransactionService(

    val transactionMapper: TransactionMapper,
    val transactionRepositoy: TransactionRepository

) {


    fun create(transactionRequest: TransactionRequest): Transaction {

        val transaction = this.transactionMapper.toEntity(transactionRequest)

        return this.transactionRepositoy.insert(transaction)
    }


    fun findById(id: String): Transaction {
        return this.transactionRepositoy.findById(id)
            .orElseThrow { TransactionNotFound("Transação não encontrado. Id = $id") }
    }


    fun getAllByCardNumber(cardNumber: String): List<Transaction> {
        return this.transactionRepositoy.findByCardNumber(cardNumber)
    }
}
