package com.kernelpanic.sovita.ui.exercise

import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kernelpanic.sovita.Exercise
import com.kernelpanic.sovita.NewWorkout
import com.kernelpanic.sovita.R
import com.kernelpanic.sovita.Workout
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import java.net.URL
import android.widget.ArrayAdapter
import org.jetbrains.anko.uiThread


class ExerciseFragment: Fragment()  {
    private lateinit var exerciseViewModel: ExerciseViewModel

    private lateinit var exerciseTextView: TextView
    private lateinit var workoutButton: FloatingActionButton

    private lateinit var workout: Workout
    private lateinit var userWorkouts: ArrayList<Workout>
    private lateinit var listWorkouts:ListView


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

        println("Final Workout Names")
        for (i in 0..(workoutNames.size-1)){
            println(workoutNames[i])
        }




        //This makes the + button on the exercise screen work so that it leads you to the new workout screen
        val workoutButton = view?.findViewById<FloatingActionButton>(R.id.fabExHome)
        workoutButton?.setOnClickListener{
            val intent = Intent(this.activity, NewWorkout::class.java)
            startActivity(intent)
        }




        //I don't know what this is for now so I commented it out -- exerciseViewModel.text.observe(viewLifecycleOwner, Observer {
        //})



        return view
        }

    private fun doAsychcall(url: String, workoutName: ArrayList<String>, listworkouts: ListView, workoutID: ArrayList<String>) {
        doAsync() {
            println("Here!!!")
            var result = URL(url).readText()
            //URL(url).apply { }

            println("Result")
            println(result)
            val workoutInfo = ArrayList<String>()

            result = result.removeRange(0, 40) //Gets rid of the header
            result = result.removeRange(
                result.length - 3,
                result.length
            ) //Gets rid of the extra }]} at the end
            println("Result after getting rid of extra")
            println(result)

            var count = 0 //Holds the number of workouts there are

            val split_result = result.split("},{\"").toTypedArray() //Splits then up by row
            for (i in split_result) {
                val exercise_info = i.split(",\"").toTypedArray() //Splits then up by row
                count += 1
                for (j in exercise_info) {
                    val exercise_info2 = j.split("\":").toTypedArray() //Splits then up by row
                    for (k in exercise_info2) {
                        println("K")
                        println(k)
                        workoutInfo.add(k)
                    }
                }
            }

            println("Count Final")
            println(count)

            var idSlot = 1
            var useridSlot = 3
            var exercisesSlot = 5
            var repsSlot = 7
            var timeSlot = 9
            var nameSlot = 11

            for(i in 0..count-1){
                println("For loop")

                val name = workoutInfo[nameSlot]
                name.removeRange(0,1)
                name.removeRange(name.length-1,name.length)
                workoutName.add(name)
                workoutID.add(workoutInfo[idSlot])
                println("Name added:" + name)
                nameSlot += 12

            }
            uiThread {
                val adapter = activity?.let {
                    ArrayAdapter(
                        it,
                        android.R.layout.simple_list_item_1,workoutName
                    )
                }

                val textView = view?.findViewById<TextView>(R.id.test)

                listWorkouts.adapter = adapter
                listWorkouts.onItemClickListener =
                    AdapterView.OnItemClickListener { parent, view, position, id ->
                        val selectedItemText = parent.getItemAtPosition(position)
                        val selectedWorkout = workoutID[position]
                        if (textView != null) {
                            textView.text = "Selected : $selectedItemText"
                        }
                    }
            }
        }
    }



}