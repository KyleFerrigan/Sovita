package com.kernelpanic.sovita.ui.exercise

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kernelpanic.sovita.Exercise
import com.kernelpanic.sovita.NewWorkout
import com.kernelpanic.sovita.R
import com.kernelpanic.sovita.Workout



class ExerciseFragment: Fragment()  {
    private lateinit var exerciseViewModel: ExerciseViewModel

    private lateinit var exerciseTextView: TextView
    private lateinit var workoutButton: FloatingActionButton

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


        //This makes the + button on the exercise screen work so that it leads you to the new workout screen
        val workoutButton = view?.findViewById<FloatingActionButton>(R.id.fabExHome)
        workoutButton?.setOnClickListener{
            val intent = Intent(activity, NewWorkout::class.java)
            startActivity(intent)
        }

        //I don't know what this is for now so I commented it out -- exerciseViewModel.text.observe(viewLifecycleOwner, Observer {
        //})



        return view
        }
}