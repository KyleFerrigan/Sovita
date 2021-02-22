package com.kernelpanic.sovita.ui.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kernelpanic.sovita.R
import com.kernelpanic.sovita.ui.meals.MealsViewModel

class MealsFragment : Fragment() {

    private lateinit var mealsViewModel: MealsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mealsViewModel =
                ViewModelProvider(this).get(MealsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_meals, container, false)
        val textView: TextView = root.findViewById(R.id.text_meals)
        mealsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}