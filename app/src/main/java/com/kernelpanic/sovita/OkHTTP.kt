package com.kernelpanic.sovita
import okhttp3.*
import java.io.*



public fun GetRequest(urlIn: String){

}
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
  PostData("http://ec2-13-58-150-155.us-east-2.compute.amazonaws.com:3000/Exercises", "ExerciseID", "93", "ExerciseName", "ServerTest");
}