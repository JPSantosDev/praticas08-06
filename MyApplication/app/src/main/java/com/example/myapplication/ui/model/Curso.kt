package com.example.myapplication.ui.model

data class Curso(
    val nome: String = "",
    val stats: Status = Status.DISPONIVEL
){
    fun exemplo(): Curso {
        return Curso(
            nome = "Sigma",
            stats = Status.DISPONIVEL
        )
    }
}