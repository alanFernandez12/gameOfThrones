package com.trabajoIntegrador.gameOfThrones

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class PersonajeActivity : AppCompatActivity() {

    lateinit var im: ImageView
    var personajels: List<Personaje> = PersonajesProvider.personajeList // lista con personajes


    override fun onCreate(savedInstanceState: Bundle?) {
        val bundle: Bundle? = intent.extras // datos extras que envie a esta activity

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personaje)
        val nomPer = bundle?.getString("Nombre") // obtengo los datos extras

        val i = personajels.indexOfFirst { it.nombre == nomPer }// busco el dato extra en la lista
        if (i != -1) {
            val image = personajels[i].imagen
            im = findViewById(R.id.IvPersonaje)

            Glide.with(im.context).load(image).into(im)// cargo la irl de la foto

        }
    }
}