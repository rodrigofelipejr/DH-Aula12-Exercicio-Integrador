package br.com.house.digital

import br.com.house.digital.entities.Colecao
import br.com.house.digital.entities.Estoque
import br.com.house.digital.entities.Livro

fun main(){
    val livro1 = Livro(1, "Livro 1", "João", 2020, 5, 15.00)
    val livro2 = Livro(2, "Livro 2", "Vitoria", 2017, 10, 30.00)
    val livro3 = Livro(3, "Livro 3", "Jessica", 2015, 15, 30.00)
    val livro4 = Livro(4, "Livro 4", "Rodrigo", 2019, 2, 40.00)
    val livro5 = Livro(5, "Livro 5", "Vitor", 2017, 1, 20.00)

    val livro6 = Livro(6, "Livro 6", "Renan", 2017, 1, 20.00) //livro para add

    val listaTodos = mutableListOf(livro1, livro2, livro3, livro4, livro5)
    val listaProfs = mutableListOf(livro1, livro2, livro3)

    val colecao1 = Colecao(1,100.00,"Todos os livros",listaTodos)
    val colecao2 = Colecao(2,50.00,"Livros dos professores", listaProfs)

    val colecao3 = Colecao(3,40.00,"Coleção novas", mutableListOf(livro4, livro5, livro6))// colecao para add

    val listaColecao = mutableListOf(colecao1,colecao2)

    val estoque = Estoque(listaTodos, listaColecao)

    estoque.cadastrarLivro(livro6)

    estoque.cadastrarColecao(colecao3)

    estoque.consultar(6, "livro")
    estoque.consultar(7, "livro") //livro codigo invalido
    estoque.consultar(1, "colecao")
    estoque.consultar(6, "cachoro")

    estoque.vender(6, 2, "livro") //maior que a quantidade em estoque
    estoque.vender(6, 1, "livro") //venda ok
    estoque.vender(10, 1, "livro") //livro que não existe

    estoque.vender(3, 1,"colecao")
    println("------------------------------- Teste ----------------------------")
    estoque.consultar(1, "livro")
    estoque.consultar(2, "livro")
    estoque.consultar(3, "livro")
    estoque.vender(2, 2,"colecao")
    estoque.consultar(1, "livro")
    estoque.consultar(2, "livro")
    estoque.consultar(3, "livro")

}