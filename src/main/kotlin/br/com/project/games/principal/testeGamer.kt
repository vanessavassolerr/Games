
package br.com.project.games.principal
import br.com.project.games.modelo.Gamer

fun main() {
    val gamer1 = Gamer("  ","vanessa@gmailc.com")
    println(gamer1)

    val gamer2 = Gamer("Julia",
                        "julia@gmail.com",
                        "19/12/1998",
                        "bobsinj")

    println(gamer2)

    gamer1.let{
        it.dataNascimento = "18/10/2000"
        it.usuario = "vassvan"
    }.also{
        println(gamer1.idInterno)
    }

    println(gamer1)
    gamer1.usuario = "roberto"
    println(gamer1)
}