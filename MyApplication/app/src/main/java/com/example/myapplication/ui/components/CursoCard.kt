package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.model.Curso

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CursoCard(
    modifier: Modifier = Modifier,
    textValue: String,
    textChange: (String) -> Unit,
    onCadastrar: () -> Unit,
    onSetDisponivel: () -> Unit,
    onSetIndisponivel: () -> Unit,
    onSetAvancado: () -> Unit,
) {
    var expanded = false
    Scaffold() { innerPadding ->
        Card(modifier = modifier
            .fillMaxWidth()
            .padding(innerPadding)
        ) {
            Column() {

                OutlinedTextField(
                    value = textValue,
                    onValueChange = textChange,
                    label = { Text("Texto") }
                )
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {expanded = !expanded}
                ) {
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {expanded = false}
                    ){
                        DropdownMenuItem(
                            text = {Text("Disponível")},
                            onClick = {onSetDisponivel() ; expanded = false}
                        )
                        DropdownMenuItem(
                            text = {Text("Indisponível")},
                            onClick = {onSetIndisponivel() ; expanded = false}
                        )
                        DropdownMenuItem(
                            text = {Text("Avançado")},
                            onClick = {onSetAvancado() ; expanded = false}
                        )

                    }
                }

                Button(
                    onClick = onCadastrar
                ) { Text("Cadastrar") }

            }
        }
    }
}

@Composable
@Preview
fun PreviewCursoCard(){
    CursoCard(
        textValue = "A",
        textChange = {},
        onCadastrar = {},
        onSetDisponivel = {},
        onSetIndisponivel = {},
        onSetAvancado = {},

    )
}