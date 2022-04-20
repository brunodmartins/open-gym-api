package com.github.brunodmartins.opengymapi.core.exercise

import com.github.brunodmartins.opengymapi.core.domain.Exercise
import com.github.brunodmartins.opengymapi.core.domain.dto.api.ExerciseRequest
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Api(tags = ["Exercise API"])
@RestController
@RequestMapping("/exercise")
class ExerciseController {

    @Autowired
    lateinit var service: ExerciseService

    @ApiOperation("Create exercise")
    @PostMapping
    fun postExercise(@Valid @RequestBody request: ExerciseRequest): ResponseEntity<Exercise> {
        val exercise = request.toExercise()
        service.save(exercise)
        return ResponseEntity<Exercise>(exercise, HttpStatus.CREATED)
    }

    @ApiOperation("Get a exercise by ID")
    @GetMapping("/{id}")
    fun getExercise(@PathVariable id: Long): ResponseEntity<Exercise> {
        return ResponseEntity<Exercise>(service.get(id), HttpStatus.OK)
    }
}