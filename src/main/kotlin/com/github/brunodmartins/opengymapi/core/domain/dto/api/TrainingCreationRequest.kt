package com.github.brunodmartins.opengymapi.core.domain.dto.api

import com.github.brunodmartins.opengymapi.core.domain.dto.TrainingExerciseDTO
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.Valid
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

@ApiModel
data class TrainingCreationRequest(

    @field:ApiModelProperty
    @field:NotEmpty(message = "Training type is required")
    val type: String,

    @field:ApiModelProperty
    @field:Size(min=1)
    @field:Valid
    val exercises: List<TrainingExerciseDTO>,
) {
}