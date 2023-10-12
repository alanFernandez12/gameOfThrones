package com.trabajoIntegrador.gameOfThrones


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trabajoIntegrador.gameOfThrones.adapter.PersonajeAdapter
import com.trabajoIntegrador.gameOfThrones.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import kotlin.math.log


class MainActivity : AppCompatActivity() {

    //Variables
    private lateinit var filtro: EditText
    lateinit var toolbar: Toolbar
    private  var personajeList: MutableList<Personaje> = mutableListOf()
    private lateinit var adapter: PersonajeAdapter
    /////////

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_main)

        Log.d("debug","antes de la api")

        //comienza API
        initApi()

        //filtrado de personajes por sus atributos
        filtro=findViewById(R.id.scFilter)



        filtro.addTextChangedListener { userFilter ->
            val searchText = userFilter.toString().lowercase()

            val personajeFiltrado = personajeList.filter { personaje ->
                personaje.firstName.lowercase().contains(searchText) ||
                        personaje.lastName.lowercase().contains(searchText) ||
                        personaje.family.lowercase().contains(searchText) ||
                        personaje.title.lowercase().contains(searchText)

            }

            adapter.updatePersonaje(personajeFiltrado)
        }



        Log.d("debug","antes del recycler")

        Log.d("debug","despues del recycler")
        //toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.titulo)

    }



    private fun initApi() {
        val api = RetrofitClient.retrofit.create(MyApi::class.java)
        val callGetCharacter = api.getCharacters()

        callGetCharacter.enqueue(object : retrofit2.Callback<List<Personaje>> {

            override fun onResponse(
                call: Call<List<Personaje>>,
                response: Response<List<Personaje>>
            ){
                Log.d("debug", "variables del onResponse")
                val personajes = response.body()
                Log.d("debug", "despues de la val ${personajes?.get(1)?.lastName}")
                if (personajes != null) {

                    personajeList.addAll(personajes)

                    Log.d("Error", "entrando al if de personajes")

                    initRecyclerView()

                } else {
                    Log.d("debug", "personajes nulos")
                }


            }

            override fun onFailure(call: Call<List<Personaje>>, t: Throwable) {
                Log.e("error", "error al llamar a la api")
            }


        })

    }

    private fun initRecyclerView() {
        val recyclerView= findViewById<RecyclerView>(R.id.recyclerMain)
        adapter = PersonajeAdapter(personajeList)

        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter=adapter


        Log.d("debug","despues inflar el recycler")

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