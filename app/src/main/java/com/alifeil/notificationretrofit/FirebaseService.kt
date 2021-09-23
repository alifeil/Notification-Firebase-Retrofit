package com.alifeil.notificationretrofit

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

private const val  CHANNEL_ID = "my_channel"

class FirebaseService : FirebaseMessagingService() {

    companion object {
        var sharedPref: SharedPreferences? = null

        var token: String?
            get() {
                return sharedPref?.getString("token", "")
            }
            set(value) {
                sharedPref?.edit()?.putString("token", value)?.apply()
            }
    }

    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        token = newToken
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val intent = Intent(this@FirebaseService, MainActivity::class.java)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationID = Random.nextInt()

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, FLAG_ONE_SHOT)
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(message.data["title"])
            .setContentText(message.data["body"])
            .setSmallIcon(R.drawable.ic_android)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(notificationID, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channelName = "channelName"
        val channel = NotificationChannel(CHANNEL_ID, channelName, IMPORTANCE_HIGH).apply {
            description = "My channel description"
            enableLights(true)
            lightColor = Color.GREEN
        }
        notificationManager.createNotificationChannel(channel)
    }

}
//class FirebaseService :FirebaseMessagingService() {
//
//    companion object {
//        var sharedPref: SharedPreferences? = null
//        var token: String?
//            get() {
//                return sharedPref?.getString("token", "")
//            }
//            set(value) {
//                sharedPref?.edit()?.putString("token", value)?.apply()
//            }
//    }
//
//    override fun onMessageReceived(message: RemoteMessage) {
////        Log.d(ContentValues.TAG,"From: ${remoteMessage.from}")
////
////        if (remoteMessage.data.size > 0) {
////            val title = remoteMessage.data["title"]
////            val body = remoteMessage.data["body"]
////            showNotification(applicationContext, title, body)
////        } else{
////            val title = remoteMessage.notification!!.title
////            val body = remoteMessage.notification!!.body
////            showNotification(applicationContext, title, body)
////        }
////        val title = remoteMessage.notification!!.title
////        val body = remoteMessage.notification!!.body
////        remoteMessage.notification?.let {
////            Log.d(ContentValues.TAG, "Message Notification Body: ${it.body}")
////        }
//        super.onMessageReceived(message)
//
//        val intent = Intent(this, MainActivity::class.java)
//        val notificationManager =
//            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        val notificationID = Random.nextInt()
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            (notificationManager)
//
//        }
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        val pendingIntent = PendingIntent.getActivity(this, 0, intent, FLAG_ONE_SHOT)
//        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
//            .setContentTitle(message.data["title"])
//            .setContentText(message.data["message"])
//            .setSmallIcon(R.drawable.ic_android)
//            .setAutoCancel(true)
//            .setContentIntent(pendingIntent)
//            .build()
//
//        notificationManager.notify(notificationID, notification)
//    }
//
//    override fun onNewToken(p0: String) {
//        super.onNewToken(p0)
//        Log.d(ContentValues.TAG,"Refreshed token: $p0")
//    }
//
//    fun showNotification(title:String?, message:String?){
//        val ii : Intent
//        ii = Intent(baseContext, MainActivity::class.java)
//        ii.data = Uri.parse("custom://" + System.currentTimeMillis())
//        ii.action = "actionstring" + System.currentTimeMillis()
//        ii.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
//
//        val pi = PendingIntent.getActivity(baseContext,0,ii,PendingIntent.FLAG_UPDATE_CURRENT)
//        val notification : Notification
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            notification = NotificationCompat.Builder(baseContext, CHANNEL_ID)
//                .setOngoing(true)
//                .setSmallIcon(R.drawable.ic_notification)
//                .setContentText(message)
//                .setAutoCancel(true)
//                .setContentIntent(pi)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setCategory(Notification.CATEGORY_SERVICE)
//                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
//                .setContentTitle(title).build()
//
//            val notificationManager = baseContext.getSystemService(
//                Context.NOTIFICATION_SERVICE
//            )as NotificationManager
//            val notificationChannel = NotificationChannel(
//                CHANNEL_ID, title, NotificationManager.IMPORTANCE_DEFAULT
//            )
//            notificationManager.createNotificationChannel(notificationChannel)
//            notificationManager.notify(notificationId, notification)
//        }else{
//            notification = NotificationCompat.Builder(baseContext)
//                .setAutoCancel(true)
//                .setSmallIcon(R.drawable.ic_notification)
//                .setContentText(message)
//                .setContentIntent(pi)
////                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
//                .setContentTitle(title).build()
//
//            val notificationManager = baseContext.getSystemService(
//                Context.NOTIFICATION_SERVICE
//            ) as NotificationManager
//            notificationManager.notify(notificationId,notification)
//
//
//
//        }
//
//
//    }
//
////    override fun onMessageReceived(message: RemoteMessage) {
////        super.onMessageReceived(message)
////
////        val intent =  Intent(this,MainActivity::class.java)
////        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
////        val notificationID = Random.nextInt()
////
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
////            createNotificationChannel(notificationManager)
////        }
////
////
////        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
////        val pendingIntent = PendingIntent.getActivity(this,0, intent, FLAG_ONE_SHOT)
////        val notification = NotificationCompat.Builder(this)
////            .setContentTitle(message.data["title"])
////            .setContentText(message.data["message"])
////            .setSmallIcon(R.drawable.ic_android)
////            .setAutoCancel(true)
////            .setContentIntent(pendingIntent)
////            .build()
////
////        notificationManager.notify(notificationID,notification)
////    }
////
////    @RequiresApi(Build.VERSION_CODES.O)
////    private fun createNotificationChannel(notificationManager: NotificationManager){
////        val channelName = "channelName"
////        val channel = NotificationChannel(CHANNEL_ID, channelName, IMPORTANCE_HIGH).apply {
////            description = "My channel description"
////            enableLights(true)
////            lightColor = Color.GREEN
////        }
////        notificationManager.createNotificationChannel(channel)
////    }
