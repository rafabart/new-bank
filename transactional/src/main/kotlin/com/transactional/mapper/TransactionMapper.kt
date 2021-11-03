package com.transactional.mapper

import com.transactional.domain.document.Transaction
import com.transactional.domain.request.TransactionRequest
import com.transactional.domain.response.TransactionResponse
import org.springframework.stereotype.Component

@Component
class TransactionMapper {


    fun toEntity(transactionRequest: TransactionRequest): Transaction {
        return Transaction(
            value = transactionRequest.value,
            cardNumber = transactionRequest.cardNumber,
            type = transactionRequest.type,
            cnpj = transactionRequest.cnpj,
            cpf = transactionRequest.cpf
        )
    }


    fun toResponse(transaction: Transaction): TransactionResponse {
        return TransactionResponse(
            id = transaction.id,
            value = transaction.value,
            status = transaction.status,
            createAt = transaction.createAt
        )
    }
}