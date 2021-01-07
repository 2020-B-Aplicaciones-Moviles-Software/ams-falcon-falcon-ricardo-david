package com.example.aplicacionmovilsoftware

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GRecyclerView : AppCompatActivity() {
    var tolaLikes = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_g_recycler_view)
        val listaEntrenador = arrayListOf<BEntrenador>()
        listaEntrenador
            .add(
                BEntrenador(
                    "Ricardo",
                    "1722627039",
                    DLiga("Kanto", "liga pokemon")
                )
            )
        listaEntrenador
            .add(
                BEntrenador(
                    "David",
                    "1712406592",
                    DLiga("Johto", "liga pokemon")
                )
            )
        val recyclerViewEntrenador = findViewById<RecyclerView>(
            R.id.rv_entrenador
        )
        this.iniciarRecyclerView(
            listaEntrenador,
            this,
            recyclerViewEntrenador
        )
    }

    fun iniciarRecyclerView(
        lista: List<BEntrenador>,
        actividad: GRecyclerView,
        recyclerView: androidx.recyclerview.widget.RecyclerView
    ){
        val adaptador = FRecyclerViewAdaptadorNombreCedula(
            lista,
            actividad,
            recyclerView
        )
        recyclerView.adapter= adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()

    }

    fun anadirLikesTotal(){
        val textoLikes = findViewById<TextView>(R.id.tv_likes_completo)
        tolaLikes = tolaLikes + 1
        textoLikes.text = tolaLikes.toString()

    }
}