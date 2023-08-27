package com.trabajoIntegrador.gameOfThrones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class PersonajeActivity : AppCompatActivity() {

    lateinit var im: ImageView
    var personajels: List<Personaje> = PersonajesProvider.personajeList // lista con personajes
    val bundle : Bundle? = intent.extras // datos extras que envie a esta activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personaje)
        val nomPer=bundle?.getString("NOMBRE") // obtengo los datos extras

        val i= personajels.indexOfFirst { it.nombre==nomPer }// busco el dato extra en la lista

       val image=personajels[i].imagen
        im=findViewById(R.id.IvPersonaje)

        Glide.with(im.context).load(image).into(im)

    }
}