package com.trabajoIntegrador.gameOfThrones


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.trabajoIntegrador.gameOfThrones.adapter.PersonajeAdapter
import com.trabajoIntegrador.gameOfThrones.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    private lateinit var binding: ActivityMainBinding // simplificando el llamado a la vista

    private lateinit var personajeMutableList: MutableList<Personaje> /*=
        PersonajesProvider.personajeList.toMutableList() // lista mutable para el filtrado de busqueda
        */


    private lateinit var adapter: PersonajeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //// rutinar para poder usar el binding e iniciar el layaout
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //Iniciamos el API
        initApi()


        //filtrado de personajes por sus atributos
        binding.scFilter.addTextChangedListener { userFilter ->
            val searchText = userFilter.toString().lowercase()

            val personajeFiltrado = personajeMutableList.filter { personaje ->
                personaje.firstName.lowercase().contains(searchText) ||
                        personaje.lastName.lowercase().contains(searchText) ||
                        personaje.family.lowercase().contains(searchText) ||
                        personaje.title.lowercase().contains(searchText)

            }

            adapter.updatePersonaje(personajeFiltrado)
        }

        initRecyclerView()

        //toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.titulo)

    }



    private fun initApi() {
        val api = RetrofitClient.retrofit.create(MyApi::class.java)
        val callGetCharacter= api.getCharacters()

        callGetCharacter.enqueue(object : retrofit2.Callback<List<Personaje>>{

            override fun onResponse(
                call: Call<List<Personaje>>,
                response: Response<List<Personaje>>
            ) {
                val personajes= response.body()
                if(personajes != null){
                    val listPersonaje: List<Personaje> = personajes
                    personajeMutableList.clear()
                    personajeMutableList.addAll(listPersonaje)


                }
            }

            override fun onFailure(call: Call<List<Personaje>>, t: Throwable) {
                Log.e("error", t.message?:" ")
            }

        })
    }

    private fun initRecyclerView() {
        adapter = PersonajeAdapter(personajeMutableList) { personaje ->
            onItemSelected(personaje)

        }

        binding.recyclerMain.layoutManager = LinearLayoutManager(this)
        binding.recyclerMain.adapter = adapter

    }

    fun onItemSelected(personaje: Personaje) {
        Toast.makeText(this, personaje.firstName, Toast.LENGTH_SHORT).show()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_cerrarSesion) {

            var preferencias = getSharedPreferences(resources.getString((R.string.sp_credenciales)), MODE_PRIVATE)
            preferencias.edit().putString(resources.getString(R.string.nombre_usuario), "").apply()
            preferencias.edit().putString(resources.getString(R.string.password_usuario), "").apply()
            var intentLogin= Intent(this,LoginActivity::class.java)
            startActivity(intentLogin)
            finish()
        }
        if(item.itemId== R.id.item_libro){
            var intentBook= Intent(this,BooksActivity::class.java)
            startActivity(intentBook)
            finish()
        }
        if(item.itemId==R.id.item_house){
            var intentHouse= Intent(this,HouseActivity::class.java)
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