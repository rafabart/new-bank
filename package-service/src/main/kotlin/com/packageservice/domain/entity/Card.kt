package com.packageservice.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Card(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(unique = true)
    val cardNumber: String,

    @ManyToMany
    //TODO: Corrigir essa relação. No projeto do e-commerce tem algo parecido com essa relação
    var benefits: Set<Benefit>? = HashSet(),

    @CreationTimestamp
    var createAt: LocalDateTime? = null,

    @UpdateTimestamp
    var updatedAt: LocalDateTime? = null

)
