package br.com.house.digital.entities

import br.com.house.digital.interfaces.Item
import br.com.house.digital.utils.localCurrency

class Livro(
    override val codigo: Int,
    var titulo: String,
    var autor: String,
    var anoLancamento: Int,
    var quantidadeEstoque: Int,
    override var preco: Double
) : Item {
    init {
        if (quantidadeEstoque <= 0) {
            quantidadeEstoque = 1
            println("Não é possível inicializar um livro com quantidade 0! Quantidade 1 atribuída automaticamente. [Código: $codigo - Título: $titulo]")
        }

        if (preco <= 0) {
            preco = 10.0
            println("Não é possível inicializar um livro com preço menor que 0! Preço de R$ 10,00 atribuído automaticamente.")
        }
    }

    override fun toString(): String {
        return "Código: $codigo - Título: $titulo - Autor: $autor - Ano: $anoLancamento - Quantidade: $quantidadeEstoque - Preço: R$ ${preco.localCurrency()}"
    }
}