package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val boton1=findViewById<Button>(R.id.BotonIngresar)
        boton1.setOnClickListener {
            val lanzar = Intent(this,principal::class.java)
            startActivity(lanzar)
        }

        val boton2=findViewById<Button>(R.id.BotonRegist)
        boton2.setOnClickListener {
            val lanzar2 = Intent(this, Registro::class.java)
            startActivity(lanzar2)
        }
    }
}