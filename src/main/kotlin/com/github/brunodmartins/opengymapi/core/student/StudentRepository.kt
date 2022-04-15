package com.github.brunodmartins.opengymapi.core.student

import com.github.brunodmartins.opengymapi.core.domain.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<Student, Long>