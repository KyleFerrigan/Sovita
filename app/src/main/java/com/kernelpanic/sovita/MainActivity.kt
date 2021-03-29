package com.kernelpanic.sovita

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.SignInButton
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    class MainActivity : AppCompatActivity() {

        var button: SignInButton? = null
        private val RC_SIGN_IN = 2
        var mGoogleSignInClient: GoogleSignInClient? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val navView: BottomNavigationView = findViewById(R.id.nav_view)
            val navController = findNavController(R.id.nav_host_fragment)
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.navigation_home,
                    R.id.navigation_exercise,
                    R.id.navigation_chat,
                    R.id.navigation_meals
                )
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)


            // get reference to all views
            val et_user_name = findViewById<EditText>(R.id.et_user_name)
            val et_password = findViewById<EditText>(R.id.et_password)
            val btn_reset = findViewById<Button>(R.id.btn_reset)
            val btn_submit = findViewById<Button>(R.id.btn_submit)

            btn_reset.setOnClickListener{
                et_user_name.run { setText("") }
                et_password.setText("")
            }

            btn_submit.setOnClickListener{
                val user_name = et_user_name.text
                val password = et_password.text
                Toast.makeText(this@MainActivity, user_name, Toast.LENGTH_LONG).show()
            }


        }
    }
}