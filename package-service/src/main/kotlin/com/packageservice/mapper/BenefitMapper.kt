package com.packageservice.mapper

import com.packageservice.domain.entity.Benefit
import com.packageservice.domain.request.BenefitRequest
import com.packageservice.domain.response.BenefitResponse
import org.springframework.stereotype.Component

@Component
class BenefitMapper {


    fun toEntity(benefitRequest: BenefitRequest): Benefit {
        return Benefit(
            cardNumber = benefitRequest.cardNumber,
            title = benefitRequest.title,
            description = benefitRequest.description,
            price = benefitRequest.price,
            taxCycle = benefitRequest.taxCycle,
            active = benefitRequest.active
        )
    }


    fun toReponse(benefit: Benefit): BenefitResponse {
        return BenefitResponse(
            id = benefit.id,
            cardNumber = benefit.cardNumber,
            title = benefit.title,
            description = benefit.description,
            price = benefit.price,
            taxCycle = benefit.taxCycle,
            createAt = benefit.createAt,
            updatedAt = benefit.updatedAt,
            active = benefit.active
        )
    }


    fun toActiveStatus(benefit: Benefit): Benefit {
        return Benefit(
            id = benefit.id,
            cardNumber = benefit.cardNumber,
            title = benefit.title,
            description = benefit.description,
            price = benefit.price,
            taxCycle = benefit.taxCycle,
            createAt = benefit.createAt,
            updatedAt = benefit.updatedAt,
            active = true
        )
    }


    fun toInactiveStatus(benefit: Benefit): Benefit {
        return Benefit(
            id = benefit.id,
            cardNumber = benefit.cardNumber,
            title = benefit.title,
            description = benefit.description,
            price = benefit.price,
            taxCycle = benefit.taxCycle,
            createAt = benefit.createAt,
            updatedAt = benefit.updatedAt,
            active = false
        )
    }
}
