package pt.ulusofona.cm.kotlin.observerobservable

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