package com.customer.repository

import com.customer.domain.entity.Customer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CustomerRepository : JpaRepository<Customer, UUID> {

    fun findAllByEmail(email: String?, pageable: Pageable): Page<Customer>
}