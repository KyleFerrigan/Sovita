package com.kernelpanic.sovita

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

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