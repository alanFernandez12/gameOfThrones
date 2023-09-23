package com.trabajoIntegrador.gameOfThrones


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.trabajoIntegrador.gameOfThrones.adapter.PersonajeAdapter
import com.trabajoIntegrador.gameOfThrones.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    private lateinit var binding: ActivityMainBinding // simplificando el llamado a la vista

    private var personajeMutableList: MutableList<Personaje> =
        PersonajesProvider.personajeList.toMutableList() // lista mutable para el filtrado de busqueda


    private lateinit var adapter: PersonajeAdapter


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

        //toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.titulo)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_cerrarSesion) {

            var preferencias =
                getSharedPreferences(resources.getString((R.string.sp_credenciales)), MODE_PRIVATE)
            preferencias.edit().putString(resources.getString(R.string.nombre_usuario), "").apply()
            preferencias.edit().putString(resources.getString(R.string.password_usuario), "")
                .apply()
            var intentLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)
            finish()
        }
        if (item.itemId == R.id.item_libro) {
            var intentBook = Intent(this, BooksActivity::class.java)
            startActivity(intentBook)
            finish()
        }
        if (item.itemId == R.id.item_house) {
            var intentHouse = Intent(this, HouseActivity::class.java)
            startActivity(intentHouse)
            finish()

            val preferencias =
                getSharedPreferences(resources.getString((R.string.sp_credenciales)), MODE_PRIVATE)
            val editar = preferencias.edit()
            editar.putString(resources.getString(R.string.nombre_usuario), "")
            editar.putString(resources.getString(R.string.password_usuario), "")
            editar.apply()

        }
        return super.onOptionsItemSelected(item)
    }

}