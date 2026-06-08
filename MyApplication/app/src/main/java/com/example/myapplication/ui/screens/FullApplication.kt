package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.components.CursoCard
import com.example.myapplication.ui.model.Curso
import com.example.myapplication.ui.model.Status

@Composable
fun FullApplication(
    modifier: Modifier = Modifier
){

    var campo1 by remember { mutableStateOf("") }
    var campo2 by remember { mutableStateOf<Status>(Status.DISPONIVEL) }
    var cursos = remember { mutableStateListOf<Curso>() }

    Scaffold(){ innerPadding ->

        CursoCard(
            modifier = Modifier.padding(innerPadding),
            textValue = campo1,
            textChange = {campo1=it},
            onSetDisponivel = {campo2 = Status.DISPONIVEL},
            onSetAvancado ={campo2 = Status.AVANCADO} ,
            onSetIndisponivel = {campo2 = Status.EM_ANDAMENTO},
            onCadastrar = { cursos.add(Curso(campo1,campo2)) }
        )


    }
}