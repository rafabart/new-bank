package com.customer.mapper

import com.customer.domain.entity.Customer
import com.customer.domain.request.CustomerRequest
import com.customer.domain.response.CustomerResponse
import org.springframework.stereotype.Component

@Component
class CustomerMapper {


    fun toEntity(customerRequest: CustomerRequest): Customer {
        return Customer(
            email = customerRequest.email,
            password = customerRequest.password,
            name = customerRequest.name,
            cnpj = customerRequest.cnpj,
            cpf = customerRequest.cpf
        )
    }


    fun toResponse(customer: Customer): CustomerResponse {
        return CustomerResponse(
            id = customer.id!!,
            email = customer.email,
            name = customer.name,
            cnpj = customer.cnpj,
            cpf = customer.cpf,
            createAt = customer.createAt!!,
            updatedAt = customer.updatedAt!!
        )
    }
}
