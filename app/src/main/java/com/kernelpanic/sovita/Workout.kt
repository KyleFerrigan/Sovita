package com.kernelpanic.sovita

data class Workout (val name: String) {
    private  lateinit var _workoutName: String
    private lateinit var _exercises: ArrayList<Exercise>

    //default constructor
    fun Workout(){
        _workoutName = ""
        _exercises = arrayListOf<Exercise>()
    }

    //1 arguement con.
    fun Workout(name:String, exercise: Exercise){
        _workoutName = name
        _exercises = arrayListOf<Exercise>()
        _exercises.add(exercise)
    }

    //2 arguement con.
    fun Workout(name:String){
        _workoutName = name
        _exercises = arrayListOf<Exercise>()
    }

    fun addExercise(exercise: Exercise) {
        _exercises.add(exercise)
    }

    fun removeExercise(exercise: Exercise){
        _exercises.remove(exercise)
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