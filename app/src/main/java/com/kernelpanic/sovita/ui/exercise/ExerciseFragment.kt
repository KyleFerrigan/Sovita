package com.kernelpanic.sovita.ui.exercise

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.jetbrains.anko.doAsync
import java.net.URL
import android.widget.ArrayAdapter
import com.kernelpanic.sovita.*
import org.jetbrains.anko.uiThread


class ExerciseFragment: Fragment()  {
    private lateinit var listWorkouts:ListView

    private lateinit var selectedworkout: String
    private lateinit var selectedItemText: String

    private lateinit var workout: Workout
    // fun OnCreate(savedInstanceState: Bundle?) {
     //   super.onCreate(savedInstanceState)

    //}




    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        //exerciseViewModel =
               // ViewModelProvider(this).get(ExerciseViewModel::class.java)

        val view = inflater.inflate(R.layout.fragment_exercise, container, false)

        val workoutNames = ArrayList<String>()
        val workoutID = ArrayList<String>()

        listWorkouts = view.findViewById(R.id.workout_list)


        //This will pull all the users workouts
        doAsychcall("http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000/Workouts/userid/3",workoutNames,listWorkouts,workoutID)


        //This makes the + button on the exercise screen work so that it leads you to the new workout screen
        val workoutButton = view?.findViewById<FloatingActionButton>(R.id.fabExHome)
        workoutButton?.setOnClickListener{
            val intent = Intent(this.activity, NewWorkout::class.java)
            startActivity(intent)
        }

        val textView = view?.findViewById<TextView>(R.id.test)

