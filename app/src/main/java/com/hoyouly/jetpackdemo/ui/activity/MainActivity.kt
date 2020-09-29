package com.hoyouly.jetpackdemo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hoyouly.jetpackdemo.R

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigation: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val host: NavHostFragment =
//            supportFragmentManager.findFragmentById(R.id.my_nov_host_fragment) as NavHostFragment
//        val navController = host.navController

//        initWidget()
//        initBottomNavigation(bottomNavigation, navController)
    }

//    private fun initBottomNavigation(
//        bottomNavigation: BottomNavigationView,
//        navController: NavController
//    ) {
//        bottomNavigation.setupWithNavController(navController)
//    }
//
//    private fun initWidget() {
//        bottomNavigation = findViewById(R.id.navigation_view)
//    }
}
