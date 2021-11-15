package com.packageservice.service

import com.packageservice.domain.entity.Card
import com.packageservice.domain.request.CardRequest
import com.packageservice.exception.CardNotFoundException
import com.packageservice.exception.RepeatedBenefitOnCardException
import com.packageservice.mapper.CardMapper
import com.packageservice.repository.CardRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CardService(

    val benefitService: BenefitService,
    val cardRepository: CardRepository,
    val cardMapper: CardMapper

) {


    @Transactional
    fun create(cardRequest: CardRequest): Card {
        return Optional.of(cardRequest)
            .map(cardMapper::toEntity)
            .map(cardRepository::save)
            .get()
    }


    fun findByCardNumber(cardNumber: String): Card {
        return this.cardRepository.findByCardNumber(cardNumber)
            .orElseThrow { CardNotFoundException("Cartão não encontrado. Número do cartão: $cardNumber") }
    }


    fun hasCardNumberInDatabase(cardNumber: String): Boolean {
        return this.cardRepository.findByCardNumber(cardNumber)
            .isPresent
    }


    @Transactional
    fun addCardBenefits(
        cardNumber: String,
        benefitId: Long
    ): Card {

//        TODO: Adicionar consumer kafka para receber o cartão novo quando criado na api card-api
//        TODO: Adicionar endpoint para remover beneficio de um cartão
        val hasBenefitOnCard = findByCardNumber(cardNumber).benefits.none { it.id == benefitId }.not()

        if (hasBenefitOnCard.not()) {
            return Optional.of(cardNumber)
                .map {
                    this.cardMapper.updateCardBenefits(
                        this.findByCardNumber(cardNumber),
                        this.benefitService.findById(benefitId)
                    )
                }
                .map(cardRepository::save)
                .get()

        } else {
            throw RepeatedBenefitOnCardException("Benefício (id = $benefitId) já presente no cartão (Número do cartão = $cardNumber)")
        }
    }
}
