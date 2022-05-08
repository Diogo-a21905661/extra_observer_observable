package pt.ulusofona.cm.kotlin.observerobservable.models

import pt.ulusofona.cm.kotlin.observerobservable.interfaces.*

data class Noticia(var titulo: String, var corpo: String) {}

class CorreioDaLusofona(var maxLeitores: Int, private var noticias: List<Noticia>) {
    private var leitores: MutableList<OnNoticiaListener> = mutableListOf()

    fun adicionarLeitor(leitor: OnNoticiaListener) {
        leitores.add(leitor)
    }

    fun removerLeitor(leitor: OnNoticiaListener) {
        leitores.remove(leitor)
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
        leitores.remove(leitor)
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
    private val numerosPares : MutableList<Int> = mutableListOf()

    override fun onReceiveNumero(num: Int) {
        numerosPares.add(num)
    }

    fun imprimeNumeros() : String {
        return "$nome leu os seguintes numeros pares: $numerosPares"
    }
}

class LeitorImpar(nome: String) : Leitor(nome), OnNumeroListener {
    private val numerosImpares : MutableList<Int> = mutableListOf()

    override fun onReceiveNumero(num: Int) {
        numerosImpares.add(num)
    }

    fun imprimeNumeros() : String {
        return "$nome leu os seguintes numeros impares: $numerosImpares"
    }
}

class Bufo(nome: String) : Leitor(nome), OnNumeroListener, OnNoticiaListener {
    private val numeros : MutableList<Int> = mutableListOf()
    private val noticias : MutableList<Noticia> = mutableListOf()

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