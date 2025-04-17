package com.example.kolos

import android.app.Application
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KolosApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Lingver.init(this, getSavedLang())
    }

    private fun getSavedLang() : String {
        val prefs = getSharedPreferences("prefs", MODE_PRIVATE)
        return prefs.getString("lang", "en") ?: "en"
    }
 }