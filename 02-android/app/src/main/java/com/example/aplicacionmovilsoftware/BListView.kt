package com.example.aplicacionmovilsoftware

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class BListView : AppCompatActivity() {
    var idItemSeleccionado=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_list_view)

        val listView = findViewById<ListView>(R.id.lv_list_view)
        val arreglo = arrayListOf(1,2,3,4,5)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, // como se va a ver ek xml
        //BBaseDeDatos.arregloEnteros // arreglo
                BBaseDeDatos.arregloEntrenadores
        )
        listView.adapter = adaptador
        //1)registrar como un menu contextual
        registerForContextMenu(listView)
/*
        listView
                .setOnItemLongClickListener { parent, view, position, id ->
                   //abrirDialogo()
                    return@setOnItemLongClickListener true
                }

        val botonListView = findViewById<Button>(R.id.btn_list_view)
        botonListView
            .setOnClickListener{
                anadirItem(BBaseDeDatos.arregloEntrenadores,adaptador)
            }

*/


    }

    /*fun anadirItem(adaptador:ArrayAdapter<Int>
    ){
        BBaseDeDatos.anadirItemAlArreglo(1)
        adaptador.notifyDataSetChanged()


    }*/
// 2) seleccionar el xml a usar en el menu contextual
    override fun onCreateContextMenu(

            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?) {


        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado =id
        Log.i("intent-explicito","ID ${id}")
    }

    override fun onContextItemSelected(
            item: MenuItem
    ): Boolean {
        return when(item.itemId){
            //Editar
            R.id.mi_editar ->{
                Log.i("intent-explicito","Editar" +
                "${BBaseDeDatos.arregloEntrenadores[idItemSeleccionado]}")
                return true
            }
            //Eliminar
            R.id.mi_eliminar ->{
                Log.i("intent-explicito","Eliminar" +
                "${BBaseDeDatos.arregloEnteros[idItemSeleccionado]}")
                return true
            }

            else ->super.onContextItemSelected(item)
        }
    }


    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Titulo")
        // builder.setMessage("Descripcion")

        val seleccionPrevia = booleanArrayOf(
                true,
                false,
                false
        )
        val opciones = resources.getStringArray(
                R.array.string_array_opciones_dialogo)
        builder.setMultiChoiceItems(
                opciones,
                seleccionPrevia,
                {
                    dialog,
                    which,
                    isChecked  ->
                    Log.i("intent-explicito","Dio clic en el item")

                }
                //null
        )

        builder.setPositiveButton(
                "Aceptar",
                DialogInterface.OnClickListener { dialog, which ->
                    Log.i("intent-explicito","Hola =) ")
                }

        )
        builder.setNegativeButton(
                "Cancelar",
                null
        )



        val dialogo = builder.create()
        dialogo.show()

    }
    fun anadirItem(arreglo:ArrayList<BEntrenador>,adaptador:ArrayAdapter<*>
    ){
        //BBaseDeDatos.anadirItemAlArreglo(BEntrenador("Camila","Estudiante"))
        adaptador.notifyDataSetChanged()


    }
}