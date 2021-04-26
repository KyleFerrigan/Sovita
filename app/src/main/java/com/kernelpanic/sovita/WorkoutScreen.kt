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

        // URL : http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000/Workouts/id/3
        doAsychCallWorkout("http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000/Workouts/id/3")


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


    private fun doAsychCallWorkout(url : String) {
        doAsync() {
            var result = URL(url).readText()
            val workoutInfo = ArrayList<String>()

            result = result.removeRange(0, 41) //Gets rid of the header
            //Gets rid of the extra }]} at the end
            result = result.removeRange(
                result.length - 3,
                result.length
            )

            //Result
            // "WorkoutID":3,"UserID":3,"ContainsExercises":"16,5,3,7,9","NumReps":"0,10,15,0,0","NumTime":"3,0,0,2,1","WorkoutName":"Test3"

            val split_result = result.split(",\"").toTypedArray()
            for (i in split_result) {
                val exercise_info = i.split("\":").toTypedArray() //Splits then up by row
                //count += 1
                for (j in exercise_info) {
                        //println("J")
                        //println(j)
                        workoutInfo.add(j)
                }
            }

            var idSlot = 1
            var useridSlot = 3
            var exercisesSlot = 5

            //Gets rid of the ""
            workoutInfo[5] = workoutInfo[5].removeRange(0,1)
            workoutInfo[5] = workoutInfo[5].removeRange(workoutInfo[5].length-1,workoutInfo[5].length)
            println("ExercisesID" + workoutInfo[5])

            var repsSlot = 7
            var timeSlot = 9
            var nameSlot = 11

            //val exerciseIDs = ArrayList<String>() //Holds workout Exercises
            //val reps = ArrayList<String>()
            //val times = ArrayList<String>()

            //Reps and time are in exercise object

            val exerciseIDs = workoutInfo[5].split(",").toTypedArray()
            println("SE")
            for (i in exerciseIDs){
                println(i)
            }

            val reps = workoutInfo[7].split(",").toTypedArray()
            val times = workoutInfo[9].split(",").toTypedArray()
            //for (i in workoutInfo[5]){
           //     exerciseIDs.add(i.toString())
           //     println("i" + i)
           // }

            // URL
            // http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000/Exercises/id/2

            // Pull Exercises Result
            // {"status":200,"error":null,"response":[{"ExerciseID":2,"ExerciseName":"Crunches","ExerciseCatagory":"Abdominals","ExerciseDifficulty":"Beginner\r","ExerciseImage":"https://sovita.s3.amazonaws.com/WorkoutGifs/crunches.gif"}]}

            /*for(i in 0..count-1){
                val name = workoutInfo[nameSlot]
                name.removeRange(0,1)
                name.removeRange(name.length-1,name.length)
                workoutName.add(name)
                workoutID.add(workoutInfo[idSlot])
                nameSlot += 12
                idSlot += 12

            }*/
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