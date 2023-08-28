package com.trabajoIntegrador.gameOfThrones

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrarUserActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegister: Button
    private final var usuarioRegistrado: Boolean = false
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

        if (usuarioRegistrado)
        Toast.makeText(this, "${username} ya registrado en sistema", Toast.LENGTH_SHORT).show()
        else
        {
            if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                // For this example, let's just display a toast message
                val message = "Registration successful for $username"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                usuarioRegistrado = true;
            }
        }
    }
}