package com.kernelpanic.sovita

import java.io.Serializable

data class Workout (val name: String, val _exercises: ArrayList<Exercise>): Serializable {
    private var _workoutName: String = name
    //private var _exercises: ArrayList<Exercise>

    fun addExercise(exercise: Exercise) {
        _exercises.add(exercise)
    }

    fun removeExercise(exercise: Exercise){
        _exercises.remove(exercise)
    }

    fun setName(name: String){
        _workoutName = name
    }
    fun getWorkoutName(): String {
        return _workoutName
    }
    //getters
    fun getExerciseName(index:Int): String {
        return _exercises[index].getName()
    }

    fun getExerciseCatagory(index:Int): String {
        return _exercises[index].getCatagory()
    }

    fun getExerciseDifficulty(index:Int): String {
        return _exercises[index].getDifficulty()
    }

    fun getExerciseReps(index:Int): Int {
        return _exercises[index].getReps()
    }

    fun getExerciseSets(index:Int): Int {
        return _exercises[index].getSets()
    }

    fun getExerciseTime(index:Int): Double {
        return _exercises[index].getTime()
    }
    fun getNumExercises() : Int {
        return _exercises.size
    }
    fun setExerciseReps(index:Int, reps:Int) {
        _exercises[index].setReps(reps)
    }
    fun setExerciseTime(index:Int, time:Double) {
        _exercises[index].setTime(time)
    }
}