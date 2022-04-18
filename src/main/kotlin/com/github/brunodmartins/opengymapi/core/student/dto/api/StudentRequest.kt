package com.github.brunodmartins.opengymapi.core.student.dto.api

import com.github.brunodmartins.opengymapi.core.domain.Student
import com.googlecode.jmapper.annotations.JMap
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

@ApiModel
data class StudentRequest (

    @field:ApiModelProperty("name")
    @field:NotEmpty(message = "The name is required.")
    @field:Size(min = 2, max = 100, message = "The length of full name must be between 2 and 100 characters.")
    val name: String,

    @field:ApiModelProperty("age")
    @field:Min(value = 1, message = "Minimum age required is 1")
    val age: Int,

    @field:ApiModelProperty("weight")
    @field:Min(value = 1, message = "Minimum weight required is 1")
    val weight: Float,
)