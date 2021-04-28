package com.kernelpanic.sovita.ui.exercise

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.kernelpanic.sovita.MainActivity
import com.kernelpanic.sovita.R

class WorkoutSaved : AppCompatActivity() {
private lateinit var okButton : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_saved)

       okButton = findViewById(R.id.okay_button)
        okButton.setOnClickListener {
            val intent = Intent(this@WorkoutSaved, MainActivity::class.java)
            startActivity(intent)
        }


    }
}