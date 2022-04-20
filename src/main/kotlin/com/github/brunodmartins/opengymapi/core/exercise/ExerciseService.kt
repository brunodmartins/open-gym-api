package com.github.brunodmartins.opengymapi.core.exercise

import com.github.brunodmartins.opengymapi.core.domain.Exercise
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ExerciseService {

    @Autowired
    lateinit var repository: ExerciseRepository

    fun save(exercise: Exercise) {
        repository.save(exercise)
    }

    fun get(id: Long) = repository.getById(id)
}