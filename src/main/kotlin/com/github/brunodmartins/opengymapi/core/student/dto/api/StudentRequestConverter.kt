package com.github.brunodmartins.opengymapi.core.student.dto.api

import com.github.brunodmartins.opengymapi.core.domain.Student
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class StudentRequestConverter : Converter<StudentRequest, Student> {
    override fun convert(request: StudentRequest): Student {
        return Student(0, request.name, request.age, request.weight)
    }
}