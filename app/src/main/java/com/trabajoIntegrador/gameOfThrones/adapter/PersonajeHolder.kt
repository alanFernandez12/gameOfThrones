package com.trabajoIntegrador.gameOfThrones.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.trabajoIntegrador.gameOfThrones.Personaje
import com.trabajoIntegrador.gameOfThrones.R

class PersonajeHolder(view: View) : RecyclerView.ViewHolder(view) {

    val nombre= view.findViewById<TextView>(R.id.nombrePer)
    val apellido= view.findViewById<TextView>(R.id.apellidoPer)
    val familia= view.findViewById<TextView>(R.id.familiaPer)
    val titulo= view.findViewById<TextView>(R.id.tituloPer)
    val imagen=view.findViewById<ImageView>(R.id.IvPersonaje)

    fun render(personajeModel:Personaje) {
        nombre.text = personajeModel.nombre
        apellido.text = personajeModel.apellido
        familia.text = personajeModel.familia
        titulo.text = personajeModel.titulo

        Glide.with(imagen.context).load(personajeModel.imagen).into(imagen)
    }

}