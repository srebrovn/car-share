package com.srebrov.carshare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.ui.navigateUp
import com.srebrov.carshare.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var app: MyApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app = application as MyApplication

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        drawerLayout = binding.drawerLayout

        binding.toolbar.title = "Welcome"

        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration( // Set of all fragments that will be used..
            setOf(
                R.id.StationsFragment,
                R.id.LoginFragment,
                R.id.RegisterFragment,
                R.id.CarsFragment,
                R.id.ReservationFragment,
                R.id.MapFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main, StationsFragment()).commit()
        }
        supportActionBar?.setDisplayShowHomeEnabled(true) // Return me one level up not to root element

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.im_login -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main, LoginFragment()).commit()
            }
            R.id.im_locations -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main, StationsFragment()).commit()
            }
            R.id.im_map -> {
                supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.nav_host_fragment_content_main,
                        MapFragment()
                    )
                    .commit()
            }
//            R.id.it_logout -> {
//                Firebase.auth.signOut()
//                updateNavDrawer()
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.nav_host_fragment_content_main, LocationsFragment()).commit()
//            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun updateNavDrawer() {
//        val user = Firebase.auth.currentUser
//        if (user != null) {
//            binding.navView.menu.findItem(R.id.im_profile).isVisible = true
//            binding.navView.menu.findItem(R.id.im_login).isVisible = false
//            binding.navView.menu.findItem(R.id.it_logout).isVisible = true
//        } else {
//            binding.navView.menu.findItem(R.id.im_profile).isVisible = false
//            binding.navView.menu.findItem(R.id.im_login).isVisible = true
//            binding.navView.menu.findItem(R.id.it_logout).isVisible = false
//        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
//        sharedPref.edit().remove("ID").apply()
        super.onDestroy()
    }
}