package com.kernelpanic.sovita

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList


//Some of this code was obtained from YouTube and other helpful sources
class WorkoutScreen : AppCompatActivity() {
    private val START_TIME_IN_MILLIS: Long = 600000
    private var mTextViewCountDown: TextView? = null
    private var mButtonStartPause: Button? = null
    private var mButtonReset: Button? = null
    private var mCountDownTimer: CountDownTimer? = null
    private var mTimerRunning = false
    private var mTimeLeftInMillis = START_TIME_IN_MILLIS
    private lateinit var exercises : ArrayList<Exercise>
    //private lateinit var image : ImageView
    private lateinit var image : WebView

    //private lateinit var

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_screen)
        //setSupportActionBar(findViewById(R.id.toolbar))


        val intent = intent
        val workout: Workout = intent.getSerializableExtra("workout2") as Workout //Has the workout
        val workoutName = workout.getWorkoutName()
        val workoutNameDisplay = findViewById<TextView>(R.id.workout_name)
        workoutNameDisplay.text = workoutName

        //println("Workout exercises: " + workout.getExerciseIDs())
        //println("Reps: " + workout.getExerciseReps())
        //println("TIme: " + workout.getExerciseTimes())
        image = findViewById(R.id.webView)
        image.settings.setJavaScriptEnabled(true)


        //Need to get rid of the quotes I don't remember where the quotes are..idk this works though
        var link = workout.getExerciseImage(0).removeRange(0,1)
        link = link.removeRange(link.length-1,link.length)

        image.loadUrl(link)



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

    private inner class DownloadImageFromInternet(var imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
        init {
            Toast.makeText(applicationContext, "Please wait, it may take a few minute...",     Toast.LENGTH_SHORT).show()
        }
        override fun doInBackground(vararg urls: String): Bitmap? {
            val imageURL = urls[0]
            var image: Bitmap? = null
            try {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)
            }
            catch (e: Exception) {
                Log.e("Error Message", e.message.toString())
                e.printStackTrace()
            }
            return image
        }
        override fun onPostExecute(result: Bitmap?) {
            imageView.setImageBitmap(result)
        }
    }

}//end WorkoutScreen class