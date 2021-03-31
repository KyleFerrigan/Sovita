package com.kernelpanic.sovita

import android.R.layout.simple_list_item_1
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService

class NewWorkout : AppCompatActivity() {

    private lateinit var prevButton: Button

    //Holds all the users workouts, don't know if this belongs here or if we need it but it's here
    private lateinit var user_workouts: ArrayList<Workout>

    //New workout that is going to be added
    private lateinit var new_workout: Workout

    //Exercises for the new workout
    private lateinit var all_exercises: ArrayList<Exercise>

    //Ten Catagories
    private lateinit var absButton: Button
    private lateinit var abExercises: ArrayList<Exercise>

    private lateinit var backButton: Button
    private lateinit var backExercises: ArrayList<Exercise>

    private lateinit var bicepsButton: Button
    private lateinit var bicepExercises: ArrayList<Exercise>

    private lateinit var calvesButton: Button
    private lateinit var calvesExercises: ArrayList<Exercise>

    private lateinit var chestButton: Button
    private lateinit var chestExercises: ArrayList<Exercise>

    private lateinit var glutesButton: Button
    private lateinit var gluteExercises: ArrayList<Exercise>

    private lateinit var hamstringsButton: Button
    private lateinit var hamstringExercises: ArrayList<Exercise>

    private lateinit var quadsButton: Button
    private lateinit var quadExercises: ArrayList<Exercise>

    private lateinit var shouldersButton: Button
    private lateinit var shoulderExercises: ArrayList<Exercise>

    private lateinit var tricepsButton: Button
    private lateinit var tricepExercises: ArrayList<Exercise>

    private lateinit var ex_listview: ListView
    private lateinit var c_adapter: CustomAdapter

