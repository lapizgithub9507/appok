package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast

var check1: CheckBox? = null

class Registro : AppCompatActivity() , View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)


        check1 = findViewById(R.id.checkBox2)
    }

    override fun onClick(view: View?) {
        val opcion = check1?.isChecked
        if(opcion == true){
            val boton1=findViewById<Button>(R.id.BotonRegistrarse)
            boton1.setOnClickListener {
                val lanzar = Intent(this, principal::class.java)
                startActivity(lanzar)
            }

        }else{
            Toast.makeText(this, "Debe aceptar terminos y condiciones para continuar", Toast.LENGTH_LONG).show()
        }

    }
}