package br.com.house.digital.exercicio

class Livro(
    var codigo: Int,
    var titulo: String,
    var autor: String,
    var anoLancamento: Int,
    var quantidadeEstoque: Int,
    var preco: Double
) {
    override fun toString(): String {
        return "Código: ${codigo} - Título: ${titulo} - Autor: ${autor} - Ano: ${anoLancamento} - Quantidade: ${quantidadeEstoque} - Preço: R$ ${preco}"
    }
}