package com.example.assignment.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.assignment.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val startAct = Intent(this@SplashActivity, PhoneLoginActivity::class.java)
            startActivity(startAct)
            super.onPause()
            finish()
        },2000)
    }
}