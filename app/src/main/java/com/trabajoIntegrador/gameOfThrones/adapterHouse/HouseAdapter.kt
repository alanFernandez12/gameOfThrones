package com.trabajoIntegrador.gameOfThrones.adapterHouse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trabajoIntegrador.gameOfThrones.HouseAtribute
import com.trabajoIntegrador.gameOfThrones.R

class HouseAdapter(private val houselist:List<HouseAtribute>) : RecyclerView.Adapter<HouseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HouseViewHolder(layoutInflater.inflate(R.layout.item_house,parent,false))


    }

    override fun getItemCount(): Int = houselist.size


    override fun onBindViewHolder(holder: HouseViewHolder, position: Int) {
        val item = houselist[position]
        holder.render(item)
    }


}