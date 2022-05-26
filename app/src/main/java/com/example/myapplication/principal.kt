package com.example.myapplication

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class principal : AppCompatActivity() {
    var txt_cedula: EditText?=null
    var txt_nomape: EditText?=null
    var txt_placa: EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        txt_cedula=findViewById(R.id.txt_cedula)
        txt_nomape=findViewById(R.id.txt_nomape)
        txt_placa=findViewById(R.id.txt_placa)
    }
    fun insertar(view: View) {
        var con = SQLite(this, "administracion", null, 1)
        var baseDatos = con.writableDatabase

        var cedula = txt_cedula?.text.toString()
        var nomape = txt_nomape?.text.toString()
        var placa = txt_placa?.text.toString()

        if (cedula.isEmpty() == false && nomape.isEmpty() == false && placa.isEmpty() == false) {
            var registro = ContentValues()
            registro.put("cedula", cedula)
            registro.put("nomape", nomape)
            registro.put("placa", placa)
            baseDatos.insert("conductores", null, registro)
            txt_cedula?.setText("")
            txt_nomape?.setText("")
            txt_placa?.setText("")
            Toast.makeText(this, "Se insertado exitosamente", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Los campos deben tener texto", Toast.LENGTH_LONG).show()
        }
        baseDatos.close()
    }
    fun buscar(view:View) {
        val con = SQLite(this, "administracion", null, 1)
        val baseDatos = con.writableDatabase
        val cedula = txt_cedula?.text.toString()
        if (cedula.isEmpty() == false) {
            val fila = baseDatos.rawQuery(
                "select nomape,placa from conductores where cedula='$cedula'",
                null
            )
            if (fila.moveToFirst() == true) {
                txt_nomape?.setText(fila.getString(0))
                txt_placa?.setText(fila.getString(1))
                baseDatos.close()
            } else {
                txt_nomape?.setText("")
                txt_placa?.setText("")
                Toast.makeText(this, "No se encontraron registros", Toast.LENGTH_LONG).show()
            }
        }
    }
    fun eliminar(view:View){
        val con=SQLite(this,"administracion",null,1)
        val baseDatos=con.writableDatabase
        val cedula=txt_cedula?.text.toString()
        if(cedula.isEmpty()==false){
            val cant=baseDatos.delete("conductores","cedula='"+cedula+"'",null)
            if(cant>0){
                Toast.makeText(this,"El conductor fue eliminado",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"El conductor no se encontro",Toast.LENGTH_LONG).show()
            }
            txt_cedula?.setText("")
            txt_nomape?.setText("")
            txt_placa?.setText("")
        }else{
            Toast.makeText(this,"Debe diligenciar una cedula",Toast.LENGTH_LONG).show()
        }

    }
    fun editar(view:View){
        val con=SQLite(this,"administracion",null,1)
        val baseDados=con.writableDatabase

        val cedula=txt_cedula?.text.toString()
        val nomape=txt_nomape?.text.toString()
        val placa=txt_placa?.text.toString()

        if(!cedula.isEmpty() && !nomape.isEmpty() && !placa.isEmpty() ){
            var registro=ContentValues()
            registro.put("cedula",cedula)
            registro.put("nomape",nomape)
            registro.put("placa",placa)

            val cant=baseDados.update("conductores",registro,"cedula='$cedula'",null)

            if(cant>0){
                Toast.makeText(this,"El registro se ha editado exitosamente",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"El registro no fue encontrado",Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this,"Los campos no deben estar vacios",Toast.LENGTH_LONG).show()
        }
    }
}