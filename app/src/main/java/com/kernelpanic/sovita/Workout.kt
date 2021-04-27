package com.kernelpanic.sovita

import java.io.Serializable

data class Workout (val name: String, val _exercises: ArrayList<Exercise>, var workoutID: Int): Serializable {
    private var _workoutName: String = name
    private var _workoutID : Int = workoutID
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

    fun getExerciseTime(index:Int): Int {
        return _exercises[index].getTime()
    }
    fun getNumExercises() : Int {
        return _exercises.size
    }
    fun setExerciseReps(index:Int, reps:Int) {
        _exercises[index].setReps(reps)
    }
    fun setExerciseTime(index:Int, time:Int) {
        _exercises[index].setTime(time)
    }

    fun getWorkoutID_() : Int {
        return _workoutID
    }

    fun getExerciseReps() : String {
     //Get string list of reps for each exercise
        var reps = ""
        for (i in 0.._exercises.size-1) {
            if(i == _exercises.size-1) {
                reps = reps + _exercises[i].getReps()
            } else {
                reps = reps + _exercises[i].getReps() + ","
            }
        }
        println("Reps:" + reps)
        return reps
    }
    fun getExerciseTimes() : String {
        //Get string list of reps for each exercise
        var time = ""
        for (i in 0.._exercises.size-1) {
            if(i == _exercises.size-1) {
                time = time + _exercises[i].getTime()
            } else {
                time = time + _exercises[i].getTime() + ","
            }
        }
        println("Time:" + time)
        return time
    }
    fun getExerciseIDs() : String {
        //Get string list of reps for each exercise
        var id = ""
        for (i in 0.._exercises.size-1) {
            if(i == _exercises.size-1) {
                id = id + _exercises[i].getiD()
            } else {
                id = id + _exercises[i].getiD() + ","
            }
        }
        println("ID:" + id)
        return id
    }
    fun getExerciseImage(index : Int) : String {
        return _exercises[index].getImageLink()
    }
}