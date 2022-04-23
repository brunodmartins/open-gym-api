package com.github.brunodmartins.opengymapi.core.domain.dto.api

import com.github.brunodmartins.opengymapi.core.domain.Prescription
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import javax.validation.constraints.Min

@ApiModel
data class PrescriptionCreationRequest (

    @field:ApiModelProperty("studentId")
    @field:Min(value = 1, message = "Student ID is required")
    val studentId: Long,

    @field:ApiModelProperty("beginDate")
    val beginDate: LocalDate,

    @field:ApiModelProperty("endDate")
    val endDate: LocalDate,
)