package com.github.brunodmartins.opengymapi.core.domain.dto.api

data class TrainingCreationRequest(
    val type: String,
    val exercises: List<TrainingExerciseRequest>,
) {
    companion object {

        data class TrainingExerciseRequest(
            val exerciseId: Long,
            val reps: Long,
            val sets: Long
        )
    }
}