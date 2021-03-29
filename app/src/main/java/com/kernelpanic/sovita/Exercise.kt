package com.kernelpanic.sovita

// Add an image as parameter later on
//This holds the exercise
class Exercise(val _name: String, val _category: String, val _difficulty: String) {
    //From what I found online, there is no constructor, you just set the variables in the header
    //Member variables
    private var _reps: Int = 0
    private var _sets: Int = 0
    private var _time: Double = 0.00
    private var _checked: Boolean = false

    fun setCheck(check: Boolean) {
        _checked = check
    }

    fun isChecked(): Boolean {
        return _checked
    }

    fun setReps(reps: Int) {
        _reps = reps
    }

    fun setSets(sets: Int) {
        _sets = sets
    }

    fun setTime(time: Double) {
        _time = time
    }

    fun getName(): String {
        return _name
    }

    fun getCatagory(): String {
        return _category
    }

    fun getDifficulty(): String {
        return _difficulty
    }

    fun getReps(): Int {
        return _reps
    }

    fun getSets(): Int {
        return _sets
    }

    fun getTime(): Double {
        return _time
    }

}