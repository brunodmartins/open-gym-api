package com.github.brunodmartins.opengymapi.core.student

import com.github.brunodmartins.opengymapi.core.student.StudentOM.Companion.student
import org.junit.jupiter.api.Assertions.assertEquals
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
class StudentServiceTest {

    @Mock
    lateinit var repository: StudentRepository

    @InjectMocks
    lateinit var service: StudentService

    @Test
    @DisplayName("Save student successfully")
    fun saveStudent() {
        service.save(student())
        verify(repository, atLeastOnce()).save(ArgumentMatchers.eq(student()))
    }

    @Test
    @DisplayName("Save student fails")
    fun saveStudentError() {
        `when`(repository.save(student())).thenThrow(RuntimeException())
        assertThrows<RuntimeException>{ service.save(student()) }
    }

    @Test
    @DisplayName("Get student")
    fun getStudent() {
        val student = student()
        `when`(repository.getById(student.id)).thenReturn(student())
        assertEquals(student, service.get(student.id))
    }

    @Test
    @DisplayName("Get student fails")
    fun getStudentError() {
        val student = student()
        `when`(repository.getById(student.id)).thenThrow(RuntimeException())
        assertThrows<RuntimeException>{ service.get(student.id) }
    }

}