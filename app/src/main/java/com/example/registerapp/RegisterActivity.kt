package com.example.registerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    lateinit var btReg:Button
    lateinit var edLogin:EditText
    lateinit var edPassword:EditText
    lateinit var confPassword:EditText
    lateinit var tvRedirekt:TextView

  private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btReg = findViewById(R.id.register_button)
        edLogin = findViewById(R.id.login1)
        edPassword = findViewById(R.id.edit_password)
        confPassword = findViewById(R.id.edit_confirm)
        tvRedirekt = findViewById(R.id.tvRedirectLogin)
        auth = Firebase.auth

        btReg.setOnClickListener {
            signUpUser()
        }
        tvRedirekt.setOnClickListener {
            val intent = Intent(this,Login::class.java)
            startActivity(intent)
        }
    }

    private fun signUpUser() {
        val email = edLogin.text.toString()
        val pass = edPassword.text.toString()
        val confirmPassword = confPassword.text.toString()

        // check pass
        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confirmPassword) {
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
                .show()
            return
        }
        // If all credential are correct
        // We call createUserWithEmailAndPassword
        // using auth object and pass the
        // email and pass in it.
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}