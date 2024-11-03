package com.sanlean.totem.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.sanlean.totem.presentation.components.SimpleKeyboard
import com.sanlean.totem.domain.utils.applyAccent
import com.sanlean.totem.domain.utils.isVowel
import org.jetbrains.compose.resources.stringResource
import totem.composeapp.generated.resources.Res
import totem.composeapp.generated.resources.back
import totem.composeapp.generated.resources.enter_child_name

@Composable
fun SearchScreen(
    onBack: () -> Unit,
    searchViewModel: SearchViewModel
) {
    var childName by remember { mutableStateOf("") }
    var accent by remember { mutableStateOf("") }

    val suggestions = searchViewModel.searchList.collectAsState()

    LaunchedEffect(childName) {
        searchViewModel.searchStudents(childName)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Campo de entrada para o nome da criança
        OutlinedTextField(
            value = childName,
            onValueChange = {
                childName = it
            },
            label = { Text(stringResource(Res.string.enter_child_name)) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Exibe as sugestões de nomes em uma lista rolável
        if (suggestions.value.isNotEmpty()) {
            // Limite de altura para a lista de sugestões
            Box(modifier = Modifier.heightIn(max = 200.dp)) { // Defina a altura máxima desejada
                LazyColumn {
                    items(suggestions.value.size) { suggestion ->
                        Text(
                            text = suggestions.value[suggestion],
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    // Preenche o campo com a sugestão clicada
                                    childName = suggestions.value[suggestion]
                                }
                                .padding(8.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Teclado virtual básico com letras maiúsculas
        SimpleKeyboard(
            onKeyClick = { key ->
                if (accent.isNotEmpty()) {
                    childName += if (key.isVowel()) {
                        key.applyAccent(accent)
                    } else {
                        key
                    }
                    accent = ""
                } else {
                    childName += key
                }
            },
            onDeleteClick = {
                if (childName.isNotEmpty()) {
                    childName = childName.dropLast(1)
                }
                accent = ""
            },
            onClearClick = {
                childName = ""
                accent = ""
            },
            onAccentClick = { key -> accent = key }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBack) {
            Text(stringResource(Res.string.back))
        }
    }
}