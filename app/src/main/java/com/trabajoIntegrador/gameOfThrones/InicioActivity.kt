package com.trabajoIntegrador.gameOfThrones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class InicioActivity : AppCompatActivity() {
    lateinit var btnIniciar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        btnIniciar= findViewById(R.id.btnIniciar)

        btnIniciar.setOnClickListener {
            var intentMainActivity= Intent(this, MainActivity::class.java)
            startActivity(intentMainActivity)

            finish()
        }
    }
}