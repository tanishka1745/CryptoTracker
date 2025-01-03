package com.example.cryptotracker.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.cryptotracker.MainActivity
import com.example.cryptotracker.database.DataBaseHelper
import com.example.cryptotracker.databinding.FragmentLoginBinding




class LogInFragement : Fragment() {
    open val PREFS_NAME: String = "MyPrefsFile"
    lateinit var handler: DataBaseHelper

    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)

        val thiscontext = activity?.let { DataBaseHelper(it) }!!
        handler = thiscontext

        // Declaring the prefrence object
        val preferences = this.activity
            ?.getSharedPreferences(LogInFragement::PREFS_NAME.toString(), 0)

        binding.loginButton.setOnClickListener {
            val email = binding.loginEmail.text.toString()
            val password = binding.loginPassword.text.toString()
            // Calling function of DB helper class
            if (handler.userPresent(email, password)) {

                var editor: SharedPreferences.Editor =
                    (preferences?.edit() ?: null) as SharedPreferences.Editor
                // changing the boolean data of key hasLoggedIn to true because credentials is matched now
                editor.putBoolean("hasLoggedIn", true)
                editor.commit()
                // Starting the Activity with Intent
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                //Generating Log message
                Toast.makeText(requireContext(),"Wrong Credentials",Toast.LENGTH_SHORT).show()
            }
        }


        return binding.root
    }


}