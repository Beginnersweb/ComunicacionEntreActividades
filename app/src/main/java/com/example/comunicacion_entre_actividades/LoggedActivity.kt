package com.example.comunicacion_entre_actividades

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoggedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged)

        // Obtener el nombre y la edad enviados desde MainActivity
        val name = intent.getStringExtra("USER_NAME")
        val edad = intent.getStringExtra("USER_EDAD")

        // Mostrar un mensaje de bienvenida junto con el nombre y la edad
        val welcomeMessage = "Bienvenido/a, \n$name. \nTienes $edad años."

        // Mostrar el mensaje en un TextView
        val textViewWelcome: TextView = findViewById(R.id.textViewWelcome)
        textViewWelcome.text = welcomeMessage

        // Mostrar el mensaje en un Toast
        Toast.makeText(this, welcomeMessage, Toast.LENGTH_LONG).show()

        Log.d("activities", "Nombre: $name, Edad: $edad")

    }
}
