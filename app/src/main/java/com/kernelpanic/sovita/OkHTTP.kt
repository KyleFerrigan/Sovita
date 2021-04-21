package com.kernelpanic.sovita
import okhttp3.*
import java.io.*



public fun GetRequest(urlIn: String){

}
public fun PostWorkoutData(workoutIDIn: String, userIDIn: String, containsExercisesIn: String) {//Input should be as follows Url,IDName,ID,parameterName,ParameterData
    val client = OkHttpClient()

    val formBody: FormBody = okhttp3.FormBody.Builder()
        .add("WorkoutID", workoutIDIn)    //id info
        .add("UserID", userIDIn) //data to change
        .add("ContainsExercises", containsExercisesIn)
        .build()

    val request: Request = okhttp3.Request.Builder()
        .url("http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000/Exercises")//url to execute on
        .post(formBody)
        .build()

    val call = client.newCall(request)
    val response = call.execute()
}

//Modular attempt below, not gonna happen
public fun PostData(urlIn: String, idName: String, idIn: String, paramName: String, valueIn: String) {//Input should be as follows Url,IDName,ID,parameterName,ParameterData
    val client = OkHttpClient()
    val formBody: FormBody = okhttp3.FormBody.Builder()
        .add(idName, idIn)    //id info
        .add(paramName, valueIn) //data to change
        .build()

    val request: Request = okhttp3.Request.Builder()
        .url(urlIn)//url to execute on
        .post(formBody)
        .build()

    try {
        val response = client.newCall(request).execute()

        // Do something with the response.
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

fun main(){
    PostWorkoutData("2", "3", "3,2,5");
    //PostData("http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000/Exercises", "ExerciseID", "93", "ExerciseName", "ServerTest");
}


