package com.example.assignment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.widget.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GithubAuthProvider
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

const val REQUEST_CODE_SIGN_IN = 0
class LoginActivity : AppCompatActivity() {
    lateinit var googleLogin:Button
    lateinit var auth:FirebaseAuth
    lateinit var sharedPreferences:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name),Context.MODE_PRIVATE)
        if(sharedPreferences.getBoolean("isLoggedIn",false))
        {
            openHome()
        }
        googleLogin = findViewById(R.id.btnLoginByGoogle)
        auth = FirebaseAuth.getInstance()


        googleLogin.setOnClickListener{
            val option = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.webclient_id))
                        .requestEmail().build()
            val signInClient = GoogleSignIn.getClient(this,option)
            signInClient.signInIntent.also {
                startActivityForResult(it, REQUEST_CODE_SIGN_IN)
            }
        }

    }

    private fun googleAuthForFirebase(account:GoogleSignInAccount)
    {
        val credential = GoogleAuthProvider.getCredential(account.idToken,null)
        CoroutineScope(Dispatchers.IO).launch{
            try{
                auth.signInWithCredential(credential).await()
//                    .await()
                withContext(Dispatchers.Main){
                    sharedPreferences.edit().putBoolean("isLoggedIn",true).apply()
                    openHome()
                }
            } catch (e : Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_SIGN_IN)
        {
            val account = GoogleSignIn.getSignedInAccountFromIntent(data).result
            account?.let {
                googleAuthForFirebase(it)
            }
        }
    }

    fun openRegister(){
        val intent = Intent(this@LoginActivity,RegisterActivity::class.java)
        startActivity(intent)
        super.onPause()
        finish()
    }
    fun openHome(){
        val intent = Intent(this@LoginActivity,HomeActivity::class.java)
        startActivity(intent)
        super.onPause()
        finish()
    }
}