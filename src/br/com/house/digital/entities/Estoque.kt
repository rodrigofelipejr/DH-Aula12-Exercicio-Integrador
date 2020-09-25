package br.com.house.digital.entities

class Estoque() {
    private val listaLivros: MutableSet<Livro> = mutableSetOf()
    private val listaColecao: MutableSet<Colecao> = mutableSetOf()

    fun cadastrar(item: Any) {
        when (item) {
            is Livro -> {
                cadastrarLivro(item)
            }
            is Colecao -> {
                cadastrarColecao(item)
            }
            else -> {
                println("Não é possível cadastrar um item deste tipo no estoque!")
            }
        }
    }

    fun consultar(codigo: Int, buscarColecao: Boolean = false) {
        if (buscarColecao) {
            buscaColecao(codigo)
        } else {
            buscaLivro(codigo)
        }
    }

    fun vender(codigo: Int, quantidade: Int, VendaColecao: Boolean = false) {
        when (VendaColecao) {
            true -> {
                venderColecao(codigo, quantidade)
            }
            false -> {
                venderLivro(codigo, quantidade)
            }
        }
    }

    private fun cadastrarLivro(livro: Livro) {
        val livroEmEstoque: Livro? = buscarLivroPorCodigo(livro.codigo)

        when (livroEmEstoque) {
            null -> {
                listaLivros.add(livro)
                println("Livro \"$livro.titulo\" adicionado ao estoque.")
            }
            else -> {
                livroEmEstoque.quantidadeEstoque += livro.quantidadeEstoque
                livroEmEstoque.titulo = livro.titulo
                livroEmEstoque.autor = livro.autor
                livroEmEstoque.anoLancamento = livro.anoLancamento
                livroEmEstoque.preco = livro.preco

                println("Livro \"${livro.titulo}\" atualizado no estoque.")
            }
        }
    }

    private fun cadastrarColecao(colecao: Colecao) {
        val colecaoEmEstoque: Colecao? = buscarColecaoPorCodigo(colecao.codigo)

        when (colecaoEmEstoque) {
            null -> {
                listaColecao.add(colecao)
                println("Coleção \"{$colecao.titulo}\" adicionada ao estoque.")
            }
            else -> {
                colecaoEmEstoque.preco += colecao.preco
                colecaoEmEstoque.descricao = colecao.descricao
                colecaoEmEstoque.livros = colecao.livros

                println("Coleção \"${colecao.descricao}\" atualizada no estoque.")
            }
        }
    }

    private fun buscarLivroPorCodigo(codigo: Int): Livro? {
        return listaLivros.find { it.codigo == codigo }
    }

    private fun buscarColecaoPorCodigo(codigo: Int): Colecao? {
        return listaColecao.find { it.codigo == codigo }
    }

    private fun buscaLivro(codigo: Int) {
        val livro: Livro? = buscarLivroPorCodigo(codigo)

        when (livro) {
            null ->
                println("Não foi possível encontrar um livro com o código $codigo. Refaça sua busca! ")
            else ->
                println("Resultado consulta (livro): $livro")
        }
    }

    private fun buscaColecao(codigo: Int) {
        val colecao: Colecao? = listaColecao.find { it.codigo == codigo }

        when (colecao) {
            null ->
                println("Não foi possível encontrar uma coleção com o código $codigo. Refaça sua busca! ")
            else ->
                println("Resultado consulta (coleção): $colecao")
        }
    }

    private fun venderLivro(codigo: Int, quantidade: Int) {
        val livro: Livro? = listaLivros.find { it.codigo == codigo }

        when (livro) {
            null -> {
                println("Livro (código: ${codigo}) não encontrado")
            }
            else -> {
                if (quantidade > livro.quantidadeEstoque) {
                    println("Quantidade de venda (${quantidade}) maior que o estoque atual (${livro.quantidadeEstoque})")
                } else {
                    if ((livro.quantidadeEstoque - quantidade) == 0) {
                        println("Venda das última(s) unidade(s) realizada! Reabasteça o estoque: (${livro.toString()})")
                        listaLivros.remove(livro)
                    } else {
                        livro.quantidadeEstoque -= quantidade
                    }

                    println("Livro ${livro.titulo} vendido com sucesso!")
                }
            }
        }
    }

    private fun venderColecao(codigo: Int, quantidade: Int) {
        val colecao: Colecao? = listaColecao.find { it.codigo == codigo }

        when (colecao) {
            null -> {
                println("Coleção (código: ${codigo}) não encontrada")
            }
            else -> {
                colecao.livros.forEach {
                    if (verificaEstoque(it, quantidade) == false) {
                        println("O livro ${it.titulo} da coleção ${colecao.descricao} não possui estoque. VENDA CANCELADA")
                        return
                    } else {
                        it.quantidadeEstoque -= quantidade
                        if (it.quantidadeEstoque == 0) {
                            println("Venda das última(s) unidade(s) realizada! Reabasteça o estoque: (${it.toString()})")
                            listaLivros.remove(it)
                        }
                    }
                }
                println("Coleção ${colecao.descricao} vendida com sucesso!")
            }
        }
    }

    private fun verificaEstoque(livro: Livro, quantidade: Int): Boolean {
        val livro: Livro? = listaLivros.find { it.equals(livro) }
        return if (livro != null) {
            livro.quantidadeEstoque >= quantidade
        } else {
            false
        }
    }
}


