package com.kernelpanic.sovita.ui.exercise

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.kernelpanic.sovita.CustomAdapter
import com.kernelpanic.sovita.R
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import java.net.URL


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
    private lateinit var abExercisesID: ArrayList<String>

    private lateinit var backButton: Button
    private lateinit var backExercises: ArrayList<Exercise>
    private lateinit var backExercisesID: ArrayList<String>

    private lateinit var bicepsButton: Button
    private lateinit var bicepExercises: ArrayList<Exercise>
    private lateinit var bicepExercisesID: ArrayList<String>

    private lateinit var calvesButton: Button
    private lateinit var calvesExercises: ArrayList<Exercise>
    private lateinit var calvesExercisesID: ArrayList<String>

    private lateinit var chestButton: Button
    private lateinit var chestExercises: ArrayList<Exercise>
    private lateinit var chestExercisesID: ArrayList<String>

    private lateinit var glutesButton: Button
    private lateinit var gluteExercises: ArrayList<Exercise>
    private lateinit var gluteExercisesID: ArrayList<String>

    private lateinit var hamstringsButton: Button
    private lateinit var hamstringExercises: ArrayList<Exercise>
    private lateinit var hamstringExercisesID: ArrayList<String>

    private lateinit var quadsButton: Button
    private lateinit var quadExercises: ArrayList<Exercise>
    private lateinit var quadExercisesID: ArrayList<String>

    private lateinit var shouldersButton: Button
    private lateinit var shoulderExercises: ArrayList<Exercise>
    private lateinit var shoulderExercisesID: ArrayList<String>

    private lateinit var tricepsButton: Button
    private lateinit var tricepExercises: ArrayList<Exercise>
    private lateinit var tricepExercisesID: ArrayList<String>

    private lateinit var ab_listview : ListView
    private lateinit var back_listview : ListView
    private lateinit var bicep_listview : ListView
    private lateinit var calves_listview : ListView
    private lateinit var chest_listview : ListView
    private lateinit var glutes_listview : ListView
    private lateinit var hamstring_listview : ListView
    private lateinit var quad_listview : ListView
    private lateinit var shoulder_listview : ListView
    private lateinit var tricep_listview : ListView

    private lateinit var c_adapter: CustomAdapter

    private lateinit var workout_name: EditText
    private lateinit var save_workoutButton: ImageButton
    private lateinit var exercisesID: ArrayList<String>
    private var workoutID : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_workout)

        workout_name = findViewById(R.id.new_workout_name)

        save_workoutButton = findViewById(R.id.save_workout)
        all_exercises = ArrayList<Exercise>()
        exercisesID = ArrayList<String>()


        save_workoutButton.setOnClickListener {
            //DIsplay message? like :Are you sure you want to save?

            //If it's checked, then add it to the workout
            for(i in 0 until abExercises.size) {
                if (abExercises[i].isChecked()){
                    all_exercises.add(abExercises[i])
                    exercisesID.add(abExercisesID[i])
                }
            }

            for(i in 0 until backExercises.size) {
                if (backExercises[i].isChecked()){
                    all_exercises.add(backExercises[i])
                    exercisesID.add(backExercisesID[i])
                }
            }

            for(i in 0 until bicepExercises.size) {
                if (bicepExercises[i].isChecked()){
                    all_exercises.add(bicepExercises[i])
                    exercisesID.add(bicepExercisesID[i])
                }
            }

            for(i in 0 until calvesExercises.size) {
                if (calvesExercises[i].isChecked()){
                    all_exercises.add(calvesExercises[i])
                    exercisesID.add(calvesExercisesID[i])
                }
            }

            for(i in 0 until chestExercises.size) {
                if (chestExercises[i].isChecked()){
                    all_exercises.add(chestExercises[i])
                    exercisesID.add(chestExercisesID[i])
                }
            }

            for(i in 0 until gluteExercises.size) {
                if (gluteExercises[i].isChecked()){
                    all_exercises.add(gluteExercises[i])
                    exercisesID.add(chestExercisesID[i])
                }
            }

            for(i in 0 until hamstringExercises.size) {
                if (hamstringExercises[i].isChecked()){
                    all_exercises.add(hamstringExercises[i])
                    exercisesID.add(hamstringExercisesID[i])
                }
            }

            for(i in 0 until quadExercises.size) {
                if (quadExercises[i].isChecked()){
                    all_exercises.add(quadExercises[i])
                    exercisesID.add(quadExercisesID[i])
                }
            }

            for(i in 0 until shoulderExercises.size) {
                if (shoulderExercises[i].isChecked()){
                    all_exercises.add(shoulderExercises[i])
                    exercisesID.add(shoulderExercisesID[i])
                }
            }

            for(i in 0 until tricepExercises.size) {
                if (tricepExercises[i].isChecked()){
                    all_exercises.add(tricepExercises[i])
                    exercisesID.add(tricepExercisesID[i])
                }
            }

            //Creates the workout will the entered name and all exercises that were checked
            new_workout = Workout(workout_name.text.toString(), all_exercises, workoutID)

            val intent = Intent(this@NewWorkout, WorkoutEdit::class.java)
            intent.putExtra("workout",new_workout)
            startActivity(intent)

        }


        abExercises = ArrayList<Exercise>()
        abExercisesID = ArrayList<String>() //Parellel to abExercises

        backExercises = ArrayList<Exercise>()
        backExercisesID = ArrayList<String>()

        bicepExercises = ArrayList<Exercise>()
        bicepExercisesID = ArrayList<String>()

        calvesExercises = ArrayList<Exercise>()
        calvesExercisesID = ArrayList<String>()

        chestExercises = ArrayList<Exercise>()
        chestExercisesID = ArrayList<String>()

        gluteExercises = ArrayList<Exercise>()
        gluteExercisesID = ArrayList<String>()

        hamstringExercises = ArrayList<Exercise>()
        hamstringExercisesID = ArrayList<String>()

        quadExercises = ArrayList<Exercise>()
        quadExercisesID = ArrayList<String>()

        shoulderExercises = ArrayList<Exercise>()
        shoulderExercisesID = ArrayList<String>()

        tricepExercises = ArrayList<Exercise>()
        tricepExercisesID = ArrayList<String>()

        doAsychcall("http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000/Exercises/Catagory/Abdominals",abExercisesID,abExercises)
        doAsychcall("http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000/Exercises/Catagory/Back", backExercisesID, backExercises)
        doAsychcall("http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000/Exercises/Catagory/Biceps", bicepExercisesID, bicepExercises)
        doAsychcall("http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000/Exercises/Catagory/Calves", calvesExercisesID, calvesExercises)
        doAsychcall("http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000/Exercises/Catagory/Chest", chestExercisesID, chestExercises)
        doAsychcall("http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000/Exercises/Catagory/Glutes", gluteExercisesID, gluteExercises)
        doAsychcall("http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000/Exercises/Catagory/Hamstrings",hamstringExercisesID,hamstringExercises)
        doAsychcall("http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000/Exercises/Catagory/Quads", quadExercisesID, quadExercises)
        doAsychcall("http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000/Exercises/Catagory/Shoulders", shoulderExercisesID, shoulderExercises)
        doAsychcall("http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000/Exercises/Catagory/Triceps", tricepExercisesID, tricepExercises)
        getLastID("http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000/workouts")
        workoutID += 1 //Increase count by one so it's always unique



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


        //Setting all the listeners for all the buttons
        absButton.setOnClickListener {
            ab_listview = findViewById(R.id.exercise_listview)
            getExerciseNames(abExercises, ab_listview)
        }
        backButton.setOnClickListener {
            back_listview = findViewById(R.id.exercise_listview)
            getExerciseNames(backExercises,back_listview)

        }
        bicepsButton.setOnClickListener {
            bicep_listview = findViewById(R.id.exercise_listview)
            getExerciseNames(bicepExercises,bicep_listview)

        }
        calvesButton.setOnClickListener {
            calves_listview = findViewById(R.id.exercise_listview)
            getExerciseNames(calvesExercises,calves_listview)
        }
        chestButton.setOnClickListener {
            chest_listview = findViewById(R.id.exercise_listview)
            getExerciseNames(chestExercises,chest_listview)

        }
        glutesButton.setOnClickListener {
            glutes_listview = findViewById(R.id.exercise_listview)
            getExerciseNames(gluteExercises,glutes_listview)
        }
        hamstringsButton.setOnClickListener {
            hamstring_listview = findViewById((R.id.exercise_listview))
            getExerciseNames(hamstringExercises, hamstring_listview)

        }
        quadsButton.setOnClickListener {
            quad_listview = findViewById(R.id.exercise_listview)
            getExerciseNames(quadExercises,quad_listview)
        }
        shouldersButton.setOnClickListener {
            shoulder_listview = findViewById(R.id.exercise_listview)
            getExerciseNames(shoulderExercises,shoulder_listview)
        }
        tricepsButton.setOnClickListener {
            tricep_listview = findViewById(R.id.exercise_listview)
            getExerciseNames(tricepExercises,tricep_listview)
        }

    }

    //This function will pull the categories exercises and show them in the list based on what button was clicked
    private fun getExerciseNames(exercises: ArrayList<Exercise>, ex_listview: ListView) {
        //This is what creates a scrollable list with textboxes that the user can select from
        c_adapter = CustomAdapter(exercises, applicationContext)
        ex_listview.adapter = c_adapter
        ex_listview.setOnItemClickListener{parent, view, position, id ->
            exercises[position].setCheck(exercises[position].isChecked())
            c_adapter.notifyDataSetChanged()
        }

    }

    //Somethings should be a 2D array, I know that. But it's ugly in Kotlin so I'm making them parallel. Eventually it'll bug me that it's wrong and I'll fix it but for know it is what it is
    private fun doAsychcall(url: String, exerciseID: ArrayList<String>, exerciseList: ArrayList<Exercise>){
        doAsync() {
            var result = URL(url).readText()
            URL(url).apply {  }
            val final_ex_info = ArrayList<String>()

            result = result.removeRange(0,40) //Gets rid of the header
            result = result.removeRange(result.length-3,result.length) //Gets rid of the extra }]} at the end
            val split_result = result.split("},{").toTypedArray() //Splits then up by row
            for (i in split_result) {
                val exercise_info = i.split(",\"").toTypedArray() //Splits then up by row
                for (j in exercise_info) {
                    val exercise_info2 = j.split("\":").toTypedArray() //Splits then up by row
                    for (k in exercise_info2) {
                        final_ex_info.add(k)
                    }
                }

                //Removing the qoute at beginning
                final_ex_info[0] = final_ex_info[0].removeRange(0,1)
                //Removing hte qoute at the beginning and end
                final_ex_info[3] = final_ex_info[3].removeRange(0,1)
                final_ex_info[3] = final_ex_info[3].removeRange(final_ex_info[3].length-1,final_ex_info[3].length)
                //Removing Qoute at Beginning and End
                final_ex_info[5] = final_ex_info[5].removeRange(0,1)
                final_ex_info[5] = final_ex_info[5].removeRange(final_ex_info[5].length-1,final_ex_info[5].length)
                //This one has a /r at the end along with the qoute
                final_ex_info[7] = final_ex_info[7].removeRange(0,1)
                final_ex_info[7] = final_ex_info[7].removeRange(final_ex_info[7].length-3,final_ex_info[7].length)
                //The qoutes may be a problem or may not in the link I'm not sure
                final_ex_info[9] = final_ex_info[9].removeRange(0,1)
                final_ex_info[9] = final_ex_info[9].removeRange(final_ex_info[9].length-1,final_ex_info[9].length)
                println("Link: " + final_ex_info[9])

                exerciseID.add(final_ex_info[1])
                exerciseList.add(Exercise(final_ex_info[3], final_ex_info[5], final_ex_info[7], final_ex_info[9], final_ex_info[1]))
                final_ex_info.clear()

            }

            activityUiThread {

            }
        }
    }

    private fun getLastID(url:String) {
        doAsync {
            var count = 1
            var result = URL(url).readText()
            val workout_info = ArrayList<String>()

            result = result.removeRange(0, 40) //Gets rid of the header
            result = result.removeRange(result.length - 3, result.length) //Gets rid of the extra }]} at the end
            val split_result = result.split("},{").toTypedArray() //Splits then up by row aka by workout

            for (i in split_result) {
                count += 1 //Let's us know how many workouts we have so we know the id
            }

            activityUiThread {
                //Can't return the count from asyc function, need to set the variable in the UI thread for the value to save
                workoutID = count
            }
        }

    }


}

