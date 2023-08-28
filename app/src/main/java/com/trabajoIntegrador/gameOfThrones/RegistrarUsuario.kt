package com.trabajoIntegrador.gameOfThrones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegistrarUsuario : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuario)

        etUsername = findViewById(R.id.etUsername)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val username = etUsername.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            // Perform registration logic here
            registrarUsuario(username, email, password)
        }
    }

    private fun registrarUsuario(username: String, email: String, password: String) {
        // Implement your registration logic here
        // You can make network requests, validate input fields, etc.

        // For this example, let's just display a toast message
        val message = "Registration successful for $username"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}