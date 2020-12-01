package com.example.assignment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    lateinit var etName:EditText
    lateinit var etEmail:EditText
    lateinit var etMobile:EditText
    lateinit var etAddress:EditText
    lateinit var etRegisterPassword:EditText
    lateinit var etConfirmPassword:EditText
    lateinit var btnLogin:Button
    lateinit var sharedPreferences:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etMobile = findViewById(R.id.etMobile)
        etAddress = findViewById(R.id.etAddress)
        etRegisterPassword = findViewById(R.id.etRegisterPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnLogin = findViewById(R.id.btnLogin)

        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name),Context.MODE_PRIVATE)

        btnLogin.setOnClickListener {
            if (etName.text.toString().length < 3)
                Toast.makeText(this@RegisterActivity,"Name is too short", Toast.LENGTH_SHORT).show()

            else if (!(etEmail.text.toString().contains('@')) && !(etEmail.text.toString().contains('.')))
                Toast.makeText(this@RegisterActivity,"Invalid Email", Toast.LENGTH_SHORT).show()

            else if (etMobile.text.length !=10)
                Toast.makeText(this@RegisterActivity,"Invalid Phone", Toast.LENGTH_SHORT).show()
            
            else if (etRegisterPassword.text.toString()==etConfirmPassword.text.toString() && etConfirmPassword.text.toString().isNotEmpty())
            {
                sharedPreferences.edit().putBoolean("isLoggedIn",true).apply()
                sharedPreferences.edit().putString("etName", etName.text.toString()).apply()
                sharedPreferences.edit().putString("etEmail", etEmail.text.toString()).apply()
                sharedPreferences.edit().putString("etMobile", etMobile.text.toString()).apply()
                sharedPreferences.edit().putString("etAddress", etAddress.text.toString()).apply()
                sharedPreferences.edit().putString("etPassword", etRegisterPassword.text.toString()).apply()
                val intent = Intent(this@RegisterActivity,HomeActivity::class.java)
                startActivity(intent)
                super.onPause()
                finish()
            }
            else
                Toast.makeText(this@RegisterActivity,"Password Not match", Toast.LENGTH_SHORT).show()
        }

    }
}