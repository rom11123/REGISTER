package com.example.registerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {

    lateinit var btSign:Button
    lateinit var login:EditText
    lateinit var password:EditText
    lateinit var text:TextView
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btSign = findViewById(R.id.login)
        login = findViewById(R.id.elogin)
        password = findViewById(R.id.e_password)
        text = findViewById(R.id.tvRedirectSignUp)
        auth = FirebaseAuth.getInstance()

        btSign.setOnClickListener {
            login()
        }

       text.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            // using finish() to end the activity
            finish()
        }


    }

    private fun login() {
        val email = login.text.toString()
        val pass = password.text.toString()
        // calling signInWithEmailAndPassword(email, pass)
        // function using Firebase auth object
        // On successful response Display a Toast
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
        }
    }
}