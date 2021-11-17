package com.customer.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Customer(

    @Id
    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    var id: UUID? = null,

    @Column(unique = true)
    val email: String,

    val password: String,

    var name: String,

    val cnpj: String? = null,

    val cpf: String? = null,

    @CreationTimestamp
    var createAt: LocalDateTime? = null,

    @UpdateTimestamp
    var updatedAt: LocalDateTime? = null,
)
