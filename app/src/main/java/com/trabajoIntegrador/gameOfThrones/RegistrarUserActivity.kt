package com.trabajoIntegrador.gameOfThrones

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.trabajoIntegrador.gameOfThrones.datos.AppDatabase
import com.trabajoIntegrador.gameOfThrones.datos.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

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
            // Obtengo los datos especificos
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
            msgToast("Campos Vacios")
        else {
            /* si usuario no existe en base de datos lo agregamos */
            val usuarioDB = Usuario(nombre = usuario, contr = password, email = email)
            val checkUsuario = runQryDbaseCorrutina(usuarioDB)
            if (checkUsuario == null) {    // usuario registrado con exito
                msgLog("si no existe añadir a la tabla $usuarioDB 2")
                msgToast("$usuario registrado con exito en sistema")
                val intentMainActivity = Intent(this, LoginActivity::class.java)
                startActivity(intentMainActivity)
                finish()
            } else
                msgToast("$usuario ya existe en sistema: ${checkUsuario}")
        }
    }

    private fun msgToast(msj: String) {
        Toast.makeText(this, msj, Toast.LENGTH_LONG).show()
    }

    private fun msgLog(msj: String) {
        Log.i("msgLog", msj)
    }


    private fun runQryDbaseCorrutina(usuarioDB: Usuario): Usuario? {
        var checkUser: Usuario?
        runBlocking(Dispatchers.IO) {
            checkUser = registerUser(usuarioDB)
        }

        /* si se utiliza lifecycle... se necesita "dormir" el hilo para asegurar
        * que se actualice checkUser
        lifecycleScope.launch(Dispatchers.IO) {
            checkUser = registerUser(usuarioDB)
        }
        Thread.sleep(100)
        msgToast("usuario: " + checkUser)
        */

        return checkUser
    }

    private fun registerUser(user: Usuario): Usuario {
        // 1. chequear si el usuario ya existe en base de datos
        val bdd = AppDatabase.getDatabase(this@RegistrarUserActivity)
        val existingUser = bdd.usuarioDao.getNombre(user.nombre)

        // 2. si el usuario no existe proceder con la insercion
        if (existingUser == null)
            bdd.usuarioDao.insertUsuario(user)
        return existingUser
    }
}


