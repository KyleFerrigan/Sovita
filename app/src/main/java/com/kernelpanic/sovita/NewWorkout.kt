package com.kernelpanic.sovita

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.kernelpanic.sovita.ui.exercise.ExerciseFragment

class NewWorkout : AppCompatActivity() {
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_workout)

    backButton = findViewById(R.id.back_button)
        backButton.setOnClickListener{
            finish()
        }
    }
}