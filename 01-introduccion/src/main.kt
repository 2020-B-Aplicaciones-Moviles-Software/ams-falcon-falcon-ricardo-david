import javax.swing.text.StyledEditorKit

fun main (){
    println("Hola mundo")



     //declarar varibles
    var edadProfesor: Int =31
    var edadProfesor =31
   var sueldoProfesor: Double =12.34
    var suelodProfesor =12.34

    // tipos de variables: mutables/inmutables
    // MUTABLES



    var edadCachorro: Int = 0
    var edadCachorro = 1
    var edadCachorro = 2
    var edadCachorro = 3
    //INMUTABLES
    //no pueden ser reasignadas
    val numeroCedula = 18128128128
    //numeroCedula = 121212
    // tratar de utilizar siempre las varibales inmutables

    // tipos de variables

    val nombreProfesor: String = "RicardoFalcon"
    val dueldo: Double = 12.2
    val estadoCivil: Char = 'S'
     val fechaNacimiento: Date = Date()

    // Condicionales
    //if
    if(true){
        verdadera
    }else{
        falso
    }
    when
    when(sueldo){
        12.2 ->{//inicio Bloque
            println("sueldo normal")
        } // fin bloque
        2.2 -> println("Sueldo negativo")
        4.2 -> println("Sueldo negativo")
        5.2 -> println("Sueldo negativo")
        else -> println("sueldo no reconocido")

    }

    val sueldoMayorAEstablecido: Int = if (sueldo > 12.2) 500 else 0
    condicion ? bloque-true : bloque-false

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
    arregloDinamico.add(11)
    arregloDinamico.add(12)

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

    //Any ALL-> Condicion -> Boolean
    // mandamos una condicion y devuelve un boolean
    // OR <->AND
    // OR =ANY
    // OR (FALSO - SI TODOS SON FALSOS)
    // OR (VERDADERO - UNO ES TRUE )
    //AND = ALL
    // AND(FALSO- UNO ES FALSO YA ES FALSO)
    // AND (TRUE - TODOS SON TRUE ES TRUE)

    val respuestaAny:Boolean = arregloDinamico
            .any{
                valorActualIteracion ->
                return@any (valorActualIteracion > 5)
            }

    println(respuestaAny)


    val respuestaAll: Boolean = arregloDinamico
            .all{
                valorActualIteracion ->
                return@all valorActualIteracion > 5
            }

    println(respuestaAll)

    // Operador reduce
    //devuelve un acumulado
    // el acumulado tiene un valor donde se empieza
    //{1,2,3,4,5}
    //0=0+1
    //1=1+2
    //3=3+3
    //6=6+4
    //10=10+5
    //15

    val respiestaFilter:Int = arregloDinamico
            .reduce{ // valor inicial =0
                acumulado, valorActualIteracion ->
                return@reduce  acumulado + valorActualIteracion

            }
    println(respiestaFilter)


    val respuestaReduceFold = arregloDinamico
            .fold(
                    100,
                    {
                        acumulado, valorActualIteracion ->
                        return@fold  acumulado - valorActualIteracion
                    }
            )

    println(respuestaReduceFold)

    //arregloMutable.fold(empieza desde el principio)
    //arregloMutable.foldRight(empieza desde el final)
    //arregloMutable.reduce(empieza desde el inicio)
    //arregloMutable.reduceRight(empieza desde el final)
    //OPERADORES
    //forEach = Unit (void)
    //map = Arreglo
    //filter= arreglo
    //all=booleano
    // any= booleano
    //reduce = valor
    //fold = valor

    val vidaActual: Double = arregloDinamico
            .map { it * 2.3 }// arreglo
            .filter{ it > 20} //arreglo
            .fold(100.00,{acc,i -> acc -i})//valor
            .also{ println(it)}//ejecutar codigo extra
    println("Valor vida actual ${vidaActual}")


    val ejemploUno = Suma (1,2)
    val ejemploDos = Suma (null,2)
    val ejemploTres = Suma (1,null)
    val ejemploCuatro = Suma (null,null)

    println(ejemploUno.sumar())
    println(Suma.historialSuma)
    println(ejemploDos.sumar())
    println(Suma.historialSuma)
    println(ejemploTres.sumar())
    println(Suma.historialSuma)
    println(ejemploCuatro.sumar())
    println(Suma.historialSuma)


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
abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int
    constructor(//constructor primario
            uno:Int,
            dos:Int

    ){// bloque de codigo del constructor primario
       //this.numeroUno
        numeroUno= uno
        //this.numeroDos
        numeroDos=dos
        println("HOLA")
    }

}

//instancia.numeroUno
//instancia.numeroDos
abstract class Numeros( //constructor primario
protected var numeroUno: Int,
protected var numeroDos: Int

){
    init {
        println("HOLA")
    }

}

class Suma(
        uno:Int, //parametros
        dos:Int //parametros
):Numeros(uno, dos){
    init {
        //this.numeroUno
        //this.numeroDos
        //no podemos acceder a this.uno
        //no podemos acceder a this.dos

    }
    constructor(// segundo constructor
            uno: Int?, //paramentros
            dos: Int // parametros
    ) :this (
            if (uno == null) 0 else uno,
            dos
    ){

    }

    constructor(// tercero constructor
            uno: Int, //paramentros
            dos: Int? // parametros
    ) :this (
            uno,
            if (dos == null) 0 else dos

    ){

    }

    constructor(// cuarto constructor
            uno: Int?, //paramentros
            dos: Int? // parametros
    ) :this (
            if (uno == null) 0 else uno,
            if (dos == null) 0 else dos

    ){

    }



    public fun sumar(): Int{
        val total: Int = numeroUno + numeroDos
        Suma.agregarHistorial(total)
        return total


    }
    //singleton
    companion object { // metodos y propieddades
        val historialSuma = arrayListOf<Int>()
        fun agregarHistorial(nuevaSuma: Int){
            this.historialSuma.add(nuevaSuma)
        }
    }
}
class BaseDeDatos(){
    companion object {
        val datos = arrayListOf<Int>()
    }
}
//BaseDeDatos.datos