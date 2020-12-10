package com.example.aplicacionmovilsoftware

/*class BEntrenador {
    var nombre: String
    var descripcion: String


    constructor(nombre: String, descripcion: String) {
        this.nombre = nombre
        this.descripcion = descripcion
    }

    override fun toString(): String {
        return "${nombre} ${descripcion}"
    }
} */

class BEntrenador(
        protected  var nombre:String,
        protected var descripcion:String
){
    override fun toString(): String {
        return "${nombre} ${descripcion}"
    }

}