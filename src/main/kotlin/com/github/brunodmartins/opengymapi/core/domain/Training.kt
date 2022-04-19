package com.github.brunodmartins.opengymapi.core.domain

import javax.persistence.*

@Entity
data class Training(
    @Id
    @GeneratedValue
    val id: Long = 0L,
    val type: String,

    @OneToMany(mappedBy="training")
    val exercises: List<TrainingExercise>,
) {

    companion object{
        @Entity
        @Table(name="training_exercise")
        data class TrainingExercise(
            @Id
            @GeneratedValue
            val id: Long = 0L,
            @ManyToOne
            val training: Training,
            @OneToOne
            val exercise: Exercise,
            val sets: Int,
            val reps: Int,
        )
    }
}

