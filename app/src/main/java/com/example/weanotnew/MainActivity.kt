package com.example.weanotnew

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment)

        //val appBarConfiguration = AppBarConfiguration(setOf(R.id.notesFragment, R.id.weatherFragment, R.id.newsFragment))
        //setupActionBarWithNavController(navController, appBarConfiguration)

        val supportActionBar: ActionBar? = (this as AppCompatActivity).supportActionBar
        if (supportActionBar != null) supportActionBar.hide()

        bottomNavigationView.setupWithNavController(navController)
    }
}