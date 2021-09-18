package com.example.bags

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.bags.databinding.ActivityMainBinding
import com.example.bags.framework.PreferenceManager
import com.example.core.data.IPreferenceHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class MainActivity : AppCompatActivity(), myDrawerController {
    companion object {
        const val TAG = "MainActivity"
    }

    private val navController: NavController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment_content_main)
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var fab: FloatingActionButton
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var preferenceHelper: IPreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferenceHelper = PreferenceManager(this.applicationContext)
        drawerLayout = binding.drawerLayout
        navigationView = binding.navigationView
        toolbar = binding.appBarMain.toolbar
        fab = binding.appBarMain.floatingActionButton
        bottomNavigationView = binding.appBarMain.contentMain.bottomNavigationView
        setSupportActionBar(toolbar)
        fab.setOnClickListener {
            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

        }
        bottomNavigationView.setupWithNavController(navController)
        navigationView.setupWithNavController(navController)
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.deleteUser -> {
                    if (FirebaseAuth.getInstance().currentUser != null) {
                        FirebaseAuth.getInstance().currentUser?.delete()
                        preferenceHelper.setUserLoggedIn(false)
                        navController.navigate(R.id.action_global_nested_graph_login)
                        Log.d(TAG, FirebaseAuth.getInstance().currentUser.toString())
                        true
                    } else {
                        true
                    }
                }
                else -> true
            }
        }
        // if (it.itemId== R.id.deleteUser)FirebaseAuth.getInstance().currentUser.delete()

        //to hide up button when this fragments selected
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.cartFragment,
                R.id.searchFragment,
                R.id.favoriteFragment,
                R.id.homeFragment
            ), drawerLayout
        )
        //to change title automatically
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_Logout -> {
                Log.d(TAG, "Selected :  $item")
                try {
                    run {
                        FirebaseAuth.getInstance().signOut()
                        preferenceHelper.setUserLoggedIn(false)
                        navController.navigate(R.id.action_global_nested_graph_login)
                        Log.d(TAG, FirebaseAuth.getInstance().currentUser.toString())
                        true
                    }
                } catch (e: Exception) {
                    Log.d(TAG, " error in mainActivity  " + e.message.toString())
                    super.onOptionsItemSelected(item)
                }
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_app_bar, menu)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun setDrawerLocked() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        toolbar.visibility = View.GONE
        fab.visibility = View.GONE
        bottomNavigationView.visibility = View.GONE
    }

    override fun setDrawerUnlocked() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        toolbar.visibility = View.VISIBLE
        fab.visibility = View.VISIBLE
        bottomNavigationView.visibility = View.VISIBLE
    }

}