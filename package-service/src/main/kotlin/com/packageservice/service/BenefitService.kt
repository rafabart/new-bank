package com.packageservice.service

import com.packageservice.domain.entity.Benefit
import com.packageservice.domain.request.BenefitRequest
import com.packageservice.exception.BenefitNotFoundException
import com.packageservice.mapper.BenefitMapper
import com.packageservice.repository.BenefitRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class BenefitService(

    val benefitMapper: BenefitMapper,
    val benefitRepository: BenefitRepository
) {


    @Transactional
    fun create(benefitRequest: BenefitRequest): Benefit {
        return Optional.of(benefitRequest)
            .map(benefitMapper::toEntity)
            .map(benefitRepository::save)
            .get()
    }


    fun findById(id: Long): Benefit {
        return this.benefitRepository.findById(id)
            .orElseThrow { BenefitNotFoundException("Benefício não encontrado. Id: $id") }
    }


    @Transactional
    fun activeBenefit(id: Long) {
        Optional.of(id)
            .map(this::findById)
            .map(benefitMapper::toActiveStatus)
            .map(benefitRepository::save)
    }


    @Transactional
    fun inactiveBenefit(id: Long) {
        Optional.of(id)
            .map(this::findById)
            .map(benefitMapper::toInactiveStatus)
            .map(benefitRepository::save)
    }


    @Transactional
    fun update(id: Long, benefitRequest: BenefitRequest): Benefit {
        return Optional.of(id)
            .map(this::findById)
            .map { this.benefitMapper.updateFromRequest(it, benefitRequest) }
            .map(benefitRepository::save)
            .get()
    }
}
