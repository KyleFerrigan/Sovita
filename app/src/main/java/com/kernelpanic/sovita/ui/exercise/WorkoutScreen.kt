package com.kernelpanic.sovita.ui.exercise
import android.content.Intent
import com.kernelpanic.sovita.ui.exercise.Workout
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.webkit.WebView
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.kernelpanic.sovita.MainActivity
import com.kernelpanic.sovita.R
import java.util.*


//Some of this code was obtained from YouTube and other helpful sources
class WorkoutScreen : AppCompatActivity() {
    private lateinit var mButtonStartPause: Button
    private lateinit var mButtonPause: Button
    private var mCountDownTimer: CountDownTimer? = null
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var image: WebView

    private lateinit var repTime: TextView
    private lateinit var exercise_name: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout_screen)
        //setSupportActionBar(findViewById(R.id.toolbar))


        val intent = intent
        val workout: Workout = intent.getSerializableExtra("workout2") as Workout //Has the workout
        var workoutName = workout.getWorkoutName()

        val workoutNameDisplay = findViewById<TextView>(R.id.workout_name)
        workoutNameDisplay.text = workoutName

        //mTextViewCountDown = findViewById(R.id.text_timer_reps)
        mButtonStartPause = findViewById(R.id.button_start_pause)
        mButtonPause = findViewById(R.id.button_reset)
        nextButton = findViewById(R.id.next)
        prevButton = findViewById(R.id.back)

        image = findViewById(R.id.webView)
        image.settings.setJavaScriptEnabled(true)
        exercise_name = findViewById(R.id.exerciseName)

        repTime = findViewById(R.id.text_timer_reps)

        var count = 0 //keeps track of what exercise we are on


        //Need to get rid of the quotes
        var link = workout.getExerciseImage(count).removeRange(0, 1)
        link = link.removeRange(link.length - 1, link.length)
        image.loadUrl(link)

        exercise_name.setText(workout.getExerciseName(count).toString())

        if (workout.getExerciseReps(count) != 0) {
            repTime.setText(workout.getExerciseReps(count).toString()+ " Reps")
            mButtonStartPause.visibility = View.INVISIBLE
            mButtonPause.visibility = View.INVISIBLE
        } else {
            if (workout.getExerciseTime(count) < 10) {
                repTime.setText("0" + workout.getExerciseTime(count).toString() + ":00")
            } else {
                repTime.setText(workout.getExerciseTime(count).toString() + ":00")
            }

            mButtonStartPause.visibility = View.VISIBLE
            mButtonPause.visibility = View.VISIBLE
        }


        nextButton.setOnClickListener {
            count += 1

            if (count == workout.getNumExercises()) {
                val intent = Intent(this@WorkoutScreen, WorkoutFinished::class.java)
                startActivity(intent)
            } else {
                mCountDownTimer?.cancel() //In case the user clicks next before the timer runs out
                var link = workout.getExerciseImage(count).removeRange(0, 1)
                link = link.removeRange(link.length - 1, link.length)
                image.loadUrl(link)

                exercise_name.setText(workout.getExerciseName(count).toString())

                if (workout.getExerciseReps(count) != 0) {
                    repTime.setText(workout.getExerciseReps(count).toString() + " Reps")
                    mButtonStartPause.visibility = View.INVISIBLE
                    mButtonPause.visibility = View.INVISIBLE
                } else {
                    if (workout.getExerciseTime(count) < 10) {
                        repTime.setText("0" + workout.getExerciseTime(count).toString() + ":00")
                    } else {
                        repTime.setText(workout.getExerciseTime(count).toString() + ":00")
                    }
                    mButtonStartPause.visibility = View.VISIBLE
                    mButtonPause.visibility = View.VISIBLE
                }
            }

        }

        prevButton.setOnClickListener {
            count = count - 1
            mCountDownTimer?.cancel() //In case the user clicks prev before the timer runs out
            var link = workout.getExerciseImage(count).removeRange(0, 1)
            link = link.removeRange(link.length - 1, link.length)
            image.loadUrl(link)

            exercise_name.setText(workout.getExerciseName(count).toString())

            if (workout.getExerciseReps(count) != 0) {
                repTime.setText(workout.getExerciseReps(count).toString() + " Reps")
                mButtonStartPause.visibility = View.INVISIBLE
                mButtonPause.visibility = View.INVISIBLE
                //true
            } else {
                if (workout.getExerciseTime(count) < 10) {
                    repTime.setText("0" + workout.getExerciseTime(count).toString() + ":00")
                } else {
                    repTime.setText(workout.getExerciseTime(count).toString() + ":00")
                }
                mButtonStartPause.visibility = View.VISIBLE
                mButtonPause.visibility = View.VISIBLE
            }
        }

        var boolPause = false // false means it's not paused
        var boolStart = false //Means it's starting didn't click reset

        mButtonStartPause.setOnClickListener {
            if (boolStart == false) {
                startTimeCounter(repTime,workout.getExerciseTime(count))
                mButtonStartPause.setText("Reset")
                if (boolPause == true) {
                    mButtonPause.setText("Pause")
                    boolPause = false
                }
                boolStart = true

            } else {
                mCountDownTimer?.cancel()
                startTimeCounter(repTime,workout.getExerciseTime(count))
                if (boolPause == true) {
                    mButtonPause.setText("Pause")
                    boolPause = false
                }
                boolStart = false
            }

        }

        mButtonPause.setOnClickListener {
            if (boolPause == false) {
                mCountDownTimer?.cancel()
                mButtonPause.setText("Resume")
                boolPause = true
            } else {
                mCountDownTimer?.start()
                mButtonPause.setText("Pause")
                boolPause = false
            }

        }

    }
    fun startTimeCounter(view: TextView, count : Int) {
        var mTimeLeftInMillis = (count*60000).toLong()
        //val countTime: TextView = findViewById(R.id.text_timer_reps)
        mCountDownTimer = object : CountDownTimer(mTimeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (mTimeLeftInMillis / 1000).toInt() / 60
                val seconds = (mTimeLeftInMillis / 1000).toInt() % 60
                val timeLeftFormatted: String = java.lang.String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
                view.setText(timeLeftFormatted.toString())
                mTimeLeftInMillis = mTimeLeftInMillis - 1000
            }
            override fun onFinish() {
                view.setText("Finished")
            }
        }.start()
    }
}