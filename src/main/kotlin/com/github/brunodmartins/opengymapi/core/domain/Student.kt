package com.github.brunodmartins.opengymapi.core.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Student(
    @Id
    @GeneratedValue
    val id: Long = 0L, val name: String, val age: Int, val weight: Float, val gender: Gender)
