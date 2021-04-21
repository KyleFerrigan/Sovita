package com.kernelpanic.sovita

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class WorkoutEdit : AppCompatActivity() {
    private lateinit var workoutName : TextView
    //private lateinit var workout_exercises: ArrayList<Exercise>
    //private lateinit var list_workout : ListView
    private lateinit var rep_button : Button
    private lateinit var time_button : Button
    private lateinit var exercise_input : EditText
    private lateinit var exercise_name : TextView
    private lateinit var next_button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_edit)

        val exercises = ArrayList<Exercise>()
        exercises.add(Exercise("one", "legs", "Easy","test"))
        exercises.add(Exercise("two", "legs", "Easy2","test2"))
        exercises.add(Exercise("3", "legs", "Easy","test3"))
        exercises.add(Exercise("4", "legs", "Easy","test4"))
        exercises.add(Exercise("5", "legs", "Easy","test5"))
        val workout1 = Workout("Test1",exercises)

        workoutName = findViewById(R.id.workout_name)
        workoutName.setText(workout1.getWorkoutName())

        rep_button = findViewById(R.id.reps)
        time_button = findViewById(R.id.time)

        exercise_input = findViewById(R.id.exercise_input)
        exercise_name = findViewById(R.id.exercise_name)

        next_button = findViewById(R.id.next_exercise)

        //var marker = false
        //False it's time, True it's rep

        var count = 0
        exercise_name.text = workout1.getExerciseName(count)

        next_button.setOnClickListener {
            workout1.setExerciseTime(count, exercise_input.text.toString().toDouble())
            workout1.setExerciseReps(count, exercise_input.text.toString().toInt())
            count += 1
            exercise_name.text = workout1.getExerciseName(count)
            exercise_input.hint = "Select Time or Reps"
        }

        time_button.setOnClickListener {
            exercise_input.hint = "Enter Time"

        }

        rep_button.setOnClickListener {
            exercise_input.hint = "Enter Reps"
        }


            /*
            if (marker){
                workout1.setExerciseReps(i,exercise_input.text.toString().toInt())
            } else {
                workout1.setExerciseTime(i,exercise_input.text.toString().toDouble())
            }*/
        //}


        /*
        // initialize an array adapter
        val adapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,list
        )

        // attach the array adapter with list view
        list_workout.adapter = adapter

        // list view item click listener
        list_workout.onItemClickListener = OnItemClickListener {
            parent, view, position, id ->
            val selectedItemText = parent.getItemAtPosition(position)

        }
        */

    }
}