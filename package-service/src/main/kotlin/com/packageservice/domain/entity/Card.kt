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

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "card_benefit",
        joinColumns = [JoinColumn(name = "cards_id")],
        inverseJoinColumns = [JoinColumn(name = "benefits_id")]
    )
    var benefits: MutableSet<Benefit> = mutableSetOf(),

    @CreationTimestamp
    var createAt: LocalDateTime? = null,

    @UpdateTimestamp
    var updatedAt: LocalDateTime? = null
)