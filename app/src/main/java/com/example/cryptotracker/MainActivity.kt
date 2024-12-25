package com.example.cryptotracker

import android.os.Bundle
import android.widget.PopupMenu

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.cryptotracker.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentSpace)
        val navController = navHostFragment!!.findNavController()
        val popupmenu = PopupMenu(this, null)
        popupmenu.inflate(R.menu.bottom_nav_menu)
        binding.bottomBar.setupWithNavController(popupmenu.menu, navController)

    }

}