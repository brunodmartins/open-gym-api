package com.github.brunodmartins.opengymapi.core.domain

import java.time.LocalDate
import javax.persistence.*

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
    val training: List<Training>,
    val presence: Int = 0,
    val lastPresence: LocalDate?,

    @OneToOne
    val lastTraining: Training?
)
