package com.github.brunodmartins.opengymapi.core.exercise

import com.github.brunodmartins.opengymapi.core.domain.BodyPart
import com.github.brunodmartins.opengymapi.core.domain.Exercise

class ExerciseOM {

    companion object {
        fun exercise(): Exercise {
            return Exercise(name="Test", description = "Description", bodyParts = setOf(BodyPart.ABDOMINAL, BodyPart.TIBIALIS))
        }
    }
}