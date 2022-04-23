package com.github.brunodmartins.opengymapi.core.domain

import java.time.LocalDate
import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList

@Entity
data class Prescription(
    @Id
    @GeneratedValue
    val id: Long = 0L,

    @OneToOne
    val student: Student,

    val beginDate: LocalDate,

    val endDate: LocalDate,

    @OneToMany
    val training: MutableList<Training> = mutableListOf(),

    val presence: Int = 0,

    val lastPresence: LocalDate? = null,

    @OneToOne
    val lastTraining: Training? = null
)
