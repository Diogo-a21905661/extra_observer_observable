package pt.ulusofona.cm.kotlin.observerobservable.interfaces

import pt.ulusofona.cm.kotlin.observerobservable.models.*

interface Registavel {
    fun leitorAdicionadoComSucesso()

    fun leitorRemovidoComSucesso()
}

interface OnNoticiaListener : Registavel {
    fun onReceiveNoticia(noticia: Noticia)
}

interface OnNumeroListener : Registavel {
    fun onReceiveNumero(num: Int)
}