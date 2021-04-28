package com.kernelpanic.sovita.ui.exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import com.kernelpanic.sovita.R


class ExerciseBegin : AppCompatActivity() {
    private lateinit var textView: TextView

    //This class if you refer to it with a button it crashes xoxo, Jessica after spending 2 hours trying to figure out what's wrong

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_begin)


        //val intent = intent
        //val workout: String? = intent.getStringExtra("workout")
        textView = findViewById(R.id.workoutName)
        textView.setText("Hello")





    }
}