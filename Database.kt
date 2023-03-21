package com.example.googlemap

import android.util.Base64
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging

data class User(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val fcmtoken: String = ""
)
class Database {
    fun addingUserDetails() {
        val db = Firebase.firestore
        val usersCollectionRef = db.collection("users")
        val password= "anjana"
        val passwordBytes= password.toByteArray()
        val encodedPassword = Base64.encodeToString(passwordBytes,Base64.DEFAULT)
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            val newUser = User(
                name = "Tapaswi",
                email = "tapaswianjana@gmail.com",
                password = encodedPassword,
                fcmtoken = token
            )
            usersCollectionRef.add(newUser)
                .addOnSuccessListener {
                    Log.d("Firestore", "User added successfully with ID: ${it.id}")
                }
                .addOnFailureListener {
                    Log.e("Firestore", "Error adding user", it)
                }
            usersCollectionRef.whereEqualTo("email", "tapaswianjana@gmail.com")
                .get()
                .addOnSuccessListener { result ->
                    if (!result.isEmpty) {
                        val user = result.documents[0].toObject(User::class.java)
                        Log.d("Firestore", "Retrieved user: $user")
                    } else {
                        Log.d("Firestore", "User not found")
                    }
                }
                .addOnFailureListener {
                    Log.e("Firestore", "Error retrieving user", it)
                }
        })
    }
}
