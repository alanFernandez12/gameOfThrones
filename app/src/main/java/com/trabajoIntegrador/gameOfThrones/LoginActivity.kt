package com.trabajoIntegrador.gameOfThrones

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    ////// --- Elementos de la vista //////
    lateinit var btnIniciar: Button
    lateinit var etUsuario: EditText
    lateinit var etContr: EditText
    lateinit var btnReg: Button
    lateinit var cbRecordarUsuario: CheckBox
    //////////////////////////////////////

    ////// --- Elementos del entorno de ejecucion //////
    lateinit var usuario: String
    lateinit var password: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //extraigo los datos obtenidos por los elementos de la vista
        btnIniciar = findViewById(R.id.btnIniciar)
        etUsuario = findViewById(R.id.etIngreseUs)
        etContr = findViewById(R.id.etContraseña)
        btnReg = findViewById(R.id.btnReg)
        cbRecordarUsuario = findViewById(R.id.cbRecordarUsuario)


        val preferencias =
            getSharedPreferences(resources.getString((R.string.sp_credenciales)), MODE_PRIVATE)
        val usuarioGuardado =
            preferencias.getString(resources.getString(R.string.nombre_usuario), "")
        val passwordGuardado =
            preferencias.getString(resources.getString(R.string.password_usuario), "")

        // si existen datos guardados del usuario accedemos al sistema sin pedir contraseña
        if (usuarioGuardado != "" && passwordGuardado != "") {
            startMainActivity(usuarioGuardado!!)
        }
        //accion al presionar el boton registrar
        btnReg.setOnClickListener {
            //FaltaActividad registro
            usuario = etUsuario.text.toString()
            password = etContr.text.toString()
            val intentRegistrarUserActivity = Intent(this, RegistrarUserActivity::class.java)

            intentRegistrarUserActivity.putExtra(
                resources.getString(R.string.nombre_usuario),
                usuario
            )
            intentRegistrarUserActivity.putExtra(
                resources.getString(R.string.password_usuario),
                password
            )
            startActivity(intentRegistrarUserActivity)
        }

        //accion al presionar el boton iniciar session con una validacion de datos
        btnIniciar.setOnClickListener {
            //por ahora si los campos estan vacío va dar un mensaje y no se podra acceder la vista main
            // para utilizar en distintas validaciones
            usuario = etUsuario.text.toString()
            password = etContr.text.toString()

            if (usuario.isEmpty() || password.isEmpty())
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            else {
                // previamente buscamos en la base de datos si existe información del usuario
                val bdd = AppDatabase.getDatabase(this)
                val checkUsuario = bdd.usuarioDao.getNombre(usuario)
                if (checkUsuario == null)
                    Toast.makeText(this, "Usuario no registrado", Toast.LENGTH_SHORT).show()
                else
                    if (!checkUsuario.contr.equals(password))
                        Toast.makeText(this, "contraseña incorrecta", Toast.LENGTH_SHORT).show()
                    else {
                        //iniciando la actividad pasando la validacion
                        if (cbRecordarUsuario.isChecked) {
                            val editar = preferencias.edit()
                            editar.putString(resources.getString(R.string.nombre_usuario), usuario)
                            editar.putString(
                                resources.getString(R.string.password_usuario),
                                password
                            )
                            editar.apply()
                        }
                        val intentMainActivity = Intent(this, MainActivity::class.java)
                        startActivity(intentMainActivity)

                    }
            }
        }
    }

    private fun startMainActivity(usuarioGuardado: String) {
        // Indicamos a que pantalla queremos ir
        val intentMain = Intent(this, MainActivity::class.java)
        // Agregamos datos que queremos pasar a la proxima pantalla
        intentMain.putExtra(resources.getString(R.string.nombre_usuario), usuarioGuardado)
        // Cambiamos de pantalla
        startActivity(intentMain)
        // Eliminamos la Activity actual para sacarla de la Pila
        finish()
    }
}