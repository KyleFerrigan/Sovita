package com.kernelpanic.sovita

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL
import java.util.*


//Some of this code was obtained from YouTube and other helpful sources
class WorkoutScreen : AppCompatActivity() {
    private val START_TIME_IN_MILLIS: Long = 600000
    private var mTextViewCountDown: TextView? = null
    private var mButtonStartPause: Button? = null
    private var mButtonReset: Button? = null
    private var mCountDownTimer: CountDownTimer? = null
    private var mTimerRunning = false
    private var mTimeLeftInMillis = START_TIME_IN_MILLIS

    //private lateinit var

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_screen)
        //setSupportActionBar(findViewById(R.id.toolbar))


        val intent = intent
        val workoutname : String? = intent.getStringExtra("workout")
        val workoutID : String? = intent.getStringExtra("workoutID")
        val workoutNameDisplay = findViewById<TextView>(R.id.workout_name)
        workoutNameDisplay.text = workoutname

        val exercises = ArrayList<Exercise>() //This will hold all the exercises for the workout

        doAsychCallWorkout("http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000/Workouts/id/$workoutID",exercises)


        mTextViewCountDown = findViewById(R.id.text_timer_reps)
        mButtonStartPause = findViewById(R.id.button_start_pause)
        mButtonReset = findViewById(R.id.button_reset)

        mButtonStartPause?.run {
            setOnClickListener {
                fun onClick() {
                    if (mTimerRunning) {
                        pauseTimer()
                    } else {
                        startTimer()
                    }
                }
            }
        }
        mButtonReset?.run {
            setOnClickListener {
                fun onClick() {
                    resetTimer()
                }
            }
        }
        updateCountDownText()
    }


    private fun doAsychCallWorkout(url : String, exercises: ArrayList<Exercise>) {
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
                        println("J2")
                        println(j)
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

                //Create Exercise Objects
                exercises.add(Exercise(tempExerciseInfo[3],tempExerciseInfo[5],tempExerciseInfo[7],tempExerciseInfo[9],tempExerciseInfo[1]))
                exercises[count].setReps(reps[count].toInt())
                exercises[count].setTime(times[count].toInt())
                count += 1
                tempExerciseInfo.clear()
            }


            uiThread {

            }
        }
    }


    @SuppressLint("SetTextI18n")
    fun startTimer() { //start timer
        mCountDownTimer = object : CountDownTimer(mTimeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mTimeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                mTimerRunning = false
                mButtonStartPause!!.text = "Start"
                mButtonStartPause!!.visibility = View.INVISIBLE
                mButtonReset!!.visibility = View.VISIBLE
            }
        }.start()
        mTimerRunning = true
        mButtonStartPause!!.text = "pause"
        mButtonReset!!.visibility = View.INVISIBLE
    }

    @SuppressLint("SetTextI18n")
    fun pauseTimer() { //pause timer
        mCountDownTimer!!.cancel()
        mTimerRunning = false
        mButtonStartPause!!.text = "Start"
        mButtonReset!!.visibility = View.VISIBLE
    }

    fun resetTimer() { //reset timer
        mTimeLeftInMillis = START_TIME_IN_MILLIS
        updateCountDownText()
        mButtonReset!!.visibility = View.INVISIBLE
        mButtonStartPause!!.visibility = View.VISIBLE
    }

    fun updateCountDownText() { //updateCountDownText
        val minutes = (mTimeLeftInMillis / 1000).toInt() / 60
        val seconds = (mTimeLeftInMillis / 1000).toInt() % 60
        val timeLeftFormatted: String = java.lang.String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        mTextViewCountDown!!.text = timeLeftFormatted
    }

}//end WorkoutScreen class