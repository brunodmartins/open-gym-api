package com.github.brunodmartins.opengymapi.core.student

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.brunodmartins.opengymapi.BaseControllerTest
import com.github.brunodmartins.opengymapi.core.domain.Student
import com.github.brunodmartins.opengymapi.core.student.StudentOM.Companion.student
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.util.ReflectionTestUtils
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.lang.RuntimeException

@SpringBootTest
@ExtendWith(MockitoExtension::class)
class StudentControllerTest : BaseControllerTest() {

    @Autowired
    lateinit var controller: StudentController

    @Mock
    lateinit var service: StudentService

    @BeforeEach
    fun setupMock() {
        ReflectionTestUtils.setField(controller, "service", service)
    }

    @Test
    @DisplayName("POST - /student - 200")
    fun saveStudentOK(){
        val uri = "/student"
        mvc.perform(MockMvcRequestBuilders
            .post(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(ObjectMapper().writeValueAsBytes(student()))
        ).andExpect(status().isCreated)
        verify(service, atLeastOnce()).save(student())
    }

    @Test
    @DisplayName("POST - /student - 500")
    fun saveStudentError(){
        val uri = "/student"
        `when`(service.save(student())).thenThrow(RuntimeException())
        mvc.perform(MockMvcRequestBuilders
            .post(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(ObjectMapper().writeValueAsBytes(student()))
        ).andExpect(status().is5xxServerError)
    }
}