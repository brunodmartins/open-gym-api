package com.github.brunodmartins.opengymapi.core.prescription

import com.github.brunodmartins.opengymapi.BaseControllerTest
import com.github.brunodmartins.opengymapi.core.domain.dto.TrainingExerciseDTO
import com.github.brunodmartins.opengymapi.core.domain.dto.api.PrescriptionCreationRequest
import com.github.brunodmartins.opengymapi.core.domain.dto.api.TrainingCreationRequest
import com.github.brunodmartins.opengymapi.core.prescription.PrescriptionOM.Companion.emptyPrescription
import com.github.brunodmartins.opengymapi.core.student.StudentService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.util.ReflectionTestUtils
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import javax.persistence.EntityNotFoundException

@SpringBootTest
@ExtendWith(MockitoExtension::class)
class PrescriptionControllerTest : BaseControllerTest(){

    @Autowired
    lateinit var controller: PrescriptionController

    @Mock
    lateinit var prescriptionService: PrescriptionService

    @BeforeEach
    fun setupMock() {
        ReflectionTestUtils.setField(controller, "prescriptionService", prescriptionService)
    }

    @Test
    @DisplayName("POST - /prescription - 201")
    fun createPrescriptionOK() {
        val prescription = emptyPrescription()
        val uri = "/prescription"
        val request = PrescriptionCreationRequest(1, prescription.beginDate, prescription.endDate)
        mvc.perform(
            MockMvcRequestBuilders
            .post(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(request))
        ).andExpect(MockMvcResultMatchers.status().isCreated)
        verify(prescriptionService, atLeastOnce()).createPrescription(1, prescription.beginDate, prescription.endDate)
    }

    @ParameterizedTest
    @DisplayName("POST - /prescription - 400")
    @ValueSource(strings = [
        """{}""",
        """{"studentId": 1}""",
        """{"studentId": 1, "beginDate": "2022-04-22"}""",
        """{"studentId": 1, "endDate": "2022-07-22"}""",
        """{"beginDate": "2022-04-22", "endDate": "2022-07-22"}""",
    ])
    fun createPrescriptionFails(body: String) {
        val uri = "/prescription"
        mvc.perform(
            MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    @DisplayName("POST - /prescription/1/training - 201")
    fun addTrainingOK() {
        val prescription = emptyPrescription()
        val prescriptionId = 1L
        val uri = "/prescription/$prescriptionId/training"
        val request = TrainingCreationRequest("A", exercises = listOf(TrainingExerciseDTO(1, 20, 3)))
        `when`(prescriptionService.get(prescriptionId)).thenReturn(prescription)
        mvc.perform(
            MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request))
        ).andExpect(MockMvcResultMatchers.status().isCreated)
        verify(prescriptionService, atLeastOnce()).addTraining(prescription, request.type, request.exercises)
    }

    @Test
    @DisplayName("POST - /prescription/1/training - 404 - Prescription not found")
    fun addTrainingFailsNotFound() {
        val prescriptionId = 1L
        val uri = "/prescription/$prescriptionId/training"
        val request = TrainingCreationRequest("A", exercises = listOf(TrainingExerciseDTO(1, 20, 3)))
        `when`(prescriptionService.get(prescriptionId)).thenThrow(EntityNotFoundException())
        mvc.perform(
            MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request))
        ).andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @ParameterizedTest
    @DisplayName("POST - /prescription/1/training - 400")
    @ValueSource(strings = [
        """{}""",
        """{"type": "A"}""",
        """{"type": "A", "exercises": []}""",
        """{"type": "A", "exercises": [{"exerciseId":0}]}""",
        """{"type": "A", "exercises": [{"exerciseId":0, "reps": 0}]}""",
        """{"type": "A", "exercises": [{"exerciseId":0, "reps": 0, "sets":0}]}""",
        """{"type": "A", "exercises": [{"exerciseId":1, "reps": 0, "sets":0}]}""",
        """{"type": "A", "exercises": [{"exerciseId":1, "reps": 1, "sets":0}]}""",
        """{"type": "A", "exercises": [{"exerciseId":1, "reps": 0, "sets":1}]}""",
    ])
    fun addTrainingFailsBadRequest(content: String) {
        val prescriptionId = 1L
        val uri = "/prescription/$prescriptionId/training"
       mvc.perform(
            MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
    }
}