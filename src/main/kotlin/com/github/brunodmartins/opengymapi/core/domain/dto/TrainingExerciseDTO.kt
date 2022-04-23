package com.github.brunodmartins.opengymapi.core.domain.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.Min

@ApiModel
data class TrainingExerciseDTO(
    @field:ApiModelProperty
    @field:Min(value = 1, message = "exerciseId is required")
    val exerciseId: Long,
    @field:ApiModelProperty
    @field:Min(value = 1, message = "minimum reps required is 1")
    val reps: Int,
    @field:ApiModelProperty
    @field:Min(value = 1, message = "Minimum sets required is 1")
    val sets: Int
)