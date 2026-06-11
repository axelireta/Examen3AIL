package com.example.examen3.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.examen3.model.Usuario

@Dao
interface UsuarioDao {
    @Insert
    suspend fun insertarUsuario(usuario: Usuario)
}