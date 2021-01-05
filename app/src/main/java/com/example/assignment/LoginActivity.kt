package com.example.assignment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.widget.*

class LoginActivity : AppCompatActivity() {
    lateinit var googleLogin:Button
    lateinit var sharedPreferces:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        googleLogin = findViewById(R.id.btnLoginByGoogle)

        googleLogin.setOnClickListener{
            val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
            super.onPause()
            finish()
        }

    }
}