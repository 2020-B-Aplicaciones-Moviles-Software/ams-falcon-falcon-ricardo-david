package com.example.aplicacionmovilsoftware

class BBaseDeDatos {
    companion object{
        val arregloEnteros = arrayListOf<Int>()
        val arregloEntrenadores = arrayListOf<BEntrenador>()
        fun inicializarEntrenadores(){
            arregloEntrenadores.add(BEntrenador("Alex","Abogado"))
            arregloEntrenadores.add(BEntrenador("Ana","Enferemera"))
            arregloEntrenadores.add(BEntrenador("Julio","Docente"))
            arregloEntrenadores.add(BEntrenador("Diana","Doctora"))
            arregloEntrenadores.add(BEntrenador("Paul","Economista"))
            arregloEntrenadores.add(BEntrenador("Andrea","Ingeniera"))


        }
        fun inicializarArreglo(){
            arregloEnteros.add(1)
            arregloEnteros.add(2)
            arregloEnteros.add(3)
            arregloEnteros.add(4)
            arregloEnteros.add(5)


        }
        fun anadirItemAlArreglo(item:Int){
            arregloEnteros.add(item)


        }

        fun anadirItemAlArreglo(item:BEntrenador){
            arregloEntrenadores.add(item)


        }
    }
}