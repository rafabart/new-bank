package com.card.domain.document

import com.card.domain.enums.CardStatus
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
data class Card(

    @Id
    val id: String = UUID.randomUUID().toString(),

    @Indexed
    val customerId: String,

    @Indexed(unique = true)
    val cardNumber: String,

    var status: CardStatus = CardStatus.NEW,

    val createAt: LocalDateTime = LocalDateTime.now(),

    var updatedAt: LocalDateTime = createAt
)
