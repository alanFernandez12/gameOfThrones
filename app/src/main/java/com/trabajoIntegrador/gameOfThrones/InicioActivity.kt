package com.trabajoIntegrador.gameOfThrones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class InicioActivity : AppCompatActivity() {
    lateinit var btnIniciar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        btnIniciar= findViewById(R.id.btnIniciar)

        Log.i("cicloDeVida", "Inicio OnCreate" )

        btnIniciar.setOnClickListener {
            val intentMainActivity= Intent(this, MainActivity::class.java)
            startActivity(intentMainActivity)

            finish()
        }
    }
}