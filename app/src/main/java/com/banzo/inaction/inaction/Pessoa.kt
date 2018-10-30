package com.banzo.inaction.inaction

import java.util.*

class Pessoa {
    var uid: String = ""
    var nome: String = ""
    var email: String = ""
    var nascimento: String = ""
    var peso: String = ""
    var altura: String = ""


    override fun toString(): String{
        return  nome
    }

}
