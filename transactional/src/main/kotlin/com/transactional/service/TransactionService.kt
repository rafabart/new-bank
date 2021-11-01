package com.transactional.service

import com.transactional.domain.document.Transaction
import com.transactional.domain.request.TransactionRequest
import com.transactional.exception.CardStatusException
import com.transactional.exception.TransactionNotFoundException
import com.transactional.mapper.TransactionMapper
import com.transactional.repository.TransactionRepository
import org.springframework.stereotype.Service

@Service
class TransactionService(

    val transactionMapper: TransactionMapper,
    val transactionRepositoy: TransactionRepository,
    val cardService: CardService

) {


    fun create(transactionRequest: TransactionRequest): Transaction {


        if (this.cardService.isValidCard(transactionRequest.cardNumber)) {
            val transaction = this.transactionMapper.toEntity(transactionRequest)
            return this.transactionRepositoy.insert(transaction)
        } else {

            throw CardStatusException("Cartão não esta ativo")
        }

    }


    fun findById(id: String): Transaction {
        return this.transactionRepositoy.findById(id)
            .orElseThrow { TransactionNotFoundException("Transação não encontrado. Id = $id") }
    }


    fun getAllByCardNumber(cardNumber: String): List<Transaction> {
        return this.transactionRepositoy.findByCardNumber(cardNumber)
    }
}
