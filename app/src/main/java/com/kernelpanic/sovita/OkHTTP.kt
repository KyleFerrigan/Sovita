package com.kernelpanic.sovita

import okhttp3.*
import java.io.*


class OkHTTP {
    fun GetRequest(urlIn: String){

    }
    fun PostData(urlIn: String, idName: String, idIn: String, nameIn: String, valueIn: String){
        val client = OkHttpClient()
        val formBody: FormBody = okhttp3.FormBody.Builder()
            .add(idName,idIn)    //id info
            .add(nameIn,valueIn) //data to change

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
}