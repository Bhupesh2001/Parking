package com.example.assignment.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.assignment.R
import com.example.assignment.activity.fragment.FavoriteFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var DrawerLayout: DrawerLayout
    lateinit var CoordinatorLayout: CoordinatorLayout
    lateinit var toolbar: Toolbar
    lateinit var Frame: FrameLayout
    lateinit var navigationView: NavigationView

    var previousMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DrawerLayout = findViewById(R.id.drawerLayout)
        toolbar = findViewById(R.id.toolbar)
        Frame = findViewById(R.id.frame)
        CoordinatorLayout = findViewById(R.id.coordinatorLayout)
        navigationView = findViewById(R.id.navigationView)
        setUpToolbar()
        openDashboard()

        val actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity,
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
                R.id.dashboard ->{
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
                            .replace(R.id.frame, FavoriteFragment()).commit()
                    supportActionBar?.title = "Profile"
                    DrawerLayout.closeDrawers()
                }
                R.id.logout ->{
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frame, FavoriteFragment()).commit()
                    supportActionBar?.title = "About App"
                    DrawerLayout.closeDrawers()
                }

            }

            return@setNavigationItemSelectedListener true
        }
    }
    fun openDashboard(){
        val fragment = FavoriteFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame,fragment).commit()
        supportActionBar?.title = "Dashboard"
        navigationView.setCheckedItem(R.id.dashboard)
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
            !is FavoriteFragment -> openDashboard()
            else -> super.onBackPressed()
        }
    }
}