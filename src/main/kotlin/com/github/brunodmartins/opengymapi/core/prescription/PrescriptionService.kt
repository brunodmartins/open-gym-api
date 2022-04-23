package com.github.brunodmartins.opengymapi.core.prescription

import com.github.brunodmartins.opengymapi.core.domain.Prescription
import com.github.brunodmartins.opengymapi.core.domain.Training
import com.github.brunodmartins.opengymapi.core.domain.dto.TrainingExerciseDTO
import com.github.brunodmartins.opengymapi.core.exercise.ExerciseService
import com.github.brunodmartins.opengymapi.core.student.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class PrescriptionService {

    @Autowired
    lateinit var repository: PrescriptionRepository

    @Autowired
    lateinit var studentService: StudentService

    @Autowired
    lateinit var exerciseService: ExerciseService

    fun createPrescription(studentId: Long, beginDate: LocalDate, endDate: LocalDate) : Prescription {
        val student = studentService.get(studentId)
        val prescription = Prescription(0, student, beginDate, endDate)
        return repository.save(prescription)
    }

    fun addTraining(prescription: Prescription, type: String, exercises: List<TrainingExerciseDTO>): Prescription {
        val training = Training(id=0, type, exercises.map {
            Training.Companion.TrainingExercise(0, exerciseService.get(it.exerciseId), it.sets, it.reps)
        })
        prescription.training.add(training)
        return repository.save(prescription)
    }

    fun save(prescription: Prescription) {
        repository.save(prescription)
    }

    fun get(id: Long): Prescription {
        return repository.getById(id)
    }
}