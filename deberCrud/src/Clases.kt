import java.io.File
import java.io.PrintWriter

class Jugador2(var id: Int, var nombre:String, var apellido: String, var nacionalidad: String, var edad:Int){
    override fun toString(): String {
        return "${id}, ${nombre}, ${apellido}, ${nacionalidad}, ${edad}"
    }

    fun ingresarJugador(filename: String){
        File(filename).appendText("\n${id}, ${nombre}, ${apellido}, ${nacionalidad}, ${edad}")
    }
    fun actualizarJugador(filename: String){
        var text:MutableList<String> = File(filename).readLines().toMutableList()
        var i: Int=0
        while (i < text.size){
            var auxiliar = tokenizer(text[i])
            if (auxiliar[0].equals(id.toString())){
                text[i] = "${id}, ${nombre}, ${apellido}, ${nacionalidad}, ${edad}"
                break
            }
            i++
        }
        val writer = PrintWriter(filename)
        writer.print("")
        writer.close()
        i=0
        while (i < text.size){
            if (i !=0)File(filename).appendText("\n${text[i]}")
            else File (filename).appendText("${text[i]}")
            i++
        }
    }
    fun eliminarJugador(filename: String){
        var text: MutableList<String> = File(filename).readLines().toMutableList()
        var i: Int = 0
        while (i < text.size){
            var auxiliar = tokenizer(text[i])
            if (auxiliar[0].equals(id.toString())){
                text[i]="vacio"
                break
            }
            i++
        }
        val writer = PrintWriter(filename)
        writer.print("")
        writer.close()
        i = 0
        while (i<text.size){
            if (!text[i].equals("vacio")){
                if (i!=0) File(filename).appendText("\n${text[i]}")
                else File(filename).appendText("${text[i]}")
            }
            i++
        }
        println(text)
    }
}

class Equipo2(var id: Int, var nombre:String, var ciudad:String, var copas: Int, var jugador2:Int){

    override fun toString(): String {
        return "${id}, ${nombre}, ${ciudad}, ${copas}"

    }
    fun ingresarEquipo(filename: String){
        File(filename).appendText("\n${id}, ${nombre}, ${ciudad}, ${copas}, ${jugador2}")
    }

    fun  actualizarEquipo(filename: String){
        var text: MutableList<String> = File(filename).readLines().toMutableList()
        var i: Int = 0
        while (i < text.size){
            var auxiliar= tokenizer(text[i])
            if (auxiliar[0].equals(id.toString())){
                text[i] = "${id}, ${nombre}, ${ciudad}, ${copas}, ${jugador2}"
                break
            }
            i++
        }
        val writer = PrintWriter(filename)
        writer.print("")
        writer.close()
        i =0
        while (i < text.size){
            if (i != 0) File(filename).appendText("\n${text[i]}")
            else File(filename).appendText("${text[i]}")
            i++

        }
    }

    fun eliminarEquipo(filename: String){
        var text: MutableList<String> = File (filename).readLines().toMutableList()
        var i: Int = 0
        while (i <text.size){
            var auxiliar = tokenizer(text[i])
            if (auxiliar[0].equals(this.id.toString())){
                text[i] = "vacio"
                break
            }
            i++
        }
        val writer = PrintWriter(filename)
        writer.print("")
        writer.close()
        i = 0
        while (i< text.size){
            if (!text[i].equals("vacio")){
                if (i !=0) File(filename).appendText("\n${text[i]}")
                else File(filename).appendText("${text[i]}")
            }
            i++

        }
        println(text)

    }



}



