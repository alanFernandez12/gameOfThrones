package com.trabajoIntegrador.gameOfThrones

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class InicioActivity : AppCompatActivity() {
    lateinit var btnIniciar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        btnIniciar = findViewById(R.id.btnIniciar)

        btnIniciar.setOnClickListener {
            var intentLoginActivity = Intent(this, LoginActivity::class.java)
            startActivity(intentLoginActivity)

            finish()
        }
    }
}