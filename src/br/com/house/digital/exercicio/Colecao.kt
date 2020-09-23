package br.com.house.digital.exercicio

class Colecao(
        val codigo: Int,
        var preco: Double,
        var descricao: String,
        val livros: MutableList<Livro>
)