    private lateinit var workout_name: EditText
    private lateinit var save_workoutButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_workout)

        workout_name = findViewById(R.id.new_workout_name)
        save_workoutButton = findViewById(R.id.save_workout)

        save_workoutButton.setOnClickListener {
            //DIsplay message? like :Are you sure you want to save?

            //If it's checked, then add it to the workout
            for(i in 0 until abExercises.size) {
                if (abExercises[i].isChecked()){
                    all_exercises.add(abExercises[i])
                }
            }

            for(i in 0 until backExercises.size) {
                if (backExercises[i].isChecked()){
                    all_exercises.add(backExercises[i])
                }
            }

            for(i in 0 until bicepExercises.size) {
                if (bicepExercises[i].isChecked()){
                    all_exercises.add(bicepExercises[i])
                }
            }

            for(i in 0 until calvesExercises.size) {
                if (calvesExercises[i].isChecked()){
                    all_exercises.add(calvesExercises[i])
                }
            }

            for(i in 0 until chestExercises.size) {
                if (chestExercises[i].isChecked()){
                    all_exercises.add(chestExercises[i])
                }
            }

            for(i in 0 until gluteExercises.size) {
                if (gluteExercises[i].isChecked()){
                    all_exercises.add(gluteExercises[i])
                }
            }

            for(i in 0 until hamstringExercises.size) {
                if (hamstringExercises[i].isChecked()){
                    all_exercises.add(hamstringExercises[i])
                }
            }

            for(i in 0 until quadExercises.size) {
                if (quadExercises[i].isChecked()){
                    all_exercises.add(quadExercises[i])
                }
            }

            for(i in 0 until shoulderExercises.size) {
                if (shoulderExercises[i].isChecked()){
                    all_exercises.add(shoulderExercises[i])
                }
            }

            for(i in 0 until tricepExercises.size) {
                if (tricepExercises[i].isChecked()){
                    all_exercises.add(tricepExercises[i])
                }
            }

            //Creates the workout will the entered name and all exercises that were checked
            new_workout = Workout(workout_name.toString(), all_exercises)

            //Add to list or database..I think it's database
            //user_workouts.add(Workout(workout_name.toString(), all_exercises))
        }

        prevButton = findViewById(R.id.prev_button)
        prevButton.setOnClickListener{
            finish()
        }

        abExercises = ArrayList<Exercise>()
        backExercises = ArrayList<Exercise>()
        bicepExercises = ArrayList<Exercise>()
        calvesExercises = ArrayList<Exercise>()
        chestExercises = ArrayList<Exercise>()
        gluteExercises = ArrayList<Exercise>()
        hamstringExercises = ArrayList<Exercise>()
        quadExercises = ArrayList<Exercise>()
        shoulderExercises = ArrayList<Exercise>()
        tricepExercises = ArrayList<Exercise>()

        //Adding some exercises, need to pull this from the database later
        abExercises.add(Exercise("Crunches", "Abs", "E"))
        abExercises.add(Exercise("ACrunches", "Abs", "E"))
        abExercises.add(Exercise("BCrunches", "Abs", "E"))
        abExercises.add(Exercise("CCrunches", "Abs", "E"))

        backExercises.add(Exercise("Hula Hoop", "Back", "E"))
        backExercises.add(Exercise("Hula Hoop1", "Back", "E"))
        backExercises.add(Exercise("Hula Hoop2","Back",  "E"))
        backExercises.add(Exercise("Hula Hoop3", "Back", "E"))

        bicepExercises.add(Exercise("X", "Bicep", "M"))
        bicepExercises.add(Exercise("X1", "Bicep", "M"))
        bicepExercises.add(Exercise("X2", "Bicep", "M"))
        bicepExercises.add(Exercise("X3", "Bicep", "M"))
        bicepExercises.add(Exercise("X4", "Bicep", "M"))
        bicepExercises.add(Exercise("Xg", "Bicep", "M"))
        bicepExercises.add(Exercise("Xe", "Bicep", "M"))
        bicepExercises.add(Exercise("yX", "Bicep", "M"))
        bicepExercises.add(Exercise("mX", "Bicep", "M"))
        bicepExercises.add(Exercise("m1X", "Bicep", "M"))
        bicepExercises.add(Exercise("m2X", "Bicep", "M"))

        calvesExercises.add(Exercise("ZZ", "Calves", "M"))
        calvesExercises.add(Exercise("Z1Z", "Calves", "M"))
        calvesExercises.add(Exercise("Z2Z", "Calves", "M"))
        calvesExercises.add(Exercise("Z22Z", "Calves", "M"))

        chestExercises.add(Exercise("WW", "Chest", "H"))
        chestExercises.add(Exercise("33K", "Chest", "H"))
        chestExercises.add(Exercise("JAZZ", "Chest", "H"))

        gluteExercises.add(Exercise("Pop", "Glutes", "M"))
        gluteExercises.add(Exercise("Pop22", "Glutes", "M"))

        hamstringExercises.add(Exercise("LOL", "Hamstring", "M"))

        quadExercises.add(Exercise("TBH", "Quad", "M"))

        shoulderExercises.add(Exercise("SMH", "Shoulder", "M"))

        tricepExercises.add(Exercise("10/10", "Tricep", "M"))

        absButton = findViewById(R.id.abs_catagory)
        backButton = findViewById(R.id.back_catagory)
        bicepsButton = findViewById(R.id.biceps_catagory)
        calvesButton = findViewById(R.id.calves_catagory)
        chestButton = findViewById(R.id.chest_catagory)
        glutesButton = findViewById(R.id.glutes_catagory)
        hamstringsButton = findViewById(R.id.hamstrings_catagory)
        quadsButton = findViewById(R.id.quads_catagory)
        shouldersButton = findViewById(R.id.shoulders_catagory)
        tricepsButton = findViewById(R.id.triceps_catagory)

        //List that holds all the exercises
        ex_listview = findViewById(R.id.exercise_listview)

        //Setting all the listeners for all the buttons
        absButton.setOnClickListener {
            getExerciseNames(abExercises,ex_listview)
        }
        backButton.setOnClickListener {
            getExerciseNames(backExercises,ex_listview)

        }
        bicepsButton.setOnClickListener {
            getExerciseNames(bicepExercises,ex_listview)

        }
        calvesButton.setOnClickListener {
            getExerciseNames(calvesExercises,ex_listview)

        }
        chestButton.setOnClickListener {
            getExerciseNames(chestExercises,ex_listview)

        }
        glutesButton.setOnClickListener {
            getExerciseNames(gluteExercises,ex_listview)

        }
        hamstringsButton.setOnClickListener {
            getExerciseNames(hamstringExercises,ex_listview)

        }
        quadsButton.setOnClickListener {
            getExerciseNames(quadExercises,ex_listview)

        }
        shouldersButton.setOnClickListener {
            getExerciseNames(shoulderExercises,ex_listview)

        }
        tricepsButton.setOnClickListener {
            getExerciseNames(tricepExercises,ex_listview)

        }

    }

    //This function will pull the categories exercises and show them in the list based on what button was clicked
    private fun getExerciseNames(exercises: ArrayList<Exercise>, ex_listview: ListView) {
        //Creates an empty list of string we will then fill with exercise names
        val listItems = arrayOfNulls<String>(exercises.size)

        //Gets all the exercise names
        for(i in 0 until exercises.size) {
            listItems[i] = exercises[i].getName()
        }

        //This is what creates a scrollable list with textboxes that the user can select from
        c_adapter = CustomAdapter(exercises, applicationContext)
        ex_listview.adapter = c_adapter
        ex_listview.setOnItemClickListener{parent, view, position, id ->
            val exercise: Exercise = exercises!![position] as Exercise
            exercise.isChecked() != exercise.isChecked()
            c_adapter.notifyDataSetChanged()
        }

    }
}

