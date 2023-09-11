package com.trabajoIntegrador.gameOfThrones

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.trabajoIntegrador.gameOfThrones.adapter.PersonajeAdapter
import com.trabajoIntegrador.gameOfThrones.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    /////////////
    private lateinit var binding: ActivityMainBinding // simplificando el llamado a la vista

    private var personajeMutableList: MutableList<Personaje> =
        PersonajesProvider.personajeList.toMutableList() // lista mutable para el filtrado de busqueda


    private lateinit var adapter: PersonajeAdapter

    ///////////////////////////////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //// rutinar para poder usar el binding e iniciar el layaout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //filtrado de personajes por sus atributos
        binding.scFilter.addTextChangedListener { userFilter ->
            val searchText = userFilter.toString().lowercase()

            val personajeFiltrado = personajeMutableList.filter { personaje ->
                personaje.nombre.lowercase().contains(searchText) ||
                        personaje.apellido.lowercase().contains(searchText) ||
                        personaje.familia.lowercase().contains(searchText) ||
                        personaje.titulo.lowercase().contains(searchText)

            }

            adapter.updatePersonaje(personajeFiltrado)
        }

        initRecyclerView()


    }

    private fun initRecyclerView() {
        adapter = PersonajeAdapter(personajeMutableList) { personaje ->
            onItemSelected(personaje)

        }

        binding.recyclerMain.layoutManager = LinearLayoutManager(this)
        binding.recyclerMain.adapter = adapter

    }

    fun onItemSelected(personaje: Personaje) {
        Toast.makeText(this, personaje.nombre, Toast.LENGTH_SHORT).show()

    }

}