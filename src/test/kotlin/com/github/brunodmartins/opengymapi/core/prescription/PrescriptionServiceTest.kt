package com.github.brunodmartins.opengymapi.core.prescription

import com.github.brunodmartins.opengymapi.core.domain.Prescription
import com.github.brunodmartins.opengymapi.core.prescription.PrescriptionOM.Companion.emptyPrescription
import com.github.brunodmartins.opengymapi.core.prescription.PrescriptionOM.Companion.fullPrescription
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.internal.verification.VerificationModeFactory.atLeastOnce
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.context.SpringBootTest
import java.util.stream.Stream

@SpringBootTest
@ExtendWith(MockitoExtension::class)
class PrescriptionServiceTest {

    @Mock
    lateinit var repository: PrescriptionRepository

    @InjectMocks
    lateinit var service: PrescriptionService


    companion object {
        @JvmStatic
        fun savePrescriptionInput(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(emptyPrescription()),
                Arguments.of(fullPrescription())
            )
        }
    }

    @ParameterizedTest
    @DisplayName("Save prescription successfully")
    @MethodSource("savePrescriptionInput")
    fun savePrescription(prescription: Prescription) {
        service.save(prescription)
        verify(repository, atLeastOnce()).save(ArgumentMatchers.eq(prescription))
    }

    @Test
    @DisplayName("Save prescription fails")
    fun savePrescriptionError() {
        `when`(repository.save(emptyPrescription())).thenThrow(RuntimeException())
        assertThrows<RuntimeException> { service.save(emptyPrescription())  }
    }

    @Test
    @DisplayName("Get prescription")
    fun getPrescription() {
        val prescription = fullPrescription()
        `when`(repository.getById(prescription.id)).thenReturn(prescription)
        Assertions.assertEquals(prescription, service.get(prescription.id))
    }

    @Test
    @DisplayName("Get prescription fails")
    fun getPrescriptionError() {
        val prescription = fullPrescription()
        `when`(repository.getById(prescription.id)).thenThrow(RuntimeException())
        assertThrows<RuntimeException>{ service.get(prescription.id) }
    }
}