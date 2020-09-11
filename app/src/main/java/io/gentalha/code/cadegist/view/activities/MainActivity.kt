package io.gentalha.code.cadegist.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.gentalha.code.cadegist.R

class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment)
    }
    private val bottomNavView: BottomNavigationView by lazy {
        findViewById(R.id.mainBottomNavigation)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNevView()
    }

    private fun setupBottomNevView() {
        bottomNavView.setupWithNavController(navController)
    }
}