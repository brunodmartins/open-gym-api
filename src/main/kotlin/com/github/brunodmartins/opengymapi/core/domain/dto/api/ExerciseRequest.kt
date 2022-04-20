package com.github.brunodmartins.opengymapi.core.domain.dto.api

import com.github.brunodmartins.opengymapi.core.domain.BodyPart
import com.github.brunodmartins.opengymapi.core.domain.Exercise
import com.github.brunodmartins.opengymapi.core.domain.Gender
import com.github.brunodmartins.opengymapi.core.domain.Student
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@ApiModel
data class ExerciseRequest (

    @field:ApiModelProperty("name")
    @field:NotEmpty(message = "The name is required.")
    @field:Size(min = 2, max = 100, message = "The length of name must be between 2 and 100 characters.")
    val name: String,

    @field:ApiModelProperty("description")
    @field:NotEmpty(message = "The description is required.")
    @field:Size(min = 2, max = 100, message = "The length of description must be between 2 and 255 characters.")
    val description: String,

    @field:ApiModelProperty("bodyParts")
    @field:Size(min=1)
    val bodyParts: Set<BodyPart>,

){
    fun toExercise() : Exercise {
        return Exercise(0, name, description, bodyParts)
    }
}