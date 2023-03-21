package com.example.googlemap

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val sharedPreferences = getSharedPreferences("login_details", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("name", "")
        val email = sharedPreferences.getString("email", "")
        val password = sharedPreferences.getString("password", "")

        if (name != "" && email != "" && password != "") {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}
