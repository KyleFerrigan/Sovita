package com.kernelpanic.sovita.ui.exercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.kernelpanic.sovita.MainActivity
import com.kernelpanic.sovita.R

class WorkoutFinished : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_finished)

        val doneButton = findViewById<Button>(R.id.end_button)
        doneButton.setOnClickListener {
            val intent = Intent(this@WorkoutFinished, MainActivity::class.java)
            startActivity(intent)
        }

    }
}