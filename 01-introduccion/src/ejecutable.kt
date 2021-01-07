import java.io.File
import java.util.*
import javax.print.DocFlavor

fun main(){
    val fileNameJugador2: String =
            "C:/Users/hp/Documents/aplicaciones moviles/gitkraken/ams-falcon-falcon-ricardo-david/deberCrud/Jugadores.txt"
    val fileNameEquipos2: String =
            "C:/Users/hp/Documents/aplicaciones moviles/gitkraken/ams-falcon-falcon-ricardo-david/deberCrud/Equipos.txt"
    do{
        println("\nElija la opcion")
        println("\n PERFILES")
        println("\n Jugadores")
        println(
                "1 -> Mostrar Jugadores\n" +
                        "2 -> Agregar Jugador\n" +
                        "3 -> Actualizar Jugador\n" +
                        "4 -> Eliminar Jugador"
        )
        println("\n Equipos")
        println(
                "5 -> Mostrar Todos los Equipos\n" +
                    //    "6 -> Mostrar Equipos por Jugadores\n" +
                        "7 -> Agregar Equipo\n" +
                        "8 -> Actualizar Equipo\n" +
                        "9 -> Eliminar Equipo\n" +
                        "0 -> Salir\n"
        )

        val scan = Scanner(System.`in`)
        var n = scan.nextLine().trim().toInt()

        when(n){
            1 ->{
                desplegarJugadores(fileNameJugador2)
            }
            2 ->{
                println(
                        "Ingrese los datos del nuevo jugador como se indica:\n" +
                                "'Nombre', 'Apellido', 'Nacionalidad', 'Edad'\n" +
                                "ó 0 para cancelar"
                )
                var scan1 = Scanner(System.`in`)
                val n1 = scan.nextLine()
                if (!n1.equals("0")){
                   insertarJugador(n1,fileNameJugador2)
                    println("Jugador ingresado correctamente\n")
                }
            }
            3 ->{
                println("Digite el numero del jugador a actualizar")
                desplegarJugadores(fileNameJugador2)
              //  println("0, Cancelar")
                val scan = Scanner(System.`in`)
                val n = scan.nextLine()
                if (!n.equals("0")){
                    val jugador2: String = busquedaJugadoresById(fileNameJugador2,n)
                    val idJugador2 = tokenizer(jugador2)
                    if (!jugador2.equals("vacio")){
                        print("${jugador2}\n")
                        println(
                                "Ingrese los datos modificados del  jugador como se indica\n" +
                                        "'Nombre', 'Apellido', 'Nacionalidad', 'Edad'\n" +
                                        "ó 0 para cancelar"
                        )
                        val scan1= Scanner(System.`in`)
                        val n1 = scan1.nextLine()
                        if (!n1.equals(0)){
                            actualizarJugador(n1,fileNameJugador2,idJugador2[0])
                            println("Jugador actualizado correctamente\n")
                        }


                    }else println("No existe un jugador asocciado con ese numero\n")
                }
            }
            4 ->{
                println("\nDigite el numero del jugador a eliminar")
                desplegarJugadores(fileNameJugador2)
                val scan = Scanner(System.`in`)
                val n = scan.nextLine()
                val jugador2: String = busquedaJugadoresById(fileNameJugador2,n)
                if (!jugador2.equals("vacio")){
                    print("${jugador2}\n")
                    println(
                            "Esta seguro que desea eliminar ese jugador\n" +
                                    "Presione S para confirmar o N para cancelar"

                    )
                    val scan1 = Scanner(System.`in`)
                    val n1 = scan1.nextLine()
                    if (n1.toLowerCase().equals("s")){
                        eliminarJugador(jugador2,fileNameJugador2)
                        println("Jugador eliminado correctamente\n")
                    }else{
                        println("Operacion Cancelada")
                    }
                }else println("No existe un jugador asociado con ese numero\n")
            }
            5 ->{
                desplegarEquipos(fileNameEquipos2,fileNameJugador2)
            }
            6 ->{
                println("\nDigite el numero del jugador")
                desplegarJugadores(fileNameJugador2)
               // println("0, Cancelar")
                val scan = Scanner(System.`in`)
                val n = scan.nextLine()
                if (!n.equals("0")){
                    desplegarEquiposByJugadores(fileNameEquipos2,fileNameJugador2,n)
                }
            }
            7 ->{
                println("\n Digite el numero del Jugador del nuevo Equipo")
                desplegarJugadores(fileNameJugador2)
              //  println("0, Cancelar")
                val scan = Scanner(System.`in`)
                val n  = scan.nextLine()
                if (!n.equals("0")){
                    println(
                            "Ingrese los datos del nuevo Equipo como se indica\n" +
                                    "'Nombre', 'Ciudad', 'Copas'\n" +
                                    "ó 0 para cancelar"
                    )
                    val n1 = scan.nextLine()
                    if (!n1.equals("0")){
                        insertarEquipo(n1, fileNameEquipos2, n)
                        println("Equipo ingresada correctamente\n")
                    }
                }
            }
            8 ->{
                println("Digite el numero del equipo a actualizar")
                desplegarEquipos(fileNameEquipos2, fileNameJugador2)
                println("0, Cancelar")
                val scan = Scanner(System.`in`)
                val n  = scan.nextLine()
                if (!n.equals("0")){
                    println("El equipo seleccionado es: ")
                    desplegarEquipoById(fileNameEquipos2,fileNameJugador2,n)
                    println(
                            "Ingrese los datos modificados del Equipo como se indica\n" +
                                    "'Nombre', 'Ciudad', 'Copas'\n" +
                                    "ó 0 para cancelar"
                    )
                    val n1 = scan.nextLine()
                    if (!n1.equals("0")){
                        println("Seleccione el Jugador del equipo")
                        desplegarJugadores(fileNameJugador2)
                       // println("0, Cancelar")
                        val n2 = scan.nextLine()
                        if(!n2.equals("0")){
                            actualizarEquipo(n1,fileNameEquipos2,n,n2)
                            println("Equipo Actualizado correctamente\n")

                        }
                    }
                }

            }
            9 ->{
                println("\nDigite el numero del equipo a eliminar")
                desplegarEquipos(fileNameEquipos2, fileNameJugador2)
            //    println("0, Cancelar")
                val scan = Scanner(System.`in`)
                val n  = scan.nextLine()
                if (!n.equals("0")){
                    println("El equipo seleccionado es: ")
                    var equipo2 = busquedaEquipoById(fileNameEquipos2,n)
                    desplegarEquipoById(fileNameEquipos2,fileNameJugador2,n)
                    println(
                            "Esta seguro que desea eliminar ese jugador\n" +
                                    "Presione S para confirmar o N para cancelar"

                    )
                    val scan1 = Scanner(System.`in`)
                    val n1 = scan1.nextLine()
                    if(n1.toLowerCase().equals("s")){
                        eliminarEquipo(equipo2,fileNameEquipos2)
                        println("Equipo eliminadao correctamente\n")

                    }
                }
                else {
                    println("Operacion Cancelada\n")
                }
            }
            else ->{
                n = 0
            }

        }

    } while ( n !=0)
}

