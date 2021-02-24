package com.kernelpanic.sovita

data class Workout (val name: String) {
    val exercises = arrayListOf<Exercise>()

    fun addExercise(exercise: Exercise) {
        exercises.add(exercise)
    }

    //Add the rest of the functions
}