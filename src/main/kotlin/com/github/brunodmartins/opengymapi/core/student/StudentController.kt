package com.github.brunodmartins.opengymapi.core.student

import com.github.brunodmartins.opengymapi.core.domain.Student
import com.github.brunodmartins.opengymapi.core.domain.dto.api.StudentRequest
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Api(tags = ["Student API"])
@RestController
@RequestMapping("/student")
class StudentController {

    @Autowired
    lateinit var service: StudentService

    @ApiOperation("Create student")
    @PostMapping
    fun postStudent(@Valid @RequestBody request: StudentRequest): ResponseEntity<Student> {
        val student = request.toStudent()
        service.save(student)
        return ResponseEntity<Student>(student, HttpStatus.CREATED)
    }

    @ApiOperation("Get a student by ID")
    @GetMapping("/{id}")
    fun getStudent(@PathVariable id: Long): ResponseEntity<Student> {
        return ResponseEntity<Student>(service.get(id), HttpStatus.OK)
    }

}