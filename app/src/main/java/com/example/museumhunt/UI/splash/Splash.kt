package com.example.museumhunt.UI.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.museumhunt.R

import com.example.museumhunt.UI.info.UserInfo

class Splash : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        val timerThread = object : Thread() {
            override fun run() {
                try {
                    sleep(3000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    val intent = Intent(this@Splash, UserInfo::class.java)
                    startActivity(intent)
                }
            }
        }
        timerThread.start()
    }

    override fun onPause() {
        // TODO Auto-generated method stub
        super.onPause()
        finish()
    }
}
