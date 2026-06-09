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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
    curso: Curso
) {

    var expanded by remember { mutableStateOf(false) }
    var filterExpanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(Status.EM_ANDAMENTO) }
    var filterOption by remember {mutableStateOf(Status.EM_ANDAMENTO)}
    val cursos = remember { mutableStateListOf<Curso>() }
    var nomeCurso by remember { mutableStateOf("") }

    Scaffold() { innerPadding ->
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
                            onClick = { selectedOption = options ; expanded = false }
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { cursos.add(Curso(nomeCurso, selectedOption)) },
                ) { Text("Cadastrar") }

                ExposedDropdownMenuBox(
                    expanded = filterExpanded,
                    onExpandedChange = {filterExpanded = !filterExpanded}
                ) {
                    OutlinedTextField(modifier = Modifier
                        .menuAnchor(),
                        value = filterOption.label,
                        onValueChange = {}
                    )
                    ExposedDropdownMenu(
                        expanded = filterExpanded,
                        onDismissRequest = {filterExpanded = false}
                    ) {
                        Status.entries.forEach {
                                options->
                            DropdownMenuItem(
                                text = {Text(options.label)},
                                onClick = {filterOption = options ; filterExpanded = false}
                            )

                        }

                    }

                }
            }

            if (cursos.isEmpty()) {
                Box(modifier = modifier.fillMaxWidth()) {
                    Text("Nenhum curso encontrado")
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(32.dp)

                ) {
                    items(cursos.filter { cursos.contains() }) { item ->

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

@Composable
@Preview
fun PreviewCursoCard(){
    CursoCard(

        curso = Curso()

    )
}