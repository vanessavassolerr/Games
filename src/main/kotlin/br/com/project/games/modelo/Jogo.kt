package br.com.project.games.modelo

data class Jogo(val titulo:String,
                val capa: String ) {
    var descricao:String? = null // val sao valores q n podem ser alterados
    override fun toString(): String {
        return "Meu jogo:'\n" +
                "Titulo: $titulo' \n" +
                "Capa: $capa \n" +
                "Descricaoo: $descricao'"
    }
}
