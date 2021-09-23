package com.alifeil.notificationretrofit

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_one.*

class ActivityOne : AppCompatActivity() {
    companion object {
        const val  titleactivityone = "TITLE ACTIVITY ONE"
        const val  messageactivityone = "MESSAGE ACTIVITY ONE"
        const val activityone = "ACTIVITY ONE"
        const val dataoneactivityku = "DATA 1"
        const val datatwoactivityku = "DATA 2"
    }
    private lateinit var titleactivityTV : TextView
    private lateinit var messageactivityTV : TextView
    private lateinit var activityactivityTV : TextView
    private lateinit var dataoneactivityTV : TextView
    private lateinit var datatwoactivityTV : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)

        val title = intent.getStringExtra(titleactivityone)
        val message = intent.getStringExtra(messageactivityone)
        val activity = intent.getStringExtra(activityone)
        val dataone = intent.getStringExtra(dataoneactivityku)
        val datatwo = intent.getStringExtra(datatwoactivityku)

        titleactivityTV = findViewById(R.id.titleactivity)
        messageactivityTV = findViewById(R.id.bodyactivity)
        activityactivityTV = findViewById(R.id.activityactivity)
        dataoneactivityTV = findViewById(R.id.dataoneactivity)
        datatwoactivityTV = findViewById(R.id.datatwoactivity)


        titleactivityTV.text =" "+title
        messageactivityTV.text = ""+message
        activityactivityTV.text = "" +activity
        dataoneactivityTV.text = ""+dataone
        datatwoactivityTV.text = ""+datatwo



    }


}