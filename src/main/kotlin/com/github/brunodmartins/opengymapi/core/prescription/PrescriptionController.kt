package com.github.brunodmartins.opengymapi.core.prescription

import com.github.brunodmartins.opengymapi.core.domain.Prescription
import com.github.brunodmartins.opengymapi.core.domain.Training
import com.github.brunodmartins.opengymapi.core.domain.dto.api.PrescriptionCreationRequest
import com.github.brunodmartins.opengymapi.core.domain.dto.api.TrainingCreationRequest
import com.github.brunodmartins.opengymapi.core.exercise.ExerciseService
import com.github.brunodmartins.opengymapi.core.student.StudentService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@Api(tags = ["Prescription API"])
@RestController
@RequestMapping("/prescription")
class PrescriptionController {

    @Autowired
    lateinit var studentService: StudentService

    @Autowired
    lateinit var prescriptionService: PrescriptionService

    @Autowired
    lateinit var exerciseService: ExerciseService

    @ApiOperation("Create Prescription")
    @PostMapping
    fun postCreatePrescription(@Valid @RequestBody request: PrescriptionCreationRequest): ResponseEntity<Prescription> {
        return ResponseEntity<Prescription>(prescriptionService.createPrescription(
            studentId = request.studentId,
            beginDate = request.beginDate,
            endDate = request.endDate
        ), HttpStatus.CREATED)
    }


    @ApiOperation("Add Training to Prescription")
    @PostMapping("/{id}/training")
    fun putAddTraining(@PathVariable id: Long, @Valid @RequestBody request: TrainingCreationRequest): ResponseEntity<Prescription>{
        val prescription = prescriptionService.get(id)
        return ResponseEntity<Prescription>(prescriptionService.addTraining(prescription, request.type, request.exercises), HttpStatus.CREATED)
    }
}