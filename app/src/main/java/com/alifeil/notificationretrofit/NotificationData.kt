package com.alifeil.notificationretrofit

import com.google.gson.annotations.SerializedName

data class NotificationData (

    @SerializedName("title")
    val title :String,
    @SerializedName("body") //serialized name digunakan untuk nama yang berbeda
    val message: String,
    val activity : String,
    val dataone : String,
    val datatwo : String
    )