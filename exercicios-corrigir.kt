package com.example.myapplication.ui.components

import com.example.myapplication.ui.model.Curso
import kotlin.collections.map
import kotlin.collections.sortedByDescending

fun filtrar(cursos: List<Curso>, termo: String): List<Curso> {
    return cursos.filter { it.nome.contains(termo) }
}

fun cursoObrigatorio(cursos: List<Curso>, id: Int): Curso? {
    return cursos.find { it.id == id } ?: throw IllegalArgumentException("Curso com id $id não encontrado")
}

class BancoCursos {
    val cursos = listOf<Curso>()
}

fun ranking(resumos: List<Curso>): List<Curso> {
    return resumos.sortedWith { compareByDescending { it.mediaFinal }.thenBy { it.porcentagemProgresso }.thenBy { it.nome }}
}

fun montarLinhas(cursos: List<Curso>, matriculas: List<Matricula>): List<String> {
    return matriculas.map { matricula ->
        val curso = cursos.find { it.id == matricula.cursoId }
        "${matricula.alunoNome} - ${curso?.nome ?: "Curso não encontrado"} "
    }
}
