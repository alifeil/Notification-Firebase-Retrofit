package com.alifeil.notificationretrofit

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdReceiver
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal

import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_data_one.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_one.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TOPIC = "/topics/myTopic2"

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseService.sharedPref = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)


         FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if(task.isSuccessful()){
                etToken.setText(task.result.toString())

            }

         }
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)

        btn_activity.setOnClickListener {
            val title = judultxt.text.toString()
            val message = deskripsitxt.text.toString()
            val activity = activityTXT.text.toString()
            val dataone = dataoneTXT.text.toString()
            val dataTwo = datatwoTXT.text.toString()
            val intent = Intent(this@MainActivity,ActivityOne::class.java)
            intent.putExtra(ActivityOne.titleactivityone,title)
            intent.putExtra(ActivityOne.messageactivityone,message)
            intent.putExtra(ActivityOne.activityone,activity)
            intent.putExtra(ActivityOne.dataoneactivityku,dataone)
            intent.putExtra(ActivityOne.datatwoactivityku,dataTwo)
            startActivity(intent)

            if(title.isNotEmpty() && message.isNotEmpty()){
                PushNotification(
                    NotificationData(title, message,activity,dataone,dataTwo),
                    TOPIC
                ).also{
                    sendNotification(it)
                }
            }
        }
        btn_DataOne.setOnClickListener {
            val title = judultxt.text.toString()
            val message = deskripsitxt.text.toString()
            val activity = activityTXT.text.toString()
            val dataone = dataoneTXT.text.toString()
            val dataTwo = datatwoTXT.text.toString()
            val intent = Intent(this@MainActivity,DataOne::class.java)
            intent.putExtra(DataOne.titledataone,title)
            intent.putExtra(DataOne.messagedataone,message)
            intent.putExtra(DataOne.activitydataone,activity)
            intent.putExtra(DataOne.dataoneone,dataone)
            intent.putExtra(DataOne.datatwoone,dataTwo)
            startActivity(intent)
            if(title.isNotEmpty() && message.isNotEmpty()){
                PushNotification(
                    NotificationData(title, message,activity,dataone,dataTwo),
                    TOPIC
                ).also {
                    sendNotification(it)
                }
            }
        }

        btn_DataTwo.setOnClickListener {
            val title = judultxt.text.toString()
            val message = deskripsitxt.text.toString()
            val intent = Intent(this@MainActivity,DataTwo::class.java)
            val activity = activityTXT.text.toString()
            val dataone = dataoneTXT.text.toString()
            val dataTwo = datatwoTXT.text.toString()
            intent.putExtra(DataTwo.titledatatwo,title)
            intent.putExtra(DataTwo.messagedatatwo,message)
            intent.putExtra(DataTwo.activitydatatwo,activity)
            intent.putExtra(DataTwo.dataonetwo,dataone)
            intent.putExtra(DataTwo.datatwotwo,dataTwo)
            startActivity(intent)
            if(title.isNotEmpty() && message.isNotEmpty()){
                PushNotification(
                    NotificationData(title, message, activity,dataone,dataTwo),
                    TOPIC
                ).also {
                    sendNotification(it)
                }
            }
        }

        btn_send.setOnClickListener {
            val title = judultxt.text.toString()
            val message = deskripsitxt.text.toString()
            val activity = activityTXT.text.toString()
            val dataOne = dataoneTXT.text.toString()
            val dataTwo = datatwoTXT.text.toString()
            val recipientToken = etToken.text.toString()
            if (title.isNotEmpty() && message.isNotEmpty()){
                PushNotification(
                    NotificationData(title,message,activity,dataOne,dataTwo),
                    TOPIC
                ).also {
                    sendNotification(it)
                }
            }else{
                Toast.makeText(applicationContext,"Masih ada yang kosong", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.postNotification(notification)
            if (response.isSuccessful){
                Log.d(TAG, "Response: ${Gson().toJson(response)}")
            }else{
                Log.e(TAG, response.errorBody().toString())
//                Log.e(TAG, response.toString())
                Log.e(TAG, response.isSuccessful.toString())
            }
        }catch (e: Exception){
            Log.e(TAG, e.toString())
        }

    }
}

