package com.example.assignment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    lateinit var txtRegister:TextView
    lateinit var etPhone:EditText
    lateinit var etPassword:EditText
    lateinit var txtLogin:TextView
    lateinit var txtFogotPassword:TextView
    lateinit var sharedPreferces:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferces = getSharedPreferences("Preferences",Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferces.getBoolean("isLoggedIn",false)
        if (isLoggedIn)
        {
            val intent = Intent(this@LoginActivity,HomeActivity::class.java)
            startActivity(intent)
            super.onPause()
            finish()
        }
        etPhone = findViewById(R.id.etPhone)
        etPassword = findViewById(R.id.etPassword)
        txtRegister = findViewById(R.id.txtRegister)
        txtLogin = findViewById(R.id.txtLogin)
        txtFogotPassword = findViewById(R.id.txtFogotPassword)

        txtRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
//            super.onPause()
//            finish()
        }

        txtLogin.setOnClickListener {
            val num = sharedPreferces.getString("etMobile","0")
            val pass = sharedPreferces.getString("etPassword","0")
            if(num == etPhone.text.toString() && pass == etPassword.text.toString())
            {
                sharedPreferces.edit().putBoolean("isLoggedIn",true).apply()
                val intent = Intent(this@LoginActivity,HomeActivity::class.java)
                startActivity(intent)
                super.onPause()
                finish()
            }
            else
            {
                Toast.makeText(this@LoginActivity, "Invalid Inputs", Toast.LENGTH_SHORT).show()
            }

        }
        txtFogotPassword.setOnClickListener {
            val intent = Intent(this@LoginActivity,ForgotActivity::class.java)
            startActivity(intent)
        }
    }
}