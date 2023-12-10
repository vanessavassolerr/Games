package br.com.project.games.principal

import br.com.project.games.modelo.Gamer
import br.com.project.games.modelo.Jogo
import br.com.project.games.servicos.ConsumoApi
import java.util.*
import transformarEmIdade

fun main() {
    val leitura = Scanner(System.`in`)
    val gamer = Gamer.criarGamer(leitura)
    println("Cadastro concluído com sucesso!. Dados do gamer:")
    println(gamer)
    println("Idade do gamer: " + gamer.dataNascimento?.transformarEmIdade())

    do {
        println("Digite um codigo de jogo para buscar: ")
        val busca = leitura.nextLine()

        val buscaApi = ConsumoApi()
        val informacaoJogo = buscaApi.buscaJogo(busca)

        var meuJogo: Jogo? = null

        val resultado = runCatching {
                meuJogo = Jogo(
                informacaoJogo.info.title,
                informacaoJogo.info.thumb
            )
        }
        resultado.onFailure { println("Jogo inexistente, tente outro id.")
        }

        resultado.onSuccess {
            println("Deseja inserir uma descricao personalizada S/N ")
            val opcao = leitura.nextLine()
            if (opcao.equals("s", true)) {
                println("Insira a descricao personalizada pelo jogo: ")
                val descricaoPersonzalida = leitura.nextLine()
                meuJogo?.descricao = descricaoPersonzalida
            } else {
                meuJogo?.descricao = meuJogo?.titulo

            }
            gamer.jogosBuscados.add(meuJogo)
        }


        print("Dejeseja buscar um novo jogo? S/N")
        val resposta = leitura.nextLine()

    }while (resposta.equals("s", true))

    println("Jogos buscados:")
    println(gamer.jogosBuscados)

    println("\n Jogos ordenados por título:")
    gamer.jogosBuscados.sortBy {
        it?.titulo
    }

    gamer.jogosBuscados.forEach {
        println("Título:" +  it?.titulo)
    }

    val jogosFiltrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("batman", true) ?: false
    }
    println("Jogos filtrados:")
    println(jogosFiltrados)


    println("Deseja excluir algum item da lista original? S/N: ")
    val opcao = leitura.nextLine()
    if (opcao.equals("S", true)){
        println(gamer.jogosBuscados)
        println("\nInforme a posicao do jogo que deseja excluir: ")
        val posicao = leitura.nextInt()
        gamer.jogosBuscados.removeAt(posicao)
    }
    println("Lista atualizada: ")
    println(gamer.jogosBuscados)


    println("Busca finalizada com sucesso.")

}