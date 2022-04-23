package com.github.brunodmartins.opengymapi.core.domain.dto.api

import com.github.brunodmartins.opengymapi.core.domain.dto.TrainingExerciseDTO

data class TrainingCreationRequest(
    val type: String,
    val exercises: List<TrainingExerciseDTO>,
) {
}