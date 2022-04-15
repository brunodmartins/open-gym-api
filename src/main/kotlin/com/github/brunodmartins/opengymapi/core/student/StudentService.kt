package com.github.brunodmartins.opengymapi.core.student

import com.github.brunodmartins.opengymapi.core.domain.Student
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StudentService {

    @Autowired
    lateinit var repository: StudentRepository

    fun save(student: Student) {
        repository.save(student)
    }
}