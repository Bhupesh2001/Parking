package com.example.assignment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ForgotActivity : AppCompatActivity() {
    lateinit var etForgotName:EditText
    lateinit var etForgotPhone:EditText
    lateinit var etForgotEmail:EditText
    lateinit var btnVerify:Button
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        etForgotName = findViewById(R.id.etForgotName)
        etForgotEmail = findViewById(R.id.etForgotEmail)
        etForgotPhone = findViewById(R.id.etForgotPhone)
        btnVerify = findViewById(R.id.btnVerify)
        sharedPreferences = getSharedPreferences("Preferences",Context.MODE_PRIVATE)

        btnVerify.setOnClickListener {
            val name = etForgotName.text.toString()
            val email = etForgotEmail.text.toString()
            val phone = etForgotPhone.text.toString()

            if (name == sharedPreferences.getString("etName"," ") &&
                email == sharedPreferences.getString("etEmail"," ") &&
                phone == sharedPreferences.getString("etMobile"," "))
            {
                val intent  = Intent(this@ForgotActivity,ResetPasswordActivity::class.java)
                startActivity(intent)
                super.onPause()
                finish()
            }
            else
                Toast.makeText(this@ForgotActivity, "Not Matched", Toast.LENGTH_SHORT).show()
        }
    }
}