package com.kernelpanic.sovita

import okhttp3.*
import java.io.*


public fun PostWorkoutData(workoutIDIn: String, userIDIn: String, containsExercisesIn: String) {//Input should be as follows Url,IDName,ID,parameterName,ParameterData

    // Create okhttp3 form body builder.
    val formBodyBuilder: FormBody.Builder = FormBody.Builder()

    // Add form parameters
    formBodyBuilder.add("WorkoutID", workoutIDIn)
    formBodyBuilder.add("UserID", userIDIn)
    formBodyBuilder.add("ContainsExercises", containsExercisesIn)

    // Build form body.
    val formBody: FormBody = formBodyBuilder.build()

    // Create a http request object.
    var builder: Request.Builder = Request.Builder()
    builder = builder.url("http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000" + "/Workouts/")
    builder = builder.post(formBody)
    val request = builder.build()

    // Create a new Call object with post method.
    val call: Call = OkHttpClient().newCall(request)

    // Execute the request and get the response synchronously.
    val response = call.execute()
}

fun main(){//todo comment this out for the whole app to run
    PostWorkoutData("2", "3", "3,2,5");
}


