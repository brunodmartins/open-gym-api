package com.github.brunodmartins.opengymapi.core.prescription

import com.github.brunodmartins.opengymapi.core.domain.Prescription
import com.github.brunodmartins.opengymapi.core.domain.dto.api.PrescriptionCreationRequest
import com.github.brunodmartins.opengymapi.core.student.StudentService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Api(tags = ["Prescription API"])
@RestController
@RequestMapping("/prescription")
class PrescriptionController {

    @Autowired
    lateinit var studentService: StudentService

    @Autowired
    lateinit var prescriptionService: PrescriptionService

    @ApiOperation("Create Prescription")
    @PostMapping
    fun postCreatePrescription(@Valid @RequestBody request: PrescriptionCreationRequest): ResponseEntity<Prescription> {
        val student = studentService.get(request.studentId)
        val prescription = Prescription(0, student, request.beginDate, request.endDate)
        prescriptionService.save(prescription)
        return ResponseEntity<Prescription>(prescription, HttpStatus.CREATED)
    }
}