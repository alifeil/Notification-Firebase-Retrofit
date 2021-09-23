package com.alifeil.notificationretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DataOne : AppCompatActivity() {
    companion object {
        const val  titledataone = "TITLE DATA ONE"
        const val  messagedataone = "MESSAGE DATA ONE"
        const val activitydataone = "ACTIVITY DATA ONE"
        const val dataoneone = "DATA ONE ONE"
        const val datatwoone = "DATA TWO ONE"
    }
    private lateinit var titledataTV : TextView
    private lateinit var messagedataTV : TextView
    private lateinit var activitydataTV : TextView
    private lateinit var dataoneoneTV : TextView
    private lateinit var dataonetwoTV : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_one)

        val title = intent.getStringExtra(titledataone)
        val message = intent.getStringExtra(messagedataone)
        val activity = intent.getStringExtra(activitydataone)
        val dataone = intent.getStringExtra(dataoneone)
        val datatwo = intent.getStringExtra(datatwoone)

        titledataTV= findViewById(R.id.TitleDataOne)
        messagedataTV = findViewById(R.id.BodyDataOne)
        activitydataTV = findViewById(R.id.ActivityDataOne)
        dataoneoneTV = findViewById(R.id.DataOneOne)
        dataonetwoTV = findViewById(R.id.DataTwoOne)


        titledataTV.text =" "+title
        messagedataTV.text = ""+message
        activitydataTV.text = ""+activity
        dataoneoneTV.text = ""+dataone
        dataonetwoTV.text = ""+datatwo

    }


}