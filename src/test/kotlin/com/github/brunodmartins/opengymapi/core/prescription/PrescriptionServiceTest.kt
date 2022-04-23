package com.github.brunodmartins.opengymapi.core.prescription

import com.github.brunodmartins.opengymapi.core.domain.Prescription
import com.github.brunodmartins.opengymapi.core.domain.dto.TrainingExerciseDTO
import com.github.brunodmartins.opengymapi.core.exercise.ExerciseOM.Companion.exercise
import com.github.brunodmartins.opengymapi.core.exercise.ExerciseService
import com.github.brunodmartins.opengymapi.core.prescription.PrescriptionOM.Companion.emptyPrescription
import com.github.brunodmartins.opengymapi.core.prescription.PrescriptionOM.Companion.fullPrescription
import com.github.brunodmartins.opengymapi.core.student.StudentService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.context.SpringBootTest
import javax.persistence.EntityNotFoundException

@SpringBootTest
@ExtendWith(MockitoExtension::class)
class PrescriptionServiceTest {

    @Mock
    lateinit var repository: PrescriptionRepository

    @Mock
    lateinit var studentService: StudentService

    @Mock
    lateinit var exerciseService: ExerciseService

    @InjectMocks
    lateinit var service: PrescriptionService

    @Test
    @DisplayName("Create Prescription success")
    fun createPrescription() {
        val prescription = emptyPrescription()
        val studentId = prescription.student.id
        `when`(studentService.get(studentId)).thenReturn(prescription.student)
        `when`(repository.save(prescription)).thenReturn(prescription)
        val prescriptionSaved = service.createPrescription(studentId, prescription.beginDate, prescription.endDate)
        assertEquals(prescription, prescriptionSaved)
    }

    @Test
    @DisplayName("Create Prescription fails")
    fun createPrescriptionFails() {
        val prescription = emptyPrescription()
        val studentId = prescription.student.id
        `when`(studentService.get(studentId)).thenReturn(prescription.student)
        `when`(repository.save(prescription)).thenThrow(RuntimeException())
        assertThrows<RuntimeException> { service.createPrescription(studentId, prescription.beginDate, prescription.endDate) }
    }

    @Test
    @DisplayName("Create Prescription fails by student not found")
    fun createPrescriptionFailsByStudentNotFound() {
        val prescription = emptyPrescription()
        val studentId = prescription.student.id
        `when`(studentService.get(studentId)).thenThrow(EntityNotFoundException())
        assertThrows<EntityNotFoundException> { service.createPrescription(studentId, prescription.beginDate, prescription.endDate) }
    }

    @Test
    @DisplayName("Add new training")
    fun addTraining() {
        val type = "A"
        val exerciseDTO = TrainingExerciseDTO(exercise().id, 20, 3)
        assertEquals(0, emptyPrescription().training.size)
        `when`(exerciseService.get(exercise().id)).thenReturn(exercise())
        `when`(repository.save(ArgumentMatchers.any())).thenAnswer {
            it.getArgument<Prescription>(0)
        }
        val prescription = service.addTraining(emptyPrescription(), type, listOf(exerciseDTO))
        assertEquals(1, prescription.training.size)
        assertEquals(type, prescription.training[0].type)
        assertEquals(20, prescription.training[0].exercises[0].reps)
        assertEquals(3, prescription.training[0].exercises[0].sets)
    }

    @Test
    @DisplayName("Add new training fails")
    fun addTrainingFail() {
        val type = "A"
        val exerciseDTO = TrainingExerciseDTO(exercise().id, 20, 3)
        assertEquals(0, emptyPrescription().training.size)
        `when`(exerciseService.get(exercise().id)).thenReturn(exercise())
        `when`(repository.save(ArgumentMatchers.any())).thenThrow(RuntimeException())
        assertThrows<RuntimeException> { service.addTraining(emptyPrescription(), type, listOf(exerciseDTO)) }
    }

    @Test
    @DisplayName("Add new training fails by exercise not found")
    fun addTrainingFailByExercise() {
        val type = "A"
        val exerciseDTO = TrainingExerciseDTO(exercise().id, 20, 3)
        assertEquals(0, emptyPrescription().training.size)
        `when`(exerciseService.get(exercise().id)).thenThrow(EntityNotFoundException())
        assertThrows<EntityNotFoundException> { service.addTraining(emptyPrescription(), type, listOf(exerciseDTO)) }
    }

    @Test
    @DisplayName("Get prescription")
    fun getPrescription() {
        val prescription = fullPrescription()
        `when`(repository.getById(prescription.id)).thenReturn(prescription)
        assertEquals(prescription, service.get(prescription.id))
    }

    @Test
    @DisplayName("Get prescription fails")
    fun getPrescriptionError() {
        val prescription = fullPrescription()
        `when`(repository.getById(prescription.id)).thenThrow(RuntimeException())
        assertThrows<RuntimeException>{ service.get(prescription.id) }
    }
}