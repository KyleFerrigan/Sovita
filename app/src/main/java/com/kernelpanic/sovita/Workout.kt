package com.kernelpanic.sovita

data class Workout (val name: String, val exercises: ArrayList<Exercise>) {
    private  lateinit var _workoutName: String
    private lateinit var _exercises: ArrayList<Exercise>

    fun addExercise(exercise: Exercise) {
        _exercises.add(exercise)
    }

    fun removeExercise(exercise: Exercise){
        _exercises.remove(exercise)
    }

    fun setName(name: String){
        _workoutName = name
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
}