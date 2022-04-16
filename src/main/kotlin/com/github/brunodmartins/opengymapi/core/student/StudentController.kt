package com.github.brunodmartins.opengymapi.core.student

import com.github.brunodmartins.opengymapi.core.domain.Student
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/student")
class StudentController {

    @Autowired
    lateinit var service: StudentService

    @PostMapping
    fun postStudent(@RequestBody student: Student): ResponseEntity<Student> {
        service.save(student)
        return ResponseEntity<Student>(student, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getStudent(@PathVariable id: Long): ResponseEntity<Student> {
        return ResponseEntity<Student>(service.get(id), HttpStatus.OK)
    }

}