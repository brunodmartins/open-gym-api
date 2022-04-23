package com.github.brunodmartins.opengymapi.core.student

import com.github.brunodmartins.opengymapi.core.domain.Gender
import com.github.brunodmartins.opengymapi.core.domain.Student

class StudentOM {

    companion object {
        fun student() = Student(id = 1, name = "Bruno", age = 27, weight = 82.5f, gender = Gender.M)

        fun newStudent() = Student(name = "Bruno", age = 27, weight = 82.5f, gender = Gender.M)
    }
}