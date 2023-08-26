package com.trabajoIntegrador.gameOfThrones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trabajoIntegrador.gameOfThrones.adapter.PersonajeAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()

    }
    private fun initRecyclerView(){
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerMain)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter= PersonajeAdapter(PersonajesProvider.personajeList)

    }

}