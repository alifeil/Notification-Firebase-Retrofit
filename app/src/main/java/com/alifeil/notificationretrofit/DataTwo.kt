package com.alifeil.notificationretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DataTwo : AppCompatActivity() {
    companion object {
        const val  titledatatwo = "TITLE DATA TWO"
        const val  messagedatatwo = "MESSAGE DATA TWO"
        const val  activitydatatwo = "ACTIVITY DATA TWO"
        const val dataonetwo   = "DATA TWO ONE"
        const val datatwotwo = "DATA TWO TWO"
    }
    private lateinit var titledata2TV : TextView
    private lateinit var messagedata2TV : TextView
    private lateinit var activitydata2TV : TextView
    private lateinit var dataonetwoTV : TextView
    private lateinit var datatwotwoTV : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_two)

        val title = intent.getStringExtra(titledatatwo)
        val message = intent.getStringExtra(messagedatatwo)
        val activity = intent.getStringExtra(activitydatatwo)
        val dataone = intent.getStringExtra(dataonetwo)
        val datatwo = intent.getStringExtra(datatwotwo)

        titledata2TV= findViewById(R.id.TitleDataTwo)
        messagedata2TV = findViewById(R.id.BodyDataTwo)
        activitydata2TV = findViewById(R.id.ActivityDataTwo)
        dataonetwoTV = findViewById(R.id.DataOneTwo)
        datatwotwoTV = findViewById(R.id.DataTwoTwo)

        titledata2TV.text =" "+title
        messagedata2TV.text = ""+message
        activitydata2TV.text = ""+activity
        dataonetwoTV.text = ""+dataone
        datatwotwoTV.text = ""+datatwo
    }
}