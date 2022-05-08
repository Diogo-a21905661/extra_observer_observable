package pt.ulusofona.cm.kotlin.observerobservable.models

import pt.ulusofona.cm.kotlin.observerobservable.interfaces.*
import pt.ulusofona.cm.kotlin.observerobservable.exceptions.*

data class Noticia(var titulo: String, var corpo: String) {}

class CorreioDaLusofona(var maxLeitores: Int, private var noticias: List<Noticia>) {
    private var leitores: MutableList<OnNoticiaListener> = mutableListOf()

    fun adicionarLeitor(leitor: OnNoticiaListener) {
        leitores.add(leitor)
    }

    fun removerLeitor(leitor: OnNoticiaListener) {
        if (leitores.contains(leitor)) {
            leitores.remove(leitor)
        } else {
            throw LeitorInexistenteException()
        }
    }

    private fun notificarLeitores() : List<Noticia> {
        return noticias
    }

    fun iniciar() {
        notificarLeitores()
    }
}

class GeradorNumerico(var maxLeitores: Int, private var numeros: List<Int>) {
    private var leitores: MutableList<OnNumeroListener> = mutableListOf()

    fun adicionarLeitor(leitor: OnNumeroListener) {
        leitores.add(leitor)
    }

    fun removerLeitor(leitor: OnNumeroListener) {
        if (leitores.contains(leitor)) {
            leitores.remove(leitor)
        } else {
            throw LeitorInexistenteException()
        }
    }

    private fun notificarLeitores() : List<Int> {
        return numeros
    }

    fun iniciar() {
        notificarLeitores()
    }
}

abstract class Leitor(var nome: String) : Registavel {
    private var registado: Boolean = false

    //Altera atributo registado para true
    override fun leitorAdicionadoComSucesso() {
        registado = true
    }

    //Altera atributo registado para false
    override fun leitorRemovidoComSucesso() {
        registado = false
    }

    fun estaRegistado() : Boolean {
        return registado
    }
}

class LeitorPar(nome: String) : Leitor(nome), OnNumeroListener {
    val numeros : MutableList<Int> = mutableListOf()

    override fun onReceiveNumero(num: Int) {
        numeros.add(num)
    }

    fun imprimeNumeros() : String {
        return "$nome leu os seguintes numeros pares: $numeros"
    }
}

class LeitorImpar(nome: String) : Leitor(nome), OnNumeroListener {
    val numeros : MutableList<Int> = mutableListOf()

    override fun onReceiveNumero(num: Int) {
        numeros.add(num)
    }

    fun imprimeNumeros() : String {
        return "$nome leu os seguintes numeros impares: $numeros"
    }
}

class Bufo(nome: String) : Leitor(nome), OnNumeroListener, OnNoticiaListener {
    val numeros : MutableList<Int> = mutableListOf()
    val noticias : MutableList<Noticia> = mutableListOf()

    override fun onReceiveNumero(num: Int) {
        numeros.add(num)
    }

    override fun onReceiveNoticia(noticia: Noticia) {
        noticias.add(noticia)
    }

    fun imprimeNumeros() : String {
        return "$nome escutou os seguintes numeros: $numeros"
    }

    fun imprimeNoticias() : String {
        return "$nome leu as seguintes noticias: $noticias"
    }
}