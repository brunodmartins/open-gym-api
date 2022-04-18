package com.github.brunodmartins.opengymapi.core.domain.dto.storage

import com.github.brunodmartins.opengymapi.core.domain.Student
import javax.persistence.*

@Entity
@Table(name="student")
data class StudentRecord  (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0L, val name: String, val age: Int, val weight: Float) {

    fun toStudent() = Student(id,name,age,weight)

    companion object {
        fun fromStudent(student: Student) = StudentRecord(student.id, student.name, student.age, student.weight)
    }

}