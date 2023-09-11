package com.trabajoIntegrador.gameOfThrones

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.trabajoIntegrador.gameOfThrones.datos.Usuario

@Dao
interface UsuarioDao {
    @Query("select * from usuarios_table")
    fun getAll(): List<Usuario>

    @Query("select * from usuarios_table where nombre = :nombreAux")
    fun getNombre(nombreAux: String): Usuario

    @Insert
    fun insertUsuario(usuario: Usuario)

    @Update
    fun update(usuario: Usuario)

    @Delete
    fun delete(usuario: Usuario)

}