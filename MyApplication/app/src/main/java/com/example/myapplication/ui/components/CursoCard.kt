package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Hd
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.model.Curso
import com.example.myapplication.ui.model.Status

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CursoCard(
    modifier: Modifier = Modifier,
) {

    var expanded by remember { mutableStateOf(false) }
    var filterExpanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(Status.DISPONIVEL) }
    var filterOption by remember { mutableStateOf(Status.DISPONIVEL) }
    val cursos = remember { mutableStateListOf<Curso>() }
    var nomeCurso by remember { mutableStateOf("") }
    var nomeMatricula by remember { mutableStateOf("") }
    var idMatricula by remember { mutableStateOf("") }
    var notaMatricula by remember { mutableStateOf("") }
    var percentualMatricula by remember { mutableStateOf("") }
    val cursosFiltrados = cursos.filter { it.stats == filterOption }
    var quantCursos by remember { mutableIntStateOf(0) }
    var selectedTab by remember { mutableIntStateOf(1) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor =MaterialTheme.colorScheme.background,
        bottomBar ={
            NavigationBar(){
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = {selectedTab = 0},
                    icon = { Icon(Icons.Default.Home, contentDescription = "")},
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = {selectedTab = 1},
                    icon = {Icon(Icons.Default.Hd, contentDescription = "")}
                )
            }
        }
    ) { innerPadding ->

        when(selectedTab){
            0->{
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(innerPadding)
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = nomeCurso,
                        onValueChange = { nomeCurso = it },
                        label = { Text("Texto") }
                    )
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            value = selectedOption.label,
                            onValueChange = {},
                            label = { Text("Status") },
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = expanded
                                )
                            }
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            Status.entries.forEach { options ->
                                DropdownMenuItem(
                                    text = { Text(text = options.label) },
                                    onClick = { selectedOption = options; expanded = false }
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Button(
                            onClick = { cursos.add(Curso(nomeCurso, selectedOption)); quantCursos++ },
                        ) { Text("Cadastrar") }

                        ExposedDropdownMenuBox(
                            expanded = filterExpanded,
                            onExpandedChange = { filterExpanded = !filterExpanded }
                        ) {
                            OutlinedTextField(
                                modifier = Modifier
                                    .menuAnchor(),
                                value = filterOption.label,
                                onValueChange = {},
                                readOnly = true
                            )
                            ExposedDropdownMenu(
                                expanded = filterExpanded,
                                onDismissRequest = { filterExpanded = false }
                            ) {
                                Status.entries.forEach { options ->
                                    DropdownMenuItem(
                                        text = { Text(options.label) },
                                        onClick = { filterOption = options; filterExpanded = false },

                                        )
                                }
                            }
                        }
                    }

                    Column(modifier = modifier.fillMaxWidth()) {
                        Text(
                            text = "Quant. Cursos: $quantCursos",
                            style = MaterialTheme.typography.headlineMedium
                        )

                        if (cursosFiltrados.isEmpty()) {
                            Column(modifier = modifier.fillMaxWidth()) {
                                Text("Nenhum curso encontrado")
                            }
                        } else {
                            LazyColumn(
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(32.dp)
                            ) {
                                items(cursosFiltrados.sortedBy { it.nome }) { item ->

                                    Card(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(16.dp)
                                        ) {
                                            Text(item.nome)
                                            Text(item.stats.label)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            1 -> {

                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(innerPadding)
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = nomeMatricula,
                        onValueChange = { nomeMatricula = it },
                        label = { Text("Nome") }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = idMatricula,
                        onValueChange = { idMatricula = it },
                        label = { Text("ID") }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = notaMatricula,
                        onValueChange = { notaMatricula = it },
                        label = { Text("Nota") }
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = percentualMatricula,
                        onValueChange = { percentualMatricula = it },
                        label = { Text("Percentual") }
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Button(
                            onClick = { cursos.add(Curso(nomeCurso, selectedOption)); quantCursos++ },
                        ) { Text("Cadastrar") }

                        ExposedDropdownMenuBox(
                            expanded = filterExpanded,
                            onExpandedChange = { filterExpanded = !filterExpanded }
                        ) {
                            OutlinedTextField(
                                modifier = Modifier
                                    .menuAnchor(),
                                value = filterOption.label,
                                onValueChange = {},
                                readOnly = true
                            )
                            ExposedDropdownMenu(
                                expanded = filterExpanded,
                                onDismissRequest = { filterExpanded = false }
                            ) {
                                Status.entries.forEach { options ->
                                    DropdownMenuItem(
                                        text = { Text(options.label) },
                                        onClick = { filterOption = options; filterExpanded = false },

                                        )
                                }
                            }
                        }
                    }

                    Column(modifier = modifier.fillMaxWidth()) {
                        Text(
                            text = "Quant. Cursos: $quantCursos",
                            style = MaterialTheme.typography.headlineMedium
                        )

                        if (cursosFiltrados.isEmpty()) {
                            Column(modifier = modifier.fillMaxWidth()) {
                                Text("Nenhum curso encontrado")
                            }
                        } else {
                            LazyColumn(
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(32.dp)
                            ) {
                                items(cursosFiltrados.sortedBy { it.nome }) { item ->

                                    Card(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(16.dp)
                                        ) {
                                            Text(item.nome)
                                            Text(item.stats.label)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}




@Composable
@Preview
fun PreviewCursoCard(){
    CursoCard(


    )
}