package com.example.assignment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ResetPasswordActivity : AppCompatActivity() {
    lateinit var etNewPassword:EditText
    lateinit var etConfirmNewPassword:EditText
    lateinit var btnDone:Button
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        etNewPassword = findViewById(R.id.etNewPassword)
        etConfirmNewPassword = findViewById(R.id.etConfirmNewPassword)
        btnDone = findViewById(R.id.btnDone)
        sharedPreferences = getSharedPreferences("Preference",Context.MODE_PRIVATE)

        btnDone.setOnClickListener {

            if (etConfirmNewPassword.text.toString() == etNewPassword.text.toString()
                    && etNewPassword.text.isNotEmpty())
            {
                sharedPreferences.edit().remove("etPassword").apply()
               sharedPreferences.edit().putString("etPassword",etNewPassword.text.toString()).apply()
                Toast.makeText(this@ResetPasswordActivity, "Password Changed", Toast.LENGTH_SHORT).show()
                super.onPause()
                finish()
            }
            else
                Toast.makeText(this@ResetPasswordActivity, "Not Matched", Toast.LENGTH_SHORT).show()
        }
    }
}