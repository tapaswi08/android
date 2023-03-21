package com.example.googlemap

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if(!task.isSuccessful){
                    Log.w("TAG","Fetching FCM registration token failed",task.exception)
                    return@OnCompleteListener
                }
            val token = task.result
            Log.e("TAG",token)
            })
        nameEditText = findViewById(R.id.etName)
        emailEditText = findViewById(R.id.etEmail)
        passwordEditText = findViewById(R.id.etPassword)
        loginButton = findViewById(R.id.btnLogin)
        loginButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please enter all the fields", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(name, email, password)
            }
        }
        var databaseobj = Database()
        println(databaseobj.addingUserDetails())
    }
    private fun loginUser(name: String, email: String, password: String) {
        ParseUser.logInInBackground(email, password) { user, e ->
            if (user != null) {
                Toast.makeText(this, "Login Successful ", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra("name", name)
                startActivity(intent)
                finish()
            } else {
                // display a toast message when user login fails.
                Toast.makeText(this, "Login failed: " + e?.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
