package com.kernelpanic.sovita

import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class NewWorkout2 : AppCompatActivity() {
    private lateinit var workoutName : TextView
    private lateinit var workout_exercises: ArrayList<Exercise>
    private lateinit var list_workout : ListView
    //private lateinit var c_adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_workout_2)


        //Creating a temp workout for now
        val exercises = ArrayList<Exercise>()
        exercises.add(Exercise("one", "legs", "Easy","test"))
        exercises.add(Exercise("two", "legs", "Easy2","test2"))
        exercises.add(Exercise("3", "legs", "Easy","test3"))
        exercises.add(Exercise("4", "legs", "Easy","test4"))
        exercises.add(Exercise("5", "legs", "Easy","test5"))
        val workout1 = Workout("Test1",exercises)

        workoutName = findViewById(R.id.workout_name)
        workoutName.setText(workout1.getWorkoutName())

        list_workout = findViewById(R.id.workout_exercises)


        //val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, workout1.)
        //list_workout_exercises.adapter = adapter
       // c_adapter = CustomAdapter(exercises, applicationContext)
        //list_workout.adapter = c_adapter
        //list_workout.setOnItemClickListener{parent, view, position, id ->
        //    workout1.getExerciseName(position).setCheck(workout1.getExerciseName(position).isChecked())
        //    [position].setCheck(exercises[position].isChecked())
        //    c_adapter.notifyDataSetChanged()
       // }

        //val user_workout = intent.getSerializableExtra("NEW_WORKOUT") as Workout


        //val workout = intent.getSerializableExtra() as? Workout

        //This is what creates a scrollable list with textboxes that the user can select from
        //val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        //list_workout_exercises.adapter = adapter



    }
}