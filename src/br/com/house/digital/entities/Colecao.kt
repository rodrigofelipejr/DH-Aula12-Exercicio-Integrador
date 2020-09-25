package br.com.house.digital.entities

import br.com.house.digital.interfaces.Item
import br.com.house.digital.utils.localCurrency

class Colecao(
    override val codigo: Int,
    override var preco: Double,
    var descricao: String,
    var livros: MutableSet<Livro>
) : Item {
    override fun toString(): String {
        var listaLivros: String = ""
        livros.forEach { listaLivros += it.titulo + ", " }
        return "Código: $codigo - Descrição: $descricao - Livros: [${listaLivros.dropLast(2)}] - Preço: R$ ${preco.localCurrency()}"
    }
}