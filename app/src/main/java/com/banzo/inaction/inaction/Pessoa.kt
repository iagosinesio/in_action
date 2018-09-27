package com.banzo.inaction.inaction

import java.util.*

class Pessoa {
    private val nome: String
    private val email: String? = null
    private val nascimento: Date? = null

    fun getNome(): String {
        return nome
    }

    fun setNome(nome: String) {
        this.nome = nome
    }

}