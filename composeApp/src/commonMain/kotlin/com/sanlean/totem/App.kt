package com.sanlean.totem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.sanlean.totem.presentation.SearchViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    var showWelcomeScreen by remember { mutableStateOf(true) }

    MaterialTheme {
        if (showWelcomeScreen) {
            WelcomeScreen(onStartClicked = { showWelcomeScreen = false })
        } else {
            val searchViewModel = koinViewModel<SearchViewModel>()
            ChildNameScreen(onBack = { showWelcomeScreen = true }, searchViewModel = searchViewModel)
        }
    }
}

@Composable
fun WelcomeScreen(onStartClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Bem-vindo ao Totem", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onStartClicked) {
            Text(text = "Iniciar")
        }
    }
}

@Composable
fun ChildNameScreen(
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
            label = { Text("Nome da Criança") },
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
            Text("Voltar")
        }
    }
}
