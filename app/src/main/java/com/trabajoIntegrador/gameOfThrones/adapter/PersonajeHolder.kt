package com.trabajoIntegrador.gameOfThrones.adapter

import android.view.View

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.trabajoIntegrador.gameOfThrones.Personaje

import com.trabajoIntegrador.gameOfThrones.databinding.ItemPersonajeBinding


class PersonajeHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding= ItemPersonajeBinding.bind(view)



    fun render(personajeModel:Personaje){
        binding.nombrePer.text=personajeModel.nombre
        binding.apellidoPer.text=personajeModel.apellido
        binding.familiaPer.text=personajeModel.familia
        binding.tituloPer.text=personajeModel.titulo
        Glide.with(binding.IvPersonaje.context).load(personajeModel.imagen).into(binding.IvPersonaje)
    }
}