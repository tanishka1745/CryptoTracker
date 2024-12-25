package com.example.cryptotracker

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Looper
import android.view.WindowManager

import androidx.appcompat.app.AppCompatActivity
import com.example.cryptotracker.fragments.LogInFragement


class SplashScreenActivity : AppCompatActivity() {
    val activity: MainActivity = MainActivity()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        //Normal Handler is deprecated , so we have to change the code little bit
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        //Normal Handler is deprecated , so we have to change the code little bit

        // Handler().postDelayed({
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            var sharedPreferences: SharedPreferences =
                this.getSharedPreferences(LogInFragement::PREFS_NAME.toString(), 0)
            // fetching the data of shared prefrenece that wether user has logged in or not
            var hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn", false)

            //checking if hasLoggedIn is true then it will launch the MainActivity otherwise LoginActivity
            if (hasLoggedIn) {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()
            }
            else{
                val intent = Intent(this, Login_SignUp::class.java)
                startActivity(intent)
                finish()
            }

        }, 3000) // 3000 is the delayed time in milliseconds.
    }

}






