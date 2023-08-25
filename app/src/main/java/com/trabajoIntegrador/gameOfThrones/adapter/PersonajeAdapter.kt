package com.trabajoIntegrador.gameOfThrones.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trabajoIntegrador.gameOfThrones.Personaje
import com.trabajoIntegrador.gameOfThrones.R

class PersonajeAdapter(private val personajeList:List<Personaje>) : RecyclerView.Adapter<PersonajeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeHolder {
        val layaoutInFlater= LayoutInflater.from(parent.context)
        return PersonajeHolder(layaoutInFlater.inflate(R.layout.item_personaje,parent,false))
    }

    override fun getItemCount(): Int {
        return personajeList.size
    }

    override fun onBindViewHolder(holder: PersonajeHolder, position: Int) {
        val item=personajeList[position]
        holder.render(item)
        holder.itemView.setOnClickListener {
            // Acción a realizar al hacer clic en un elemento
        }
    }

}