package com.example.assignment.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.assignment.R

class RegisterActivity : AppCompatActivity() {

    lateinit var atv:AutoCompleteTextView
    val states = arrayListOf("Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand", "Karnataka",
                             "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana",
                             "Tripura", "Uttarakhand", "Uttar Pradesh", "West Bengal", "Andaman and Nicobar Islands", "Chandigarh", "Dadra and Nagar Haveli", "Daman and Diu", "Delhi",
                             "Lakshadweep", "Puducherry")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        atv = findViewById(R.id.etState)
        atv.setAdapter(ArrayAdapter<String>(this@RegisterActivity, R.layout.support_simple_spinner_dropdown_item,states))
    }
}

//
//        etName = findViewById(R.id.etName)
//        etEmail = findViewById(R.id.etEmail)
//        etMobile = findViewById(R.id.etMobile)
//        etAddress = findViewById(R.id.etAddress)
//        etRegisterPassword = findViewById(R.id.etRegisterPassword)
//        etConfirmPassword = findViewById(R.id.etConfirmPassword)
//        btnLogin = findViewById(R.id.btnLogin)
//
//        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name),Context.MODE_PRIVATE)
//
//        btnLogin.setOnClickListener {
//            if (etName.text.toString().length < 3)
//                Toast.makeText(this@RegisterActivity,"Name is too short", Toast.LENGTH_SHORT).show()
//
//            else if (!(etEmail.text.toString().contains('@')) && !(etEmail.text.toString().contains('.')))
//                Toast.makeText(this@RegisterActivity,"Invalid Email", Toast.LENGTH_SHORT).show()
//
//            else if (etMobile.text.length !=10)
//                Toast.makeText(this@RegisterActivity,"Invalid Phone", Toast.LENGTH_SHORT).show()
//
//            else if (etRegisterPassword.text.toString()==etConfirmPassword.text.toString() && etConfirmPassword.text.toString().isNotEmpty())
//            {
//                sharedPreferences.edit().putBoolean("isLoggedIn",true).apply()
//                sharedPreferences.edit().putString("etName", etName.text.toString()).apply()
//                sharedPreferences.edit().putString("etEmail", etEmail.text.toString()).apply()
//                sharedPreferences.edit().putString("etMobile", etMobile.text.toString()).apply()
//                sharedPreferences.edit().putString("etAddress", etAddress.text.toString()).apply()
//                sharedPreferences.edit().putString("etPassword", etRegisterPassword.text.toString()).apply()
//                val intent = Intent(this@RegisterActivity,HomeActivity::class.java)
//                startActivity(intent)
//                super.onPause()
//                finish()
//            }
//            else
//                Toast.makeText(this@RegisterActivity,"Password Not match", Toast.LENGTH_SHORT).show()
//        }
