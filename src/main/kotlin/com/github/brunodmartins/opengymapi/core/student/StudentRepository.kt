package com.github.brunodmartins.opengymapi.core.student

import com.github.brunodmartins.opengymapi.core.domain.dto.storage.StudentRecord
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<StudentRecord, Long>