package com.example.aplicacionmovilsoftware

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class ACicloVida : AppCompatActivity() {
    var total = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a_ciclo_vida)
        Log.i("ciclo-vida","onCreate")

        val botonCicloVida= findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida
            .setOnClickListener{
                aumentarTotal()
            }

    }
    override  fun onSaveInstanceState(outState: Bundle){
        outState.run {
            //Guardar las variables
            // primitivos
            putInt("totalGuardado", total)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val totalRecuperado: Int? = savedInstanceState.getInt("totalGuardado")
        if (totalRecuperado != null){
            this.total= totalRecuperado
            val txvACicloVida = findViewById<TextView>(R.id.txv_ciclo_vida)
            txvACicloVida.text = total.toString()
        }
    }
    fun aumentarTotal(){
        total = total +1
        val txvACicloVida = findViewById<TextView>(R.id.txv_ciclo_vida)
        txvACicloVida.text = total.toString()
    }
    override fun onStart(){
        super.onStart()
        Log.i("ciclo-vida","onStart")
    }
    override fun onRestart(){
        super.onRestart()
        Log.i("ciclo-vida","onRestart")
    }

    override fun onResume(){
        super.onResume()
        Log.i("ciclo-vida","onResume")
    }
    override fun onPause(){
        super.onPause()
        Log.i("ciclo-vida","onPause")
    }
    override fun onStop(){
        super.onStop()
        Log.i("ciclo-vida","onStop")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.i("ciclo-vida","onDestroy")
    }

}