package com.trabajoIntegrador.gameOfThrones.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.trabajoIntegrador.gameOfThrones.Personaje
import com.trabajoIntegrador.gameOfThrones.PersonajeActivity
import com.trabajoIntegrador.gameOfThrones.R

class PersonajeAdapter(private var personajeList:List<Personaje>, private val onClickListener:(Personaje) -> Unit) : RecyclerView.Adapter<PersonajeHolder>() {

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
        val activity=holder.itemView.context
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context,item.nombre,Toast.LENGTH_SHORT).show()
            val intentPersonaje=Intent(it.context,PersonajeActivity::class.java)
            intentPersonaje.putExtra("Nombre",item.nombre.toString())
            activity.startActivity(intentPersonaje)

        }
    }
    fun updatePersonaje(personajeList:List<Personaje>){
        this.personajeList=personajeList
        notifyDataSetChanged()
    }
}