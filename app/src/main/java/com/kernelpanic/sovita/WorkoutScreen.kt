package com.kernelpanic.sovita

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_screen)
        //setSupportActionBar(findViewById(R.id.toolbar))


        val intent = intent
        val workoutname : String? = intent.getStringExtra("workout")
        val workoutID : String? = intent.getStringExtra("workoutID")
        val workoutNameDisplay = findViewById<TextView>(R.id.workout_name)
        workoutNameDisplay.text = workoutname



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