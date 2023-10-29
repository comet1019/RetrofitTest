package com.example.retrofittest

import android.app.Application
import com.example.retrofittest.loginData.TokenSharedPreferences

class App: Application() {

    companion object{
        lateinit var token_prefs : TokenSharedPreferences
    }

    override fun onCreate() {
        super.onCreate()

        token_prefs = TokenSharedPreferences(applicationContext)


    }



}