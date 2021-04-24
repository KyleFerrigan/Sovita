package com.kernelpanic.sovita

import android.telecom.Call
import okhttp3.*
import java.io.*

public fun addWorkoutData(workoutIDIn: String, userIDIn: String, containsExercisesIn: String, numRepsin: String, numTimeIn: String, workoutNameIn: String) {//Input should be as follows Url,IDName,ID,parameterName,ParameterData

    // Add parameters
    val workoutID: String = workoutIDIn
    val userID: String = userIDIn
    val containsExercises: String = containsExercisesIn
    val numReps: String = numRepsin
    val numTime: String = numTimeIn
    val workoutName: String = workoutNameIn

    // Create a http request object.
    var builder: Request.Builder = Request.Builder()
    builder = builder.url("http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000" + "/Workouts/Create/"+workoutID+"/"+userID+"/"+containsExercises+"/"+numReps+"/"+numTime+"/"+workoutName)
    val request = builder.build()

    // Create a new Call object with post method.
    val call: okhttp3.Call = OkHttpClient().newCall(request)

    // Execute the request and get the response synchronously.
    val response = call.execute()
}

/*fun main() { //todo comment this out for the whole app to run
   addWorkoutData(
        "2", "3", "3,2,5",
        "2,4,6", "7,2,3", "kyleTest but in kotlin 5"
    );
}*/

//Dont worry about below just archive
/*fun oldpostver(workoutIDIn: String, userIDIn: String, containsExercisesIn: String, numRepsin: String, numTimeIn: String, workoutNameIn: String) {
    // Create okhttp3 form body builder.
    //val formBodyBuilder: FormBody.Builder = FormBody.Builder()

    // Add form parameters
    //formBodyBuilder.add("UserID", userIDIn)
    //formBodyBuilder.add("ContainsExercises", containsExercisesIn)
    //formBodyBuilder.add("NumReps", numRepsin)
    //formBodyBuilder.add("NumTime", numTimeIn)
    //formBodyBuilder.add("WorkoutName", workoutNameIn)

    // Build form body.
    //val formBody: FormBody = formBodyBuilder.build()

    //builder = builder.post(formBody)
}*/