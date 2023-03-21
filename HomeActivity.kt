package com.example.googlemap

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseUser

class HomeActivity : AppCompatActivity() {

    // creating a variable for our text view
    private lateinit var userNameTV: TextView

    // button for logout
    private lateinit var logoutBtn: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        logoutBtn = findViewById(R.id.idBtnLogout)

        // initializing our variables
        userNameTV = findViewById(R.id.idTVUserName)

        // getting data from intent.
        val name = intent.getStringExtra("username")

        // setting data to our text view.
        userNameTV.text = name

        // initializing click listener for logout button
        logoutBtn.setOnClickListener {
            // calling a method to logout our user.
            ParseUser.logOutInBackground { e ->
                if (e == null) {
                    Toast.makeText(this, "User Logged Out", Toast.LENGTH_SHORT).show()
                    val i = Intent(this, LoginActivity::class.java)
                    startActivity(i)
                    finish()
                }
            }
        }
    }
}
