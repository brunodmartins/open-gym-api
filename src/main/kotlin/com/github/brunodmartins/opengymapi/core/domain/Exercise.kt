package com.github.brunodmartins.opengymapi.core.domain

import javax.persistence.*

@Entity
data class Exercise(
    @Id
    @GeneratedValue
    var id: Long = 0L, val name: String, val description: String,

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    val bodyParts: Set<BodyPart>)