//TOKEN
fun tokenizer(input:String):List<String>{
    val result = input.split(", ")
    return result
}

fun desplegarJugadores(filename: String){
    println("Id, Nombre, Apellido, Nacionalidad, Edad")
    val lines: List<String> = File(filename).readLines()
    var i: Int = 0
    while (i < lines.size){
        var jugador2 = tokenizer(lines[i])
        var jugador2aux = Jugador2(
                jugador2[0].toInt(), jugador2[1], jugador2[2], jugador2[3], jugador2[4].toInt()
        ).toString()
        println(jugador2aux)
        i++
    }
}
fun busquedaJugadoresById(filename: String, id: String): String{
    val lines: List<String> = File(filename).readLines()
    var i: Int = 0
    while (i< lines.size){
        var jugador2 = tokenizer(lines[i])
        if (jugador2[0].equals(id)){
            return  lines.get(i)
        }
        i++
    }
    return "vacio"
}
fun insertarJugador(input: String, filename: String){
    var jugador2 = tokenizer(input)
    var id: Int = consultarIdJugador(filename)
    var nuevojugador = Jugador2(
            id, jugador2[0], jugador2[1], jugador2[2], jugador2[3].toInt()
    ).ingresarJugador(filename)
}
fun actualizarJugador(input: String, filename: String, id: String){
    var jugador2 = tokenizer(input)
    var nuevojugador = Jugador2(
            id.toInt(), jugador2[0], jugador2[1], jugador2[2], jugador2[3].toInt()
    ).actualizarJugador(filename)
}

