package com.example.assignment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    lateinit var txtMessage:TextView
    lateinit var txtLogoutFromHome:TextView
    lateinit var sharedPreferences : SharedPreferences
    lateinit var imgLogoutFromHome:ImageButton

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        txtMessage = findViewById(R.id.txtMessage)
//        imgLogoutFromHome = findViewById(R.id.imgLogoutFromHome)
        txtLogoutFromHome = findViewById(R.id.txtLogoutFromHome)

        sharedPreferences = getSharedPreferences("Preferences",Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("etName","c")
        txtMessage.setText("$name")

//        imgLogoutFromHome.setOnClickListener{ logout() }
        txtLogoutFromHome.setOnClickListener { logout() }
    }

    fun logout() {
        sharedPreferences = getSharedPreferences("Preferences",Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isLoggedIn",false).apply()
        val intent = Intent(this@HomeActivity,LoginActivity::class.java)
        startActivity(intent)
        super.onPause()
        finish()
    }
}