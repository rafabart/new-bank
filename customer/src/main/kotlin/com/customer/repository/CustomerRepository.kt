package com.customer.repository

import com.customer.domain.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CustomerRepository : JpaRepository<Customer, UUID> {

    fun findByEmail(email: String): Optional<Customer>
}