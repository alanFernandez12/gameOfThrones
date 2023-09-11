package com.trabajoIntegrador.gameOfThrones.adapterHouse

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.trabajoIntegrador.gameOfThrones.HouseAtribute
import com.trabajoIntegrador.gameOfThrones.R

class HouseViewHolder(view:View):ViewHolder(view) {

    val nombreHouse = view.findViewById<TextView>(R.id.tvNombreHouse)
    val descripcionHouse = view.findViewById<TextView>(R.id.tvDescripcionHouse)
    val lemaHouse = view.findViewById<TextView>(R.id.tvLemaHouse)

    val photo = view.findViewById<ImageView>(R.id.ivHouse)

    fun render(HouseAtribute : HouseAtribute) {
        nombreHouse.text = HouseAtribute.nombre
        descripcionHouse.text = HouseAtribute.descripcion
        lemaHouse.text = HouseAtribute.lema
    }
}