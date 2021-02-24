package com.kernelpanic.sovita.ui.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kernelpanic.sovita.Exercise
import com.kernelpanic.sovita.R
import com.kernelpanic.sovita.Workout

class ExerciseFragment : Fragment() {
    private lateinit var exerciseViewModel: ExerciseViewModel
    private lateinit var workouts: Workout
    private lateinit var exercises: Exercise
    private lateinit var exerciseTextView: TextView


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        exerciseViewModel =
                ViewModelProvider(this).get(ExerciseViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_exercise, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        exerciseViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root



    }


}