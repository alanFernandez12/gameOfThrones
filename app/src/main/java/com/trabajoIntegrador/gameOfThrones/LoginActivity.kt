package com.trabajoIntegrador.gameOfThrones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    ////// --- Elementos de la vista //////
    lateinit var btnIniciar: Button
    lateinit var  etUsuario: EditText
    lateinit var etContr:EditText
    lateinit var btnReg:Button
    lateinit var cbRecordarUsuario:CheckBox
    //////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //extraigo los datos obtenidos por los elementos de la vista
        btnIniciar=findViewById(R.id.btnIniciar)
        etUsuario=findViewById(R.id.etIngreseUs)
        etContr=findViewById(R.id.etContraseña)
        btnReg=findViewById(R.id.btnReg)
        cbRecordarUsuario = findViewById(R.id.cbRecordarUsuario)


        var preferencias = getSharedPreferences(resources.getString((R.string.sp_credenciales)), MODE_PRIVATE)
        var usuarioGuardado = preferencias.getString(resources.getString(R.string.nombre_usuario), "")
        var passwordGuardado = preferencias.getString(resources.getString(R.string.password_usuario), "")

        if(usuarioGuardado!= "" && passwordGuardado!= ""){
            startMainActivity(usuarioGuardado!!)
        }
        //accion al presionar el boton registrar
        btnReg.setOnClickListener{
        //FaltaActividad registro
            Log.i("depurar", "Ingresando a Registrar Usuario")
            var intentRegistrarUserActivity=Intent(this,RegistrarUserActivity::class.java)
            startActivity(intentRegistrarUserActivity)
        }

        //accion al presionar el boton iniciar session con una validacion de datos
        btnIniciar.setOnClickListener{

            //por ahora si los campos estan vacío va dar un mensaje y no se podra acceder la vista main
            if(etUsuario.text.isEmpty() || etContr.text.isEmpty())
                Toast.makeText(this,"Usuario o contraseña incorrectos",Toast.LENGTH_SHORT).show()
            else{
                //iniciando la actividad pasando la validacion
                if(cbRecordarUsuario.isChecked)
                {
                    val editar = preferencias.edit()
                    editar.putString(resources.getString(R.string.nombre_usuario),etUsuario.text.toString())
                    editar.putString(resources.getString(R.string.password_usuario),etContr.text.toString())
                    editar.apply()
                }

                var intentMainActivity=Intent(this,MainActivity::class.java)
                startActivity(intentMainActivity)


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