package com.trabajoIntegrador.gameOfThrones.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.trabajoIntegrador.gameOfThrones.Personaje
import com.trabajoIntegrador.gameOfThrones.PersonajeActivity
import com.trabajoIntegrador.gameOfThrones.R

class PersonajeAdapter(
    private var personajeList: List<Personaje>
) : RecyclerView.Adapter<PersonajeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeHolder {
        Log.d("debug","entrando oncreateviewholder")
        val layaoutInFlater = LayoutInflater.from(parent.context)
        return PersonajeHolder(layaoutInFlater.inflate(R.layout.item_personaje, parent, false))
        Log.d("debug","despues del return oncreateviewholder")
    }

    override fun getItemCount(): Int {
        return personajeList.size
    }

    override fun onBindViewHolder(holder: PersonajeHolder, position: Int) {
        val item = personajeList[position]
        Log.d("debug","despues item=personaleslist")
        holder.render(item)
        val activity = holder.itemView.context
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, item.firstName, Toast.LENGTH_SHORT).show()

            val intentPersonaje = Intent(it.context, PersonajeActivity::class.java)
            intentPersonaje.putExtra("Nombre", item.firstName.toString())
            activity.startActivity(intentPersonaje)

        }
    }

    fun updatePersonaje(personajeList: List<Personaje>) {
        this.personajeList = personajeList
        notifyDataSetChanged()
    }
}