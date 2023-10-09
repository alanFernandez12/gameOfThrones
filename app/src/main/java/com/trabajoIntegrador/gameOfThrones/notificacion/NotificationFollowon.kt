package com.trabajoIntegrador.gameOfThrones.notificacion

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class NotificationFollowon : Activity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myNotificationFollowon = TextView(this)
        myNotificationFollowon.text = "Game of Thrones"
        setContentView(myNotificationFollowon)
    }
}