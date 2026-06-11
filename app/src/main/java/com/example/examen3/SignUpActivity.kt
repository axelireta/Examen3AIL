package com.example.examen3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.examen3.db.AppDatabase
import com.example.examen3.model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ettNombre = findViewById<EditText>(R.id.ettNombreUsuarioRegistro)
        val ettContrasena = findViewById<EditText>(R.id.ettContrasenaRegistro)
        val btnRegistrarse = findViewById<Button>(R.id.btnRegistrarse)

        val db = AppDatabase.getInstance(this)

        btnRegistrarse.setOnClickListener {
            val nombre = ettNombre.text.toString()
            val contrasena = ettContrasena.text.toString()

            lifecycleScope.launch(Dispatchers.IO) {
                db.usuarioDao().insertarUsuario(
                    Usuario(nombre = nombre, contrasena = contrasena)
                )
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SignUpActivity,
                        "Jugador registrado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}