package com.kernelpanic.sovita

import android.media.Image
import java.net.URL
import java.io.Serializable;

// Add an image as parameter later on
//This holds the exercise
class Exercise(val _name: String, val _category: String, val _difficulty: String, val _imageLink: String, val _id : String): Serializable {
    //From what I found online, there is no constructor, you just set the variables in the header
    //Member variables
    private var _reps: Int = 0
    private var _sets: Int = 0
    private var _time: Int = 0
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

    fun setTime(time: Int) {
        _time = time
    }

    fun getName(): String {
        return _name
    }

    fun getiD() : String {
        return _id
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

    fun getTime(): Int {
        return _time
    }

    fun getImageLink() : String {
    //    var picture = URL("https://sovita.s3.amazonaws.com/WorkoutGifs/woodChopper.gif").getContent()
    //    return picture
        return _imageLink
    }

}