package com.github.brunodmartins.opengymapi.core.exercise

import com.github.brunodmartins.opengymapi.core.exercise.ExerciseOM.Companion.exercise
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@ExtendWith(MockitoExtension::class)
class ExerciseServiceTest {

    @Mock
    lateinit var repository: ExerciseRepository

    @InjectMocks
    lateinit var service: ExerciseService

    @Test
    @DisplayName("Save exercise successfully")
    fun saveExercise() {
        service.save(exercise())
        verify(repository, atLeastOnce()).save(ArgumentMatchers.eq(exercise()))
    }

    @Test
    @DisplayName("Save exercise fails")
    fun saveExerciseError() {
        `when`(repository.save(exercise())).thenThrow(RuntimeException())
        assertThrows<RuntimeException> { service.save(exercise()) }
    }

    @Test
    @DisplayName("Get exercise")
    fun getExercise() {
        val exercise = exercise()
        `when`(repository.getById(exercise.id)).thenReturn(exercise())
        Assertions.assertEquals(exercise, service.get(exercise.id))
    }

    @Test
    @DisplayName("Get exercise fails")
    fun getExerciseError() {
        val exercise = exercise()
        `when`(repository.getById(exercise.id)).thenThrow(RuntimeException())
        assertThrows<RuntimeException>{ service.get(exercise.id) }
    }
}