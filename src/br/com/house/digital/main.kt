package br.com.house.digital

import br.com.house.digital.entities.Colecao
import br.com.house.digital.entities.Estoque
import br.com.house.digital.entities.Livro

fun main() {
    val livro1 = Livro(46172676, "Título 1", "Auto 1", 2020, -8, 15.00)
    val livro2 = Livro(64078455, "Título 2", "Auto 2", 2017, 10, 30.00)
    val livro3 = Livro(55705942, "Título 3", "Auto 3", 2015, 15, 30.00)
    val livro4 = Livro(34048745, "Título 4", "Auto 4", 2019, 2, 40.00)
    val livro5 = Livro(59079041, "Título 5", "Auto 5", 2017, 1, 20.00)
    val livro6 = Livro(59079041, "Título 5", "Auto 5", 2017, 8, 21.00)

    val lista1 = mutableSetOf(livro1, livro2, livro3, livro4, livro5)
    val lista2 = mutableSetOf(livro4, livro5)

    val colecao1 = Colecao(50323, 100.00, "Descrição 1", lista1)
    val colecao2 = Colecao(90995, 50.00, "Descrição 2", lista2)
    val colecao3 = Colecao(90995, 40.00, "Descrição 3", mutableSetOf(livro4, livro5, livro6))

    val listaColecao = mutableSetOf(colecao1, colecao2)

    val estoque = Estoque()

    with(estoque) {
        lista1.forEach { estoque.cadastrar(it) }
        lista2.forEach { estoque.cadastrar(it) }

        consultar(livro6.codigo)
        cadastrar(livro6)
        consultar(livro6.codigo)

        cadastrar("LIVRO") // validação
        consultar(63079041) // código inválido

        cadastrar(colecao1)
        cadastrar(colecao2)

        consultar(livro1.codigo)
        vender(livro1.codigo, 2) // maior que a quantidade em estoque
        vender(livro1.codigo, 1) // venda ok
        vender(colecao1.codigo, 1, true)

        consultar(livro4.codigo)
        consultar(livro5.codigo)
        vender(colecao2.codigo, 4, true)
        consultar(livro4.codigo)
        consultar(livro5.codigo)

        consultar(colecao2.codigo, true)
        cadastrar(colecao3)
        consultar(colecao2.codigo, true)
    }
}