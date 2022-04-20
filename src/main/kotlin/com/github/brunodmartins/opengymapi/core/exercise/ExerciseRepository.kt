package com.github.brunodmartins.opengymapi.core.exercise

import com.github.brunodmartins.opengymapi.core.domain.Exercise
import org.springframework.data.jpa.repository.JpaRepository

interface ExerciseRepository : JpaRepository<Exercise, Long>