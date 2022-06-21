package com.amydvdev.themoviedb.ui.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.amydvdev.themoviedb.R
import com.amydvdev.themoviedb.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupActionBarNavController()
    }

    private fun setupActionBarNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_popular_movies) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_popular_movies))
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, dest, _ ->
            supportActionBar?.title = dest.label
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}