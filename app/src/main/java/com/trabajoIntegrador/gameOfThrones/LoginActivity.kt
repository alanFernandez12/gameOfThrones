package com.trabajoIntegrador.gameOfThrones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    lateinit var btnIniciar: Button
    lateinit var  etUsuario: EditText
    lateinit var etContr:EditText
    lateinit var btnReg:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //extraigo los datos obtenidos por los elementos de la vista
        btnIniciar=findViewById(R.id.btnIniciar)
        etUsuario=findViewById(R.id.etIngreseUs)
        etContr=findViewById(R.id.etContraseña)
        btnReg=findViewById(R.id.btnReg)


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
                var intentMainActivity=Intent(this,MainActivity::class.java)
                startActivity(intentMainActivity)


            }
        }
    }
}