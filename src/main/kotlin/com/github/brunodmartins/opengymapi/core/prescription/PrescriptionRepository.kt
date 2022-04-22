package com.github.brunodmartins.opengymapi.core.prescription

import com.github.brunodmartins.opengymapi.core.domain.Prescription
import org.springframework.data.jpa.repository.JpaRepository

interface PrescriptionRepository : JpaRepository<Prescription, Long>