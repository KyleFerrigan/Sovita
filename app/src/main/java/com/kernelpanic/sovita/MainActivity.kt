package com.kernelpanic.sovita

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import okhttp3.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_exercise, R.id.navigation_chat, R.id.navigation_meals))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
/*class okhttptest{
    OkHttpClient client = new OkHttpClient();

    JsonObject postData = new JsonObject()
    postData.addProperty("name", "morpheus");
    postData.addProperty("job", "leader");

    final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    RequestBody postBody = RequestBody.create(JSON, postData.toString());
    Request post = new Request.Builder()
    .url("https://reqres.in/api/users")
    .post(postBody)
    .build();

    client.newCall(post).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) {
            try {
                ResponseBody responseBody = response.body();
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                Log.i("data", responseBody.string());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}*/