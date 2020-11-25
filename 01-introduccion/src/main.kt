fun main (){
    println("Hola mundo")

    // declarar varibles
    // var edadProfesor: Int =31
    var edadProfesor =31
   // var sueldoProfesor: Double =12.34
    var suelodProfesor =12.34

    // tipos de variables: mutables/inmutables
    // MUTABLES



    //var edadCachorro: Int = 0
    //var edadCachorro = 1
    //var edadCachorro = 2
    //var edadCachorro = 3
    //INMUTABLES
    //no pueden ser reasignadas
    val numeroCedula = 18128128128
    //numeroCedula = 121212
    // tratar de utilizar siempre las varibales inmutables

    // tipos de variables

    val nombreProfesor: String = "RicardoFalcon"
    val dueldo: Double = 12.2
    val estadoCivil: Char = 'S'
    // val fechaNacimiento: Date = Date()

    // Condicionales
    //if
    if(true){
        //verdadera
    }else{
        //falso
    }
    //when
    /*when(sueldo){
        12.2 ->{//inicio Bloque
            println("sueldo normal")
        } // fin bloque
        2.2 -> println("Sueldo negativo")
        4.2 -> println("Sueldo negativo")
        5.2 -> println("Sueldo negativo")
        else -> println("sueldo no reconocido")

    }*/

    //val sueldoMayorAEstablecido: Int = if (sueldo > 12.2) 500 else 0
    //condicion ? bloque-true : bloque-false

    imprimirNombre(nombre = "Ricardo")
    //calcularSueldo(1000.00)
    calcularSueldo(sueldo = 1000.00)
    // calcularSueldo(1000.00, 14.00)
    calcularSueldo(sueldo = 1000.00,tasa = 14.00)
    //calcularSueldo(1000.00,12.00, 3)
    calcularSueldo(sueldo = 1000.00,tasa = 12.00,calculoEspecial = 3)


    //parametros nombrados
    calcularSueldo(

            calculoEspecial = 3,
            tasa = 14.00,
            sueldo = 1000.00


    )

    // arreglos estaticos
    val arregloEstatico:Array <Int> = arrayOf(1,2,3)
    // no se puede modificar los arreglos
    // arreglos dinamicos
    val arregloDinamico:ArrayList <Int> = arrayListOf(1,2,3,4,5,6,7,8,9,10)
    println(arregloDinamico)

    //Operadores.- sirven para los arreglos estaticos y dinamicos

    arregloEstatico.forEach {  }
    arregloDinamico.forEach{ }
    val respuestaForEach: Unit = arregloDinamico
            .forEach{
                valorActualIteracion ->
                println("valor ${valorActualIteracion}")

            }
    println(respuestaForEach)

    arregloDinamico
            .forEachIndexed{ indice, valorActualIteracion ->
                println("valor ${valorActualIteracion} Indice: ${indice}")
            }



    // operador map
    // sirve para modificar cada uno de los elementos del
    //arreglo

    val respuestaMap: List<Int> = arregloDinamico
            .map {

                valorActualIteracion ->
                //calculo1
                //calculo 2
                return@map valorActualIteracion *10
            }

    println(respuestaMap)

    val respuestaMapDos: List<Int> = arregloDinamico
            .map { it + 15}

    println(respuestaMapDos)

    // Operador de Filtros

    val respuestaFilter: List<Int> = arregloDinamico
            .filter {
                valorActualIteracion ->
                val mayoresACinco: Boolean = valorActualIteracion > 5
                return@filter mayoresACinco
            }
    println(respuestaFilter)

}// FIN BLOQUE MAIN
fun imprimirNombre(nombre: String){
    println("Nombre ${nombre}") //Template Strings


}

fun calcularSueldo(
        sueldo: Double, // requerido
        tasa: Double = 12.00, // opcionales
        calculoEspecial: Int? = null// variables que Pueden ser null
): Double{
    if(calculoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa)* calculoEspecial
    }

}
