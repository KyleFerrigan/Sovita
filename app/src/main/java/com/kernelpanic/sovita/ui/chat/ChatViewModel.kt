package com.kernelpanic.sovita.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChatViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        //value = "Naperville Speedsters"
    }
    val text: LiveData<String> = _text
}