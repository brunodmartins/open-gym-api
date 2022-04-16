package com.github.brunodmartins.opengymapi.core.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

@Entity
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0L,

    @field:NotEmpty(message = "The name is required.")
    @field:Size(min = 2, max = 100, message = "The length of full name must be between 2 and 100 characters.")
    val name: String,

    @field:Min(value = 1, message = "Minimum age required is 1")
    val age: Int,

    @field:Min(value = 1, message = "Minimum weight required is 1")
    val weight: Float)
