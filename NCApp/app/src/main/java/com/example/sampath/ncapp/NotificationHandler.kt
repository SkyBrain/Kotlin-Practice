package com.example.sampath.ncapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable

class NotificationHandler(context:Context): ContextWrapper(context) {
    var notificationManager:NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    companion object {
        val WATCH_MOVIE_NOTIFICATION_CHANNEL_ID:String = "WMNCID"
        val WATCH_MOVIE_NOTIFICATION_CHANNEL_NAME:String = "WMNCName"
    }

    fun createNotificationChannel(){
        val notificationChannel = NotificationChannel(WATCH_MOVIE_NOTIFICATION_CHANNEL_ID, WATCH_MOVIE_NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)

        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.setShowBadge(true)
        notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        notificationManager.createNotificationChannel(notificationChannel)
    }
}