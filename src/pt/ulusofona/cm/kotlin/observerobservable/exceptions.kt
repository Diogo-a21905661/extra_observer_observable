package pt.ulusofona.cm.kotlin.observerobservable

//Exception que representa a mensagem "Este leitor não está registado!"
class LeitorInexistenteException(message: String = "Este leitor não está registado!") : Exception(message)

//Exception para quando o gerador atinge o máximo número de leitores
class LimiteDeLeitoresAtingidoException(message: String) : Exception(message)