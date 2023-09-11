package com.trabajoIntegrador.gameOfThrones

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrarUserActivity : AppCompatActivity() {

    lateinit var etUsername: EditText
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var btnRegister: Button
    var usuarioRegistrado: Boolean = false
    lateinit var usuario :String
    lateinit var password: String
    lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuario)
        etUsername = findViewById(R.id.etUsername)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnRegister = findViewById(R.id.btnRegister)

// obtener datos recibidos desde la vista previa
        var bundle: Bundle? = intent.extras
        // Reviso que efectivamente tenga datos
        if (bundle != null) {
            // Obtengo los datos especifico
            usuario = bundle?.getString(resources.getString(R.string.nombre_usuario)).toString()
            password = bundle?.getString(resources.getString(R.string.password_usuario)).toString()
            // los paso a los cajas de texto
            etUsername.setText(usuario)
            etPassword.setText(password)
        }
            btnRegister.setOnClickListener {
                usuario = etUsername.text.toString()
                email = etEmail.text.toString()
                password = etPassword.text.toString()

                // Perform registration logic here
                registrarUsuario(usuario, email, password)


            }
        }

        private fun registrarUsuario(usuario:String, email: String, password: String) {
            // Implement your registration logic here
            // You can make network requests, validate input fields, etc.

            if (usuarioRegistrado)
                Toast.makeText(this, "${usuario} ya registrado en sistema", Toast.LENGTH_SHORT)
                    .show()
            else {
                if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    // For this example, let's just display a toast message
                    val message = "Registration successful for $username"
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    usuarioRegistrado = true
                    val intentMainActivity = Intent(this, LoginActivity::class.java)
                    startActivity(intentMainActivity)
                    finish()
                } else {
                    Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }