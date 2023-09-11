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
    lateinit var usuario: String
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
        val bundle: Bundle? = intent.extras
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

    private fun registrarUsuario(usuario: String, email: String, password: String) {
        if (usuario.isEmpty() || email.isEmpty() || password.isEmpty())
            Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show()
        else {
            // previamente buscamos en la base de datos si existe información del usuario
            val bdd = AppDatabase.getDatabase(this)
            val checkUsuario = bdd.usuarioDao.getNombre(usuario)

            if (checkUsuario == null) {    // si no existe lo agregamos
                val newUsuario = Usuario(nombre = usuario, contr = password, email = email)
                bdd.usuarioDao.insertUsuario(newUsuario)
                Toast.makeText(
                    this,
                    "${usuario} registrado con exito en sistema",
                    Toast.LENGTH_SHORT
                )
                    .show()
                val intentMainActivity = Intent(this, LoginActivity::class.java)
                startActivity(intentMainActivity)
                finish()
            }
        }
    }

}