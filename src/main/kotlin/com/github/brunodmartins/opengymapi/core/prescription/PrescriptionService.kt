package com.github.brunodmartins.opengymapi.core.prescription

import com.github.brunodmartins.opengymapi.core.domain.Prescription
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PrescriptionService {

    @Autowired
    lateinit var repository: PrescriptionRepository

    fun save(prescription: Prescription) {
        repository.save(prescription)
    }

    fun get(id: Long): Prescription {
        return repository.getById(id)
    }
}