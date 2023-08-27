package com.trabajoIntegrador.gameOfThrones
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.trabajoIntegrador.gameOfThrones.adapter.PersonajeAdapter
import com.trabajoIntegrador.gameOfThrones.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    /////////////
    private lateinit var binding: ActivityMainBinding

    private var personajeMutableList: MutableList<Personaje> =
        PersonajesProvider.personajeList.toMutableList()


    private lateinit var adapter: PersonajeAdapter
    ///////////////////////////////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.scFilter.addTextChangedListener { userFilter ->
            val searchText= userFilter.toString().lowercase()

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
        val intentPersonaje= Intent(this,PersonajeActivity::class.java)
        intentPersonaje.putExtra("NOMBRE", personaje.nombre)
        startActivity(intentPersonaje)
    }

}