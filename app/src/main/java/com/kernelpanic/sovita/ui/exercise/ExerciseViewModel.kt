package com.kernelpanic.sovita.ui.exercise

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kernelpanic.sovita.NewWorkout

class ExerciseViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is exercise Fragment"

    }
    val text: LiveData<String> = _text


}