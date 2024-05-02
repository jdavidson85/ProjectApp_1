package com.example.projectapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

object NotificationHelper {

    fun createNotificationChannel(context: Context) {
        val channelId = "reminder_channel"
        val channelName = "Reminders"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channelDescription = "Channel for reminder notifications"

        val channel = NotificationChannel(channelId, channelName, importance)
        channel.description = channelDescription

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}