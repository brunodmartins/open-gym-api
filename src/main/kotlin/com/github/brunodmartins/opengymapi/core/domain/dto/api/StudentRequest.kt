package com.github.brunodmartins.opengymapi.core.domain.dto.api

import com.github.brunodmartins.opengymapi.core.domain.Gender
import com.github.brunodmartins.opengymapi.core.domain.Student
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Pattern
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

    @field:ApiModelProperty("Gender")
    @field:Pattern(regexp = "[mf]", flags = [Pattern.Flag.CASE_INSENSITIVE])
    val gender: String
){
    fun toStudent() : Student {
        return Student(0, name, age, weight, Gender.valueOf(gender))
    }
}