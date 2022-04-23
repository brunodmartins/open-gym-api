package com.github.brunodmartins.opengymapi.core.prescription

import com.github.brunodmartins.opengymapi.BaseControllerTest
import com.github.brunodmartins.opengymapi.core.domain.dto.api.PrescriptionCreationRequest
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

    @Mock
    lateinit var studentService: StudentService

    @BeforeEach
    fun setupMock() {
        ReflectionTestUtils.setField(controller, "prescriptionService", prescriptionService)
        ReflectionTestUtils.setField(controller, "studentService", studentService)
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

    @Test
    @DisplayName("POST - /prescription - 404 - Student Not found")
    @Disabled
    fun createPrescriptionFailsByStudent() {
        val prescription = emptyPrescription()
        val uri = "/prescription"
        val request = PrescriptionCreationRequest(1, prescription.beginDate, prescription.endDate)
        `when`(studentService.get(1)).thenThrow(EntityNotFoundException())
        mvc.perform(
            MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request))
        ).andExpect(MockMvcResultMatchers.status().isNotFound)
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
}