        //Listivew for all the workouts
        listWorkouts.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id ->
                    selectedItemText = parent.getItemAtPosition(position) as String
                    selectedworkout = workoutID[position]
                    val exercises = ArrayList<Exercise>()
                    workout = Workout(selectedItemText, exercises, selectedworkout.toInt())
                    doAsychCallWorkout("http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000/Workouts/id/$selectedworkout", workout)
                    if (textView != null) {
                        textView.text = (selectedItemText)
                    }
                }

        //Button to start the workout
        val startWorkout = view?.findViewById<Button>(R.id.startWorkout)
        startWorkout?.setOnClickListener {
            val intent = Intent(this.activity, WorkoutScreen::class.java)
            intent.putExtra("workout2",workout)
            startActivity(intent)
        }


        //I don't know what this is for now so I commented it out -- exerciseViewModel.text.observe(viewLifecycleOwner, Observer {
        //})



        return view
        }


    private fun doAsychcall(url: String, workoutName: ArrayList<String>, listworkouts: ListView, workoutID: ArrayList<String>) {
        doAsync() {
            var result = URL(url).readText()
            val workoutInfo = ArrayList<String>()

            result = result.removeRange(0, 40) //Gets rid of the header
            result = result.removeRange(
                result.length - 3,
                result.length
            ) //Gets rid of the extra }]} at the end

            var count = 0 //Holds the number of workouts there are

            val split_result = result.split("},{\"").toTypedArray() //Splits then up by row
            for (i in split_result) {
                val exercise_info = i.split(",\"").toTypedArray() //Splits then up by row
                count += 1
                for (j in exercise_info) {
                    val exercise_info2 = j.split("\":").toTypedArray() //Splits then up by row
                    for (k in exercise_info2) {
                        //println("K")
                        //println(k)
                        workoutInfo.add(k)
                    }
                }
            }

            var idSlot = 1
            var useridSlot = 3
            var exercisesSlot = 5
            var repsSlot = 7
            var timeSlot = 9
            var nameSlot = 11

            for(i in 0..count-1){
                //println("For loop")

                val name = workoutInfo[nameSlot]
                name.removeRange(0,1)
                name.removeRange(name.length-1,name.length)
                workoutName.add(name)
                workoutID.add(workoutInfo[idSlot])
                //println("Name added:" + name)
                nameSlot += 12
                idSlot += 12

            }
            uiThread {
                val adapter = activity?.let {
                    ArrayAdapter(
                        it,
                        android.R.layout.simple_list_item_1,workoutName
                    )
                }

                listWorkouts.adapter = adapter

            }
        }
    }

    private fun doAsychCallWorkout(url : String, workout: Workout) {
        doAsync() {
            var result = URL(url).readText()
            val workoutInfo = ArrayList<String>()

            result = result.removeRange(0, 41) //Gets rid of the header
            //Gets rid of the extra }]} at the end
            result = result.removeRange(
                    result.length - 3,
                    result.length
            )

            val split_result = result.split(",\"").toTypedArray()
            for (i in split_result) {
                val exercise_info = i.split("\":").toTypedArray()
                for (j in exercise_info) {
                    workoutInfo.add(j)
                }
            }

            var idSlot = 1
            var useridSlot = 3
            var exercisesSlot = 5

            //Gets rid of the ""
            workoutInfo[5] = workoutInfo[5].removeRange(0,1)
            workoutInfo[5] = workoutInfo[5].removeRange(workoutInfo[5].length-1,workoutInfo[5].length)

            var repsSlot = 7
            workoutInfo[7] = workoutInfo[7].removeRange(0,1)
            workoutInfo[7] = workoutInfo[7].removeRange(workoutInfo[7].length-1,workoutInfo[7].length)

            var timeSlot = 9
            workoutInfo[9] = workoutInfo[9].removeRange(0,1)
            workoutInfo[9] = workoutInfo[9].removeRange(workoutInfo[9].length-1,workoutInfo[9].length)

            var nameSlot = 11
            workoutInfo[11] = workoutInfo[11].removeRange(0,1)
            workoutInfo[11] = workoutInfo[11].removeRange(workoutInfo[11].length-1,workoutInfo[11].length)

            val exerciseIDs = workoutInfo[5].split(",").toTypedArray()
            val reps = workoutInfo[7].split(",").toTypedArray()
            val times = workoutInfo[9].split(",").toTypedArray()
            val tempExerciseInfo = ArrayList<String>()
            var count = 0 //This will keep track of the reps and time for each exercise



            for (i in exerciseIDs) {
                //Pull Exercise from Database
                var eResult = URL("http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000/Exercises/id/$i").readText()

                eResult = eResult.removeRange(0, 41) //Gets rid of the header
                //Gets rid of the extra }]} at the end
                eResult = eResult.removeRange(
                        eResult.length - 3,
                        eResult.length
                )


                val split_result2 = eResult.split(",\"").toTypedArray()
                for (i in split_result2) {
                    val exercise_info2 = i.split("\":").toTypedArray()
                    for (j in exercise_info2) {
                        //println("J2")
                        //println(j)
                        tempExerciseInfo.add(j)
                    }
                }

                var eIDslot = 1
                var eNameslot = 3

                //Gets rid of ""
                tempExerciseInfo[3] = tempExerciseInfo[3].removeRange(0,1)
                tempExerciseInfo[3] = tempExerciseInfo[3].removeRange(tempExerciseInfo[3].length-1,tempExerciseInfo[3].length)

                var eCataslot = 5
                tempExerciseInfo[5] = tempExerciseInfo[5].removeRange(0,1)
                tempExerciseInfo[5] = tempExerciseInfo[5].removeRange(tempExerciseInfo[5].length-1,tempExerciseInfo[5].length)

                var eDiffslot = 7

                tempExerciseInfo[7] = tempExerciseInfo[7].removeRange(0,1)
                //This one has a /r for some reson
                tempExerciseInfo[7] = tempExerciseInfo[7].removeRange(tempExerciseInfo[7].length-3,tempExerciseInfo[7].length)

                var eImage = 9
                //Leave the qoute because we need for url

                workout.addExercise(Exercise(tempExerciseInfo[3], tempExerciseInfo[5], tempExerciseInfo[7], tempExerciseInfo[9], tempExerciseInfo[1]))
                workout.setExerciseReps(count,reps[count].toInt())
                workout.setExerciseTime(count,times[count].toInt())

                println("Workout " + workout.getExerciseName(count))
                println("Reps " + workout.getExerciseReps(count))
                println("Time " + workout.getExerciseTime(count))
                println("Diff " + workout.getExerciseDifficulty(count))
                println("Cata " + workout.getExerciseCatagory(count))

                count += 1
                tempExerciseInfo.clear()
            }



            uiThread {

            }

        }
    }

}