fun eliminarJugador(input: String, filename: String){
    var jugador2 = tokenizer(input)
    var jugadoreliminado = Jugador2(
            jugador2[0].toInt(), jugador2[1], jugador2[2], jugador2[3], jugador2[4].toInt()
    ).eliminarJugador(filename)
}

fun consultarIdJugador(filename: String):Int{
    val lines: List<String> =File(filename).readLines()
    var idJugador = tokenizer(lines[(lines.size - 1)])
    return (idJugador[0].toInt() + 1)
}

fun desplegarEquipos(filenameEquipo2: String, filenameJugador2: String){
    println("Numero, Nombre, Ciudad, Copas, Jugador")
    val lines: List<String> = File(filenameEquipo2).readLines()
    var i: Int = 0
    while (i < lines.size){
        var equipo2 = tokenizer(lines[i])
        var equipo2aux = Equipo2(
                equipo2[0].toInt(), equipo2[1], equipo2[2], equipo2[3].toInt(), equipo2[4].toInt()
        )
        var jugador2 = tokenizer(busquedaJugadoresById(filenameJugador2,equipo2[4]))
        println(equipo2aux.toString() +", " +"${jugador2[1]} ${jugador2[2]}")
        i++
    }
}

fun desplegarEquiposByJugadores(filenameEquipo2: String, filenameJugador2: String, idJugador2: String){
    println("Numero, Nombre, Ciudad, Copas, Jugador")
    val lines: List<String> = File(filenameEquipo2).readLines()
    var i : Int= 0
    while ( i< lines.size){
        var equipo2 = tokenizer(lines[i])
        if (equipo2[4].equals(idJugador2)){
            var equipoaux = Equipo2(
                    equipo2[0].toInt(), equipo2[1], equipo2[2], equipo2[3].toInt(), equipo2[4].toInt()
            )
            var jugador2 = tokenizer(busquedaJugadoresById(filenameJugador2,equipo2[4]))
            println(equipoaux.toString() + "${jugador2[1]} ${jugador2[2]}")
        }
        i++
    }
}

fun insertarEquipo(input: String, filename: String, idJugador2: String){
    var equipo2 = tokenizer(input)
    var id: Int = consultarIdEquipo(filename)
    var nuevoequipo = Equipo2(
            id, equipo2[0], equipo2[1], equipo2[2].toInt(), idJugador2.toInt()
    ).ingresarEquipo(filename)
}

fun actualizarEquipo(input: String, filename: String, id: String, idJugador2:String){
    var equipo2 = tokenizer(input)
    var nuevoequipo =Equipo2(
            id.toInt(), equipo2[0], equipo2[1], equipo2[2].toInt(), idJugador2.toInt()
    ).actualizarEquipo(filename)

}

fun eliminarEquipo(input: String, filename: String){
    var equipo2 = tokenizer(input)
    var equipoeliminado = Equipo2(
            equipo2[0].toInt(), equipo2[1], equipo2[2], equipo2[3].toInt(), equipo2[4].toInt()
    ).eliminarEquipo(filename)
}
fun busquedaEquipoById(filename: String, id: String): String{
    val lines: List<String> = File(filename).readLines()
    var i: Int = 0
    while (i < lines.size){
        var equipo2 = tokenizer(lines[i])
        if (equipo2[0].equals(id)){
            return lines.get(i)
        }
        i++
    }
    return "vacio"
}
fun desplegarEquipoById(filenameEquipo2: String, filenameJugador2: String, id: String){
    var equipo2 = tokenizer(busquedaEquipoById(filenameEquipo2, id))
    var equipoaux = Equipo2(
            equipo2[0].toInt(), equipo2[1], equipo2[2], equipo2[3].toInt(), equipo2[4].toInt()
    )
    var jugador2 = tokenizer(busquedaJugadoresById(filenameJugador2, equipo2[4]))
    println(equipoaux.toString() + "${jugador2[1]} ${jugador2[2]}")

}

fun consultarIdEquipo(filename: String):Int{
    val lines: List<String> = File(filename).readLines()
    var i: Int = 0
    var idEquipo2 = tokenizer(lines[(lines.size -1)])
    return (idEquipo2[0].toInt()+1)
}