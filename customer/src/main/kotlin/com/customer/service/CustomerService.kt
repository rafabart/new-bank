package com.customer.service

import com.customer.domain.entity.Customer
import com.customer.domain.request.CustomerRequest
import com.customer.exception.CustomerNotFoundException
import com.customer.mapper.CustomerMapper
import com.customer.repository.CustomerRepository
import com.customer.util.Log
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CustomerService(

    private val customerRepository: CustomerRepository,
    private val customerMapper: CustomerMapper,
    private val cardService: CardService

) {
    companion object : Log()


    @Transactional
    fun create(customerRequest: CustomerRequest): Customer {
        return Optional.of(customerRequest)
            .map(customerMapper::toEntity)
            .map(customerRepository::saveAndFlush)
            .map(cardService::createCard)
            .get()
    }


    fun findById(id: UUID): Customer {
        return this.customerRepository.findById(id)
            .orElseThrow {
                logger.info("[CustomerService][findById]: Cliente não encontrado. Id: $id")
                CustomerNotFoundException("Cliente não encontrado. Id: $id")
            }
    }


    fun findAllByEmail(email: String?, pageable: Pageable): Page<Customer> {
        return this.customerRepository.findAllByEmail(email, pageable)
    }


    fun findAll(pageable: Pageable): Page<Customer> {
        return this.customerRepository.findAll(pageable)
    }
}
