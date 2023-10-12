package com.trabajoIntegrador.gameOfThrones.adapter

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.trabajoIntegrador.gameOfThrones.Personaje
import com.trabajoIntegrador.gameOfThrones.R

import com.trabajoIntegrador.gameOfThrones.databinding.ItemPersonajeBinding


class PersonajeHolder(view: View) : RecyclerView.ViewHolder(view) {
    //push
    val nombre= view.findViewById<TextView>(R.id.nombrePer)
    val apellido= view.findViewById<TextView>(R.id.apellidoPer)
    val familia= view.findViewById<TextView>(R.id.familiaPer)
    val titulo= view.findViewById<TextView>(R.id.tituloPer)
    val foto= view.findViewById<ImageView>(R.id.IvPersonaje)


    fun render(personajeModel: Personaje) {
       nombre.text = personajeModel.firstName
        apellido.text = personajeModel.lastName
        familia.text = personajeModel.family
        titulo.text = personajeModel.title
        Glide.with(foto.context).load(personajeModel.imageUrl)
            .into(foto)

    }
}