package com.example.sampath.ncapp

import android.app.Notification
import android.app.NotificationManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.EditText
import com.example.sampath.ncapp.R.id.btnMovieSettings
import com.example.sampath.ncapp.R.id.btnWatchMovie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var notificationHandler:NotificationHandler

    companion object {
        val WATCH_MOVIE_NOTIFICATION_ID = 1000
    }

    override fun onClick(v: View?) {
        when(v?.id){
            this.btnMovieSettings.id -> {
                openNotification(NotificationHandler.WATCH_MOVIE_NOTIFICATION_CHANNEL_ID)
            }
            this.btnWatchMovie.id -> {
                postNotificationToUserDevice(WATCH_MOVIE_NOTIFICATION_ID,edtWatchMovie.text.toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notificationHandler = NotificationHandler(this)
        setContentView(R.layout.activity_main)
        btnWatchMovie.setOnClickListener(this)
        btnMovieSettings.setOnClickListener(this)
    }

    fun postNotificationToUserDevice(notificationID:Int, titleText:String){
        var notificationBuilder:Notification? = null
        when(notificationID){
            WATCH_MOVIE_NOTIFICATION_ID ->{
                notificationBuilder = notificationHandler.createAndReturnWatchMovieNotification(titleText,"This is a great movie.")
            }
        }

        if (notificationBuilder != null){
            notificationHandler?.notifyTheUser(WATCH_MOVIE_NOTIFICATION_ID,notificationBuilder)
        }
    }

    fun openNotification(id:String){
        var settingsIntent = Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS)
        settingsIntent.putExtra(Settings.EXTRA_APP_PACKAGE,packageName)
        settingsIntent.putExtra(Settings.EXTRA_CHANNEL_ID,id)
        startActivity(settingsIntent)
    }

}
