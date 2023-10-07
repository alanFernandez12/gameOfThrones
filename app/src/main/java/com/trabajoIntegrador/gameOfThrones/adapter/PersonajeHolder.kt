package com.trabajoIntegrador.gameOfThrones.adapter

import android.view.View

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.trabajoIntegrador.gameOfThrones.Personaje

import com.trabajoIntegrador.gameOfThrones.databinding.ItemPersonajeBinding


class PersonajeHolder(view: View) : RecyclerView.ViewHolder(view) {
    //push
    val binding = ItemPersonajeBinding.bind(view)


    fun render(personajeModel: Personaje) {
        binding.nombrePer.text = personajeModel.firstName
        binding.apellidoPer.text = personajeModel.lastName
        binding.familiaPer.text = personajeModel.family
        binding.tituloPer.text = personajeModel.title
        Glide.with(binding.IvPersonaje.context).load(personajeModel.imageUrl)
            .into(binding.IvPersonaje)
    }
}