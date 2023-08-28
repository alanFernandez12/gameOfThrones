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

        btnIniciar=findViewById(R.id.btnIniciar)
        etUsuario=findViewById(R.id.etIngreseUs)
        etContr=findViewById(R.id.etContraseña)
        btnReg=findViewById(R.id.btnReg)


        btnReg.setOnClickListener{
        //FaltaActividad registro
            Log.i("depurar", "Ingresando a Registrar Usuario")
            //var intentRegistrarUsuario=Intent(this,RegistrarUsuario::class.java)
            //startActivity(intentRegistrarUsuario)
        }

        btnIniciar.setOnClickListener{

            if(etUsuario.text.isEmpty() || etContr.toString().isEmpty())
                Toast.makeText(this,"Usuario o contraseña incorrectos",Toast.LENGTH_SHORT).show()
            else{
                var intentMainActivity=Intent(this,MainActivity::class.java)
                startActivity(intentMainActivity)


            }
        }
    }
}