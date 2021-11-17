package com.customer.domain.request

data class CustomerRequest(

    val email: String,

    val password: String,

    val name: String,

    val cnpj: String? = null,

    val cpf: String? = null
)
