package com.customer

import com.customer.domain.entity.Customer
import com.customer.domain.request.CustomerRequest
import com.customer.exception.CustomerNotFoundException
import com.customer.mapper.CustomerMapper
import com.customer.repository.CustomerRepository
import com.customer.util.Log
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CustomerService(

    private val customerRepository: CustomerRepository,
    private val customerMapper: CustomerMapper

) {
    companion object : Log()


    @Transactional
    fun create(customerRequest: CustomerRequest): Customer {
        return Optional.of(customerRequest)
            .map(customerMapper::toEntity)
            .map(customerRepository::save)
            .get()
    }


    fun findById(id: UUID): Customer {
        return this.customerRepository.findById(id)
            .orElseThrow {
                logger.info("[CustomerService][findById]: Cliente não encontrado. Id: $id")
                CustomerNotFoundException("Cliente não encontrado. Id: $id")
            }
    }


    fun findByEmail(email: String): Customer {
        return this.customerRepository.findByEmail(email)
            .orElseThrow {
                logger.info("[CustomerService][findByEmail]: Cliente não encontrado.  Email: $email")
                CustomerNotFoundException("Cliente não encontrado. Email: $email")
            }
    }
}