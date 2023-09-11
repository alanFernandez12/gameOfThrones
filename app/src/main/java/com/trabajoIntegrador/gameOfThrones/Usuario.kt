package com.trabajoIntegrador.gameOfThrones

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "usuarios_table")
data class Usuario(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo("nombre") var nombre: String,
    @ColumnInfo("contr") var contr: String,
    @ColumnInfo("email") var email: String
)
