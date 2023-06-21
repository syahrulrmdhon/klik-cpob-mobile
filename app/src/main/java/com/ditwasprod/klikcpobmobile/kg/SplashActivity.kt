package com.ditwasprod.klikcpobmobile.kg

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(Runnable {
            //Do something after 100ms
            val myIntent = Intent(this, MainActivity::class.java)
            this.startActivity(myIntent)
        }, 100)


    }
}