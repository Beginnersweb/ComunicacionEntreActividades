package com.example.comunicacion_entre_actividades

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

const val USER_PHONE = "org.bedu.activities.USER_PHONE"


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("activities", "onCreate Login")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener referencias a los elementos de la interfaz
        val etName: EditText = findViewById(R.id.etName)
        val etEdad: EditText = findViewById(R.id.etEdad)
        val btnSubmit: Button = findViewById(R.id.btnEnviar)

        // Filtro para permitir solo letras en el campo de nombre
        etName.filters = arrayOf(InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                if (!source[i].isLetter() && source[i] != ' ') {  // Solo letras y espacios permitidos
                    return@InputFilter ""
                }
            }
            null
        })

        // Validación adicional con TextWatcher para asegurar que contiene al menos 3 letras
        etName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Filtra solo letras para contar la cantidad de letras válidas
                val lettersOnly = s.toString().filter { it.isLetter() }
                if (lettersOnly.length < 3) {
                    etName.error = "El nombre debe contener al menos 3 letras"
                } else {
                    etName.error = null // Limpiar error si es válido
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })


        // Limitar el campo de edad contenga 2 números
        val maxLength = 2
        etEdad.filters = arrayOf(InputFilter.LengthFilter(maxLength))

        // Validar que la edad contenga 2 números
        etEdad.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Validar que la edad contenga 2 números
                if (s.toString().length != 2 || !s.toString().matches(Regex("\\d{2}"))) {
                    etEdad.error = "La edad debe contener 2 números"
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Validar que la edad esté entre 05 y 99
        etEdad.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val edadStr = s.toString()
                if (edadStr.isNotEmpty()) {
                    val edad = edadStr.toIntOrNull() ?: 0
                    if (edad < 5) {
                        etEdad.error = "Debe tener al menos 05 años"
                    } else if (edad > 99) {
                        etEdad.error = "La edad máxima es 99 años"
                    } else {
                        etEdad.error = null // Edad válida
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
// Botón para enviar el formulario
        btnSubmit.setOnClickListener {
            val name = etName.text.toString().trim()
            val edadStr = etEdad.text.toString().trim()

            // Validación de los campos
            var isValid = true

            if (name.isEmpty()) {
                etName.error = "El nombre es obligatorio"
                isValid = false
            } else if (name.filter { it.isLetter() }.length < 3) {
                etName.error = "El nombre debe contener al menos 3 letras"
                isValid = false
            } else {
                etName.error = null
            }

            // Validación de la edad
            if (edadStr.isEmpty()) {
                etEdad.error = "La edad es obligatoria"
                isValid = false
            } else {
                val edad = edadStr.toIntOrNull()
                if (edad == null || edad < 5) {
                    etEdad.error = "Debe tener al menos 05 años"
                    isValid = false
                } else if (edad > 99) {
                    etEdad.error = "La edad máxima es 99 años"
                    isValid = false
                } else {
                    etEdad.error = null
                }
            }

            // Si todo es válido, enviar a la siguiente actividad
            if (isValid) {
                val intent = Intent(this, LoggedActivity::class.java).apply {
                    putExtra("USER_NAME", name)
                    putExtra("USER_EDAD", edadStr)
                }
                startActivity(intent)
            }
        }

    }
    // Sobrescribimos los callbacks
    override fun onStart() {
        super.onStart()
        Log.d("activities","onStart Login")
        // Aquí puedes agregar código adicional si es necesario
    }

    override fun onResume() {
        super.onResume()
        Log.d("activities","onResume Login")
        // Aquí puedes agregar código adicional si es necesario
    }

    override fun onPause() {
        Log.d("activities","onPause Login")
        super.onPause()
        // Aquí puedes agregar código adicional si es necesario
    }

    override fun onStop() {
        Log.d("activities","onStop Login")
        super.onStop()
        // Aquí puedes agregar código adicional si es necesario
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("activities","onRestart Login")
        // Aquí puedes agregar código adicional si es necesario
    }

    override fun onDestroy() {
        Log.d("activities","onDestroy Login")
        super.onDestroy()
        // Aquí puedes agregar código adicional si es necesario
    }
}