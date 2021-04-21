package com.kernelpanic.sovita.ui.meals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MealsViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Coming next semester!"
    }
    val text: LiveData<String> = _text
}