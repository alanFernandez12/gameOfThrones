package com.trabajoIntegrador.gameOfThrones

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.trabajoIntegrador.gameOfThrones.datos.AppDatabase
import com.trabajoIntegrador.gameOfThrones.datos.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import android.Manifest
import android.app.TaskStackBuilder
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class LoginActivity : AppCompatActivity() {
    ////// --- Elementos de la vista //////
    lateinit var btnIniciar: Button
    lateinit var etUsuario: EditText
    lateinit var etContr: EditText
    lateinit var btnReg: Button
    lateinit var cbRecordarUsuario: CheckBox
    //////////////////////////////////////

    ////// --- elementos para configurar las notificaciones //////
    private val canalNombre = "Game Of Thrones"
    private val CHANNEL_ID = "IdChannel"
    private val NOTIFICATION_ID = 12345

    ////// --- Elementos del entorno de ejecucion //////
    lateinit var usuario: String
    lateinit var password: String
    ////////

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
        Toast.makeText(this, "Usuario guardado: " + usuarioGuardado, Toast.LENGTH_SHORT).show()
        if (usuarioGuardado != "") {
            Toast.makeText(this, "Usuario guardado: " + usuarioGuardado, Toast.LENGTH_SHORT).show()
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

            if (usuario.isEmpty())
                Toast.makeText(this, "ingrese usuario", Toast.LENGTH_SHORT).show()
            else {
                // previamente buscamos en la base de datos si existe información del usuario
                val checkUsuario = runQryDbaseCorrutina(usuario)
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
                            Toast.makeText(
                                this,
                                "recordar Usuario: " + preferencias.getString(
                                    resources.getString(R.string.nombre_usuario),
                                    ""
                                ),
                                Toast.LENGTH_SHORT
                            ).show()
                            crearCanalNotificacion()
                            crearNotificacion()
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

    private fun runQryDbaseCorrutina(usuarioDB: String): Usuario? {
        var checkUser: Usuario?
        runBlocking(Dispatchers.IO) {
            checkUser = registrarIngreso(usuarioDB)
        }
        return checkUser
    }

    private fun registrarIngreso(user: String): Usuario {
        // chequear si el usuario ya existe en base de datos
        val bdd = AppDatabase.getDatabase(this@LoginActivity)
        return bdd.usuarioDao.getNombre(user)
    }

    private fun crearCanalNotificacion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val canalImportancia = NotificationManager.IMPORTANCE_HIGH
            val canal = NotificationChannel(this.CHANNEL_ID, canalNombre, canalImportancia)

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(canal)

        }
    }

    private fun crearNotificacion() {

        val resultIntent = Intent(applicationContext, MainActivity::class.java)
        val resultPendingIntent = TaskStackBuilder.create(applicationContext).run {

            addNextIntentWithParentStack(resultIntent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val notificacion = NotificationCompat.Builder(this, CHANNEL_ID).also {
            it.setContentTitle("Game Of Thrones Notificacion")
            it.setContentText("Usuario recordado")
            it.setSmallIcon(android.R.mipmap.sym_def_app_icon)
            it.priority = NotificationCompat.PRIORITY_DEFAULT
            it.setContentIntent(resultPendingIntent)
            it.setVibrate(longArrayOf(1000, 500, 1000, 500, 1000))
            it.setAutoCancel(true)
        }.build()

        val notificationManager = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        notificationManager.notify(NOTIFICATION_ID, notificacion)
    }
}