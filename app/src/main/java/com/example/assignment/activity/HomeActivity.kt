package com.example.assignment.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.assignment.R
import com.example.assignment.fragments.DashboardFragment
import com.example.assignment.fragments.FavoriteFragment
import com.example.assignment.fragments.ProfileFragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    lateinit var DrawerLayout: DrawerLayout
    lateinit var CoordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var Frame: FrameLayout
    lateinit var navigationView: NavigationView
    lateinit var sharedPreferences: SharedPreferences

    var previousMenuItem:MenuItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        DrawerLayout = findViewById(R.id.drawerLayout)
        toolbar = findViewById(R.id.toolbar)
        Frame = findViewById(R.id.frame)
        CoordinatorLayout = findViewById(R.id.coordinatorLayout)
        navigationView = findViewById(R.id.navigationView)
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        setUpToolbar()
        openDashboard()

        val actionBarDrawerToggle = ActionBarDrawerToggle(this@HomeActivity,
                DrawerLayout, R.string.open_drawer, R.string.close_drawer)

        DrawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {
            if (previousMenuItem !=null)
                previousMenuItem?.isChecked = false
            it.isCheckable = true
            it.isChecked = true
            previousMenuItem = it
            when(it.itemId){
                R.id.home ->{
                    openDashboard()
                    DrawerLayout.closeDrawers()
                }
                R.id.favorite ->{
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frame, FavoriteFragment()).commit()
                    supportActionBar?.title = "Favorite"
                    DrawerLayout.closeDrawers()
                }
                R.id.profile ->{
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frame, ProfileFragment()).commit()
                    supportActionBar?.title = "Profile"
                    DrawerLayout.closeDrawers()
                }
                R.id.logout ->{
//                    todo
                    Firebase.auth.signOut()
                    DrawerLayout.closeDrawers()
                    sharedPreferences.edit().putBoolean("isLoggedIn",false).apply()
                    val intent = Intent(this@HomeActivity, LoginActivity::class.java)
                    startActivity(intent)
                    super.onPause()
                    finish()
                }

            }
            return@setNavigationItemSelectedListener true
        }
    }
    fun openDashboard(){
        val fragment = DashboardFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame,fragment).commit()
        supportActionBar?.title = "Dashboard"
        navigationView.setCheckedItem(R.id.home)
    }
    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="Toolbar Title"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == android.R.id.home){
            DrawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frame)
        when(frag){
            !is DashboardFragment -> openDashboard()
            else -> super.onBackPressed()
        }
    }
    private fun signOut() {
        // [START auth_sign_out]
        Firebase.auth.signOut()

        // [END auth_sign_out]
    }

}