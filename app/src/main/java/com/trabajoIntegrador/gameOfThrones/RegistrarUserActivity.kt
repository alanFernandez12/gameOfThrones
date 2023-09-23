package com.trabajoIntegrador.gameOfThrones

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.trabajoIntegrador.gameOfThrones.datos.AppDatabase
import com.trabajoIntegrador.gameOfThrones.datos.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrarUserActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegister: Button
    private lateinit var usuario: String
    private lateinit var password: String
    private lateinit var email: String

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
            usuario = bundle.getString(resources.getString(R.string.nombre_usuario)).toString()
            password = bundle.getString(resources.getString(R.string.password_usuario)).toString()
            // los paso a los cajas de texto
            etUsername.setText(usuario)
            etPassword.setText(password)
        }
        btnRegister.setOnClickListener {
            usuario = etUsername.text.toString()
            email = etEmail.text.toString()
            password = etPassword.text.toString()

            // Realizar logica de registro
            registrarUsuario(usuario, email, password)


        }
    }

    private fun registrarUsuario(usuario: String, email: String, password: String) {
        if (usuario.isEmpty() || email.isEmpty())
            Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show()
        else {
            // previamente buscamos en la base de datos si existe información del usuario
            val usuarioDB = Usuario(nombre = usuario, contr = password, email = email)
            val checkUsuario = ejecutarQueryDatabaseCorrutina(usuarioDB, 1) // 1 consulta
            Log.i("corrutina", "Validar si existe " + checkUsuario.toString() + " " + 1)
            if (checkUsuario == false) {    // si no existe lo agregamos
                ejecutarQueryDatabaseCorrutina(usuarioDB, 2) // 2 inserta
                Log.i(
                    "corrutina",
                    "si existe añadir a la tabla " + checkUsuario.toString() + " " + 2
                )
                Toast.makeText(this, "$usuario registrado con exito en sistema", Toast.LENGTH_SHORT)
                    .show()
                val intentMainActivity = Intent(this, LoginActivity::class.java)
                startActivity(intentMainActivity)
                finish()
            } else
                Toast.makeText(
                    this,
                    "$usuario ya existe en sistema: ${usuarioDB.nombre}",
                    Toast.LENGTH_SHORT
                ).show()
        }

    }

    private fun ejecutarQueryDatabaseCorrutina(usuarioDB: Usuario, oper: Int): Boolean? {
        var checkUser: Usuario? = null;

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val bdd = AppDatabase.getDatabase(this@RegistrarUserActivity)
                if (oper == 1)
                    checkUser = bdd.usuarioDao.getNombre(usuarioDB.nombre)
                else
                    bdd.usuarioDao.insertUsuario(usuarioDB)
            }
            Log.i("corrutina", "linea 95" + checkUser.toString() + " " + oper)
        }

        Toast.makeText(
            this@RegistrarUserActivity,
            "Ejecucion withcontext: $checkUser",
            Toast.LENGTH_LONG
        ).show()
        Log.i(
            "corrutina",
            "linea 103" + checkUser.toString() + " " + usuarioDB.toString() + " " + oper
        )
        return (checkUser?.equals(usuarioDB))
    }
}
