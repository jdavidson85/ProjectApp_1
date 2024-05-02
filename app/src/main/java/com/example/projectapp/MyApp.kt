package com.example.projectapp

import android.app.Application

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Create notification channel for reminders
        NotificationHelper.createNotificationChannel(this)
    }
}