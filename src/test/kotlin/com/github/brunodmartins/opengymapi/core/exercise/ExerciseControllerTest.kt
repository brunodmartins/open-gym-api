package com.github.brunodmartins.opengymapi.core.exercise

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.brunodmartins.opengymapi.BaseControllerTest
import com.github.brunodmartins.opengymapi.core.exercise.ExerciseOM.Companion.exercise
import com.github.brunodmartins.opengymapi.core.student.StudentOM
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.util.ReflectionTestUtils
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@ExtendWith(MockitoExtension::class)
class ExerciseControllerTest : BaseControllerTest() {

    @Autowired
    lateinit var controller: ExerciseController

    @Mock
    lateinit var service: ExerciseService

    @BeforeEach
    fun setupMock() {
        ReflectionTestUtils.setField(controller, "service", service)
    }

    @Test
    @DisplayName("POST - /exercise - 201")
    fun saveExerciseOK(){
        val uri = "/exercise"
        mvc.perform(
            MockMvcRequestBuilders
            .post(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(exercise()))
        ).andExpect(MockMvcResultMatchers.status().isCreated)
        Mockito.verify(service, Mockito.atLeastOnce()).save(exercise())
    }

    @ParameterizedTest
    @DisplayName("POST - /exercise - 400")
    @ValueSource(strings = [
        """{}""",
        """{"name": "myName"}""",
        """{"name": "myName", "description": "desc"}""",
        """{"name": "myName", "description": "desc", "bodyParts": [] }""",
        """{"name": "myName", "description": "desc", "bodyParts": ["x"] }""",
    ])
    fun saveExerciseBadRequest(body: String) {
        val uri = "/exercise"
        mvc.perform(MockMvcRequestBuilders
            .post(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    @DisplayName("GET - /exercise/1 - 200")
    fun getExerciseOK(){
        val uri = "/exercise/1"
        Mockito.`when`(service.get(1)).thenReturn(exercise())
        mvc.perform(MockMvcRequestBuilders
            .get(uri)
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(toJson(exercise())))
    }
}