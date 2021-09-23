package com.alifeil.notificationretrofit

import com.google.gson.annotations.SerializedName

data class PushNotification(
    val data : NotificationData,
    @SerializedName("to")
    val to: String
)