package br.com.house.digital.exercicio

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
            listaLivros.forEach {
                if (it.codigo == codigo) {
                    it.toString()
                } else {
                    println("Livro não encontrado")
                }
            }
        } else if (tipo == "colecao") {
            listaColecao.forEach {
                if (it.codigo == codigo) {
                    it.toString()
                } else {
                    println("Coleção não encontrada")
                }
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
                }
            } else {
                println("Livro não encontrado")
            }
        } else if (tipo == "colecao") {
            val colecao: Colecao? = listaColecao.find { it.codigo == codigo }

            if (colecao != null) {
                colecao.livros.forEach {
                    if (verificaEstoque(it) == false) {
                        println("O livro ${it.titulo} da coleção ${colecao.descricao} não possui estoque.")
                        return
                    } else {
                        it.quantidadeEstoque -= 1
                        if (it.quantidadeEstoque == 0) {
                            listaLivros.remove(it)
                        }
                    }
                }
            } else {
                println("Coleção não encontrado")
            }
        } else {
            println("Método de venda inválido!")
        }
    }

    fun verificaEstoque(livro: Livro): Boolean {
        val livro: Livro? = listaLivros.find { it.equals(livro) }
        return if (livro != null) {
            livro.quantidadeEstoque >= 1
        } else {
            false
        }
    }
}