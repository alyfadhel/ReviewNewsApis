package com.example.apinewsreview.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.apinewsreview.R
import com.example.apinewsreview.ui.activity.viewModel.HomeActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            goToHomeActivity()
        },2000)
    }

    private fun goToHomeActivity() {
        val intent = Intent(this,
            HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}