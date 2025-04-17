package com.example.kolos.functions

import android.app.Activity

fun restartApp(activity: Activity){
    val intent = activity.intent
    activity.finish()
    activity.startActivity(intent)
}