package com.kernelpanic.sovita.ui.exercise

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kernelpanic.sovita.R
import com.kernelpanic.sovita.addWorkoutData
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync

class WorkoutEdit : AppCompatActivity() {
    private lateinit var workoutName : TextView
    private lateinit var rep_button : Button
    private lateinit var time_button : Button
    private lateinit var exercise_input : EditText
    private lateinit var exercise_name : TextView
    private lateinit var exerciseIDs : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_edit)

        val intent = intent
        val workout: Workout = intent.getSerializableExtra("workout") as Workout


      //  val exerciseIDs = intent.getStringArrayListExtra("exerciseID")
        /*
        var exerciseIDStr = ""

        if (exerciseIDs != null) {
            for (i in 0..exerciseIDs.size) {
                if (i == exerciseIDs.size-1) {
                  exerciseIDStr = exerciseIDStr + exerciseIDs[i]
                } else {
                    exerciseIDStr = exerciseIDStr + exerciseIDs[i] + ","
                }
            }
        }
        println("Exercise String")
        println(exerciseIDStr)
*/
        val exercises = ArrayList<Exercise>()
        //exercises.add(Exercise("one", "legs", "Easy","test"))
        //exercises.add(Exercise("two", "legs", "Easy2","test2"))
        //exercises.add(Exercise("3", "legs", "Easy","test3"))
        //exercises.add(Exercise("4", "legs", "Easy","test4"))
        //exercises.add(Exercise("5", "legs", "Easy","test5"))
        //val workout1 = Workout("Test1",exercises)

        workoutName = findViewById(R.id.workout_name)
        //workoutName.setText(workout1.getWorkoutName())
        workoutName.text = "Workout: " + workout.getWorkoutName()

        rep_button = findViewById(R.id.reps)
        time_button = findViewById(R.id.time)

        exercise_input = findViewById(R.id.exercise_input)
        exercise_name = findViewById(R.id.exercise_name)

        var count = 0
        exercise_name.text = "Exercise: " + workout.getExerciseName(count)
        exercise_input.hint = "Enter Time in Minutes or Reps and hit appropriate button"

        time_button.setOnClickListener {
            workout.setExerciseTime(count,exercise_input.text.toString().toInt())
            count += 1
            if (count < workout.getNumExercises()) {
                exercise_name.text = workout.getExerciseName(count)
                exercise_input.setText("")
                exercise_input.hint = "Enter Time (Minutes) or Reps (Whole #)"
            } else {
                doAsync {
                    addWorkoutData(
                            workout.getWorkoutID_().toString(), "3", workout.getExerciseIDs(),
                            workout.getExerciseReps(), workout.getExerciseTimes(), workout.getWorkoutName()
                    )
                    println("Here")
                    activityUiThread {

                    }
                }
                val intent = Intent(this@WorkoutEdit, WorkoutSaved::class.java)
                startActivity(intent)
            }
        }

        rep_button.setOnClickListener {
            workout.setExerciseReps(count, exercise_input.text.toString().toInt())
            count += 1
            if (count < workout.getNumExercises()) {
                exercise_name.text = workout.getExerciseName(count)
                exercise_input.setText("")
                exercise_input.hint = "Enter Time (Minutes) or Reps (Whole #)"
            } else {
                doAsync {
                    addWorkoutData(
                            workout.getWorkoutID_().toString(), "3", workout.getExerciseIDs(),
                            workout.getExerciseReps(), workout.getExerciseTimes(), workout.getWorkoutName()
                    )
                    println("Here333")
                    activityUiThread {

                    }
                }
                val intent = Intent(this@WorkoutEdit, WorkoutSaved::class.java)
                startActivity(intent)
            }
        }
    }
}