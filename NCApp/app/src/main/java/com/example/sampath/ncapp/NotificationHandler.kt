package com.example.sampath.ncapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color

class NotificationHandler(context:Context): ContextWrapper(context) {
    var notificationManager:NotificationManager? = null
    init {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel()
    }

    companion object {
        val WATCH_MOVIE_NOTIFICATION_CHANNEL_ID:String = "WMNCID"
        val WATCH_MOVIE_NOTIFICATION_CHANNEL_NAME:String = "WMNCName"
    }

    fun createNotificationChannel(){
        val notificationChannel = NotificationChannel(WATCH_MOVIE_NOTIFICATION_CHANNEL_ID, WATCH_MOVIE_NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)

        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.RED
        notificationChannel.setShowBadge(true)
        //notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        notificationChannel.enableVibration(true)
        notificationChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)

        notificationManager?.createNotificationChannel(notificationChannel)
    }

    fun createAndReturnWatchMovieNotification(titleText:String, bodyText:String):Notification{
        return Notification.Builder(applicationContext,WATCH_MOVIE_NOTIFICATION_CHANNEL_ID)
                .setContentTitle(titleText)
                .setContentText(bodyText)
                .setSmallIcon(R.drawable.watch_movie)
                .setAutoCancel(true)
                .setChannelId(WATCH_MOVIE_NOTIFICATION_CHANNEL_ID)
                .build()
    }

    fun notifyTheUser(notificationID:Int,notificationBuilder:Notification){
        notificationManager?.notify(notificationID,notificationBuilder)
    }
}