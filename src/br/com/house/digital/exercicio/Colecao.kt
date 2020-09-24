package br.com.house.digital.exercicio

class Colecao(
    val codigo: Int,
    var preco: Double,
    var descricao: String,
    val livros: MutableList<Livro>
) {
    override fun toString(): String {
        return "Código: ${codigo} - Descrição: ${descricao} - Livros: [${livros.forEach { it.titulo }}] - Preço: R$ ${preco}"
    }
}