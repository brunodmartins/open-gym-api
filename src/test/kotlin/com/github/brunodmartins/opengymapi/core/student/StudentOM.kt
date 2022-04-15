package com.github.brunodmartins.opengymapi.core.student

import com.github.brunodmartins.opengymapi.core.domain.Student

class StudentOM {

    companion object {
        fun student() = Student(name = "Bruno", age = 27, weight = 82.5f)
    }
}