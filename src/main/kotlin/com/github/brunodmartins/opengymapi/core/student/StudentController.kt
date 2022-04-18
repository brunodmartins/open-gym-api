package com.github.brunodmartins.opengymapi.core.student

import com.github.brunodmartins.opengymapi.core.domain.Student
import com.github.brunodmartins.opengymapi.core.student.dto.api.StudentRequest
import com.github.brunodmartins.opengymapi.core.student.dto.api.StudentRequestConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/student")
class StudentController {

    @Autowired
    lateinit var service: StudentService

    @Autowired
    lateinit var converter: StudentRequestConverter

    @PostMapping
    fun postStudent(@Valid @RequestBody request: StudentRequest): ResponseEntity<Student> {
        val student = converter.convert(request)
        service.save(student)
        return ResponseEntity<Student>(student, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getStudent(@PathVariable id: Long): ResponseEntity<Student> {
        return ResponseEntity<Student>(service.get(id), HttpStatus.OK)
    }

}