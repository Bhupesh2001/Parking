package com.example.assignment.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.*
import com.example.assignment.R
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class PhoneLoginActivity : AppCompatActivity() {
    lateinit var txtotp: EditText
    lateinit var phnNoTxt: EditText
    lateinit var btnSendOtp: TextView
    lateinit var btnCheckOtp: TextView
    lateinit var mAuth: FirebaseAuth
    lateinit var progressBar: ProgressBar
    lateinit var mVerificationId: String
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_login)

        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        if(sharedPreferences.getBoolean("isLoggedIn",false))
        {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            super.onPause()
            finish()
        }
        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.INVISIBLE

        btnSendOtp = findViewById(R.id.btnSendOtp)
        btnCheckOtp = findViewById(R.id.btnCheckOtp)
        phnNoTxt = findViewById(R.id.phnNoTxt)
        txtotp = findViewById(R.id.txtOtp)
        mAuth = FirebaseAuth.getInstance()
        var otpSent = false

        btnSendOtp.setOnClickListener {
            val number = phnNoTxt.text.toString()
            if(number.length==10) {
                sendVerification(number)
                otpSent = true
                progressBar.visibility = View.VISIBLE
            }
            else{
                phnNoTxt.error = "Not a valid number"
                // Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT).show()
            }
        }

        btnCheckOtp.setOnClickListener {
            if (otpSent) {
                val code = txtotp.text.toString()
                when {
                    phnNoTxt.text.toString().length != 10 -> phnNoTxt.error = "Enter a valid Phone Number"
                    code.length == 6 -> verifyVerificationCode(code)
                    else -> txtotp.error = "Enter a valid OTP"
                }
            }
            else
                Toast.makeText(this, "Request an OTP first", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendVerification(number: String?) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + number,
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks)
    }

    private val mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            val code: String? = p0.smsCode
            if (code != null) {
                txtotp.text = Editable.Factory.getInstance().newEditable(code)
            }
            progressBar.visibility = View.INVISIBLE
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            Toast.makeText(this@PhoneLoginActivity, p0.message, Toast.LENGTH_SHORT).show()
        }

        override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(p0, p1)
            mVerificationId = p0
        }
    }

    private fun verifyVerificationCode(code: String) {
        val credentials: PhoneAuthCredential = PhoneAuthProvider.getCredential(mVerificationId, code)
        signInWithPhoneAuthCredential(credentials)
    }

    private fun signInWithPhoneAuthCredential(credentials: PhoneAuthCredential) {
        mAuth.signInWithCredential(credentials).addOnCompleteListener(this@PhoneLoginActivity) { p0 ->
            if (p0.isSuccessful) {
                Toast.makeText(this@PhoneLoginActivity, "Welcome", Toast.LENGTH_SHORT).show()
                sharedPreferences.edit().putBoolean("isLoggedIn",true).apply()
                startActivity(Intent(this@PhoneLoginActivity, HomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this@PhoneLoginActivity, "Wrong OTP", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

