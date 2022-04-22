package com.github.brunodmartins.opengymapi.core.prescription

import com.github.brunodmartins.opengymapi.core.domain.Prescription
import com.github.brunodmartins.opengymapi.core.domain.Training
import com.github.brunodmartins.opengymapi.core.exercise.ExerciseOM.Companion.exercise
import com.github.brunodmartins.opengymapi.core.student.StudentOM.Companion.student
import java.time.LocalDate

class PrescriptionOM {

    companion object {
        fun emptyPrescription(): Prescription {
            return Prescription(
                student = student(),
                beginDate = LocalDate.now(),
                endDate = LocalDate.now().plusMonths(2)
            )
        }

        fun fullPrescription(): Prescription {
            val trainingExercise = Training.Companion.TrainingExercise(
                exercise = exercise(),
                sets = 3,
                reps = 20,
            )
            val trainingA = Training(
                type = "A",
                exercises = listOf(trainingExercise)
            )
            val trainingB = Training(
                type = "B",
                exercises = listOf(trainingExercise)
            )
            return Prescription(
                student = student(),
                beginDate = LocalDate.now(),
                endDate = LocalDate.now().plusMonths(2),
                presence = 2,
                training = listOf(trainingA, trainingB),
                lastTraining = trainingB,
                lastPresence = LocalDate.now()
            )
        }
    }
}