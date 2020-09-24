package br.com.house.digital.entities

class Estoque(private val listaLivros: MutableList<Livro>, private val listaColecao: MutableList<Colecao>) {

    init {

    }

    fun cadastrarLivro(livro: Livro) {
        listaLivros.add(livro)
        println("Livro ${livro.titulo} add ao estoque")
    }

    fun cadastrarColecao(colecao: Colecao) {
        listaColecao.add(colecao)
        println("Coleção ${colecao.descricao} add ao estoque")
    }

    fun consultar(codigo: Int, tipo: String) {

        if (tipo == "livro") {
            val livro: Livro? = listaLivros.find { it.codigo == codigo }

            if (livro == null) {
                println("Livro não encontrado")
            } else {
                println(livro)
            }
        } else if (tipo == "colecao") {
            val colecao: Colecao? = listaColecao.find { it.codigo == codigo }

            if (colecao == null) {
                println("Coleção não encontrado")
            } else {
                println(colecao)
            }
        } else {
            println("Método de consulta inválido!")
        }
    }

    fun vender(codigo: Int, quantidade: Int, tipo: String) {
        if (tipo == "livro") {
            val livro: Livro? = listaLivros.find { it.codigo == codigo }

            if (livro != null) {
                if (quantidade > livro.quantidadeEstoque) {
                    println("Quantidade maior que o estoque atual")
                } else {
                    if ((livro.quantidadeEstoque - quantidade) == 0) {
                        listaLivros.remove(livro)
                    } else {
                        listaLivros.forEach {
                            if (it.equals(livro)) {
                                it.quantidadeEstoque -= quantidade
                            }
                        }
                    }
                    println("Livro ${livro.titulo} vendido com sucesso!")
                }
            } else {
                println("Livro não encontrado")
            }
        } else if (tipo == "colecao") {
            val colecao: Colecao? = listaColecao.find { it.codigo == codigo }

            if (colecao != null) {
                colecao.livros.forEach {
                    if (verificaEstoque(it, quantidade) == false) {
                        println("O livro ${it.titulo} da coleção ${colecao.descricao} não possui estoque.")
                        return
                    } else {
                        it.quantidadeEstoque -= quantidade
                        if (it.quantidadeEstoque == 0) {
                            listaLivros.remove(it)
                        }
                    }
                    println("Coleção ${colecao.descricao} vendida com sucesso!")
                }
            } else {
                println("Coleção não encontrado")
            }
        } else {
            println("Método de venda inválido!")
        }
    }

    fun verificaEstoque(livro: Livro, quantidade: Int): Boolean {
        val livro: Livro? = listaLivros.find { it.equals(livro) }
        return if (livro != null) {
            livro.quantidadeEstoque >= quantidade
        } else {
            false
        }
    }
}