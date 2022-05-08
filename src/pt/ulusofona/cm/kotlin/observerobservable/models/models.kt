package pt.ulusofona.cm.kotlin.observerobservable.models

import pt.ulusofona.cm.kotlin.observerobservable.interfaces.*
import pt.ulusofona.cm.kotlin.observerobservable.exceptions.*

data class Noticia(var titulo: String, var corpo: String) {}

class CorreioDaLusofona(var maxLeitores: Int, private var noticias: List<Noticia>) {
    private var leitores: MutableList<OnNoticiaListener> = mutableListOf()

    fun adicionarLeitor(leitor: OnNoticiaListener) {
        if (leitores.size == maxLeitores) {
            throw LimiteDeLeitoresAtingidoException("CorreioDaLusofona atingiu o número máximo de leitores: $maxLeitores")
        } else {
            leitores.add(leitor)
            leitor.leitorAdicionadoComSucesso()
        }
    }

    fun removerLeitor(leitor: OnNoticiaListener) {
        if (leitores.contains(leitor)) {
            leitores.remove(leitor)
            leitor.leitorRemovidoComSucesso()
        } else {
            throw LeitorInexistenteException()
        }
    }

    //Testing notificar leitores in news
    private fun notificarLeitores() {
        for (noticia in noticias) {
            for (leitor in leitores) {
                leitor.onReceiveNoticia(noticia)
            }
        }
    }

    fun iniciar() {
        notificarLeitores()
    }
}

class GeradorNumerico(var maxLeitores: Int, private var numeros: List<Int>) {
    private var leitores: MutableList<OnNumeroListener> = mutableListOf()

    fun adicionarLeitor(leitor: OnNumeroListener) {
        if (leitores.size == maxLeitores) {
            throw LimiteDeLeitoresAtingidoException("GeradorNumerico atingiu o número máximo de leitores: $maxLeitores")
        } else {
            leitores.add(leitor)
            leitor.leitorAdicionadoComSucesso()
        }
    }

    fun removerLeitor(leitor: OnNumeroListener) {
        if (leitores.contains(leitor)) {
            leitores.remove(leitor)
            leitor.leitorRemovidoComSucesso()
        } else {
            throw LeitorInexistenteException()
        }
    }

    private fun notificarLeitores() {
        for (numero in numeros) {
            for (leitor in leitores) {
                leitor.onReceiveNumero(numero)
            }
        }
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
        if (num % 2 == 0) {
            numeros.add(num)
        }
    }

    fun imprimeNumeros() : String {
        return "$nome leu os seguintes numeros pares: $numeros"
    }
}

class LeitorImpar(nome: String) : Leitor(nome), OnNumeroListener {
    val numeros : MutableList<Int> = mutableListOf()

    override fun onReceiveNumero(num: Int) {
        if (num % 2 != 0) {
            numeros.add(num)
        }
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