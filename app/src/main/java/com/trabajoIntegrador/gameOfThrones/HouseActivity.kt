package com.trabajoIntegrador.gameOfThrones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar

class HouseActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_house)

        toolbar = findViewById(R.id.toolbarHouse)
        setSupportActionBar(toolbar)
        supportActionBar!!.title=resources.getString(R.string.titulo)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_house, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_cerrarSesionC) {
            var preferencias = getSharedPreferences(resources.getString((R.string.sp_credenciales)), MODE_PRIVATE)
            preferencias.edit().putString(resources.getString(R.string.nombre_usuario), "").apply()
            preferencias.edit().putString(resources.getString(R.string.password_usuario), "").apply()
            var intentLogin= Intent(this,LoginActivity::class.java)
            startActivity(intentLogin)
            finish()
        }
        if(item.itemId== R.id.item_personajesC){
            var intentMain= Intent(this,MainActivity::class.java)
            startActivity(intentMain)
            finish()
        }
        if(item.itemId==R.id.item_booksC){
            var intentBook= Intent(this,BooksActivity::class.java)
            startActivity(intentBook)
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}