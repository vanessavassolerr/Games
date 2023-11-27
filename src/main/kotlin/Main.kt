import com.google.gson.Gson
import java.lang.NullPointerException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.Scanner


fun main() {
    val leitura = Scanner(System.`in`)
    println("Digite um codigo de jogo para buscar: ")
    val busca = leitura.nextLine()
    val endereco = "https://www.cheapshark.com/api/1.0/games?id=$busca"


    val client: HttpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create(endereco))
        .build()
    val response = client
        .send(request, BodyHandlers.ofString())

    val json = response.body()
    println(json)

    val gson = Gson()
    val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)

//    try {
//        val meuJogo = Jogo(meuInfoJogo.info.title,
//            meuInfoJogo.info.thumb)
//        print(meuJogo)
//    } catch (ex: NullPointerException) {
//        println("Jogo inexistente, tente outro id.")
//    }

    var meuJogo:Jogo? = null
    val resultado = runCatching {
                val meuJogo = Jogo(
                    meuInfoJogo.info.title,
                    meuInfoJogo.info.thumb)


    }
resultado.onFailure { println("Jogo inexistente, tente outro id.")}
    resultado.onSuccess {

    println("Deseja inserir uma descricao personalizada S/N ")
    val opcao = leitura.nextLine()
    if (opcao.equals("s", true)) {
        println("Insira a descricao personalizada pelo jogo: ")
        val descricaoPersonzalida = leitura.nextLine()
        meuJogo?.descricao
    } else {
        meuJogo?.descricao = meuJogo?.titulo

    }
        print(meuJogo)
    